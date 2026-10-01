package com.example.iathena.screens

import android.app.Activity
import android.content.Context
import android.graphics.Bitmap
import android.graphics.PixelFormat
import android.hardware.display.DisplayManager
import android.media.ImageReader
import android.media.projection.MediaProjection
import android.media.projection.MediaProjectionManager
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.iathena.service.TextRecognizerHelper
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.math.max
import kotlin.math.min

class CaptureActivity : ComponentActivity() {

    private lateinit var projectionManager: MediaProjectionManager
    private var capturedBitmap by mutableStateOf<Bitmap?>(null)
    private val ocrHelper = TextRecognizerHelper()

    private val startMediaProjection = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK && result.data != null) {
            // Atraso de 500ms para dar tempo do aviso de permissão sumir da tela completamente
            Handler(Looper.getMainLooper()).postDelayed({
                try {
                    val projection = projectionManager.getMediaProjection(result.resultCode, result.data!!)!!
                    captureScreen(projection)
                } catch (e: Exception) {
                    Toast.makeText(this, "Erro de permissão do sistema", Toast.LENGTH_LONG).show()
                    finish()
                }
            }, 500)
        } else {
            Toast.makeText(this, "Permissão negada", Toast.LENGTH_SHORT).show()
            finish()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        projectionManager = getSystemService(Context.MEDIA_PROJECTION_SERVICE) as MediaProjectionManager

        setContent {
            val scope = rememberCoroutineScope()
            var isPermissionRequested by remember { mutableStateOf(false) }

            if (capturedBitmap == null) {
                // Tela invisível que pisca 1% apenas para forçar a atualização dos frames
                var tick by remember { mutableStateOf(false) }
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(if (tick) Color(0x01000000) else Color.Transparent)
                )

                LaunchedEffect(Unit) {
                    if (!isPermissionRequested) {
                        isPermissionRequested = true
                        startMediaProjection.launch(projectionManager.createScreenCaptureIntent())
                    }
                    while (capturedBitmap == null) {
                        delay(50)
                        tick = !tick
                    }
                }
            } else {
                CropScreen(
                    bitmap = capturedBitmap!!,
                    onCancel = { finish() },
                    onCrop = { rect ->
                        val croppedBitmap = cropBitmap(capturedBitmap!!, rect)

                        if (croppedBitmap != null) {
                            scope.launch {
                                val text = ocrHelper.extractTextFromBitmap(croppedBitmap)
                                if (!text.isNullOrBlank()) {
                                    Toast.makeText(this@CaptureActivity, "Lido: $text", Toast.LENGTH_LONG).show()
                                    finish()
                                } else {
                                    Toast.makeText(this@CaptureActivity, "Nenhum texto encontrado.", Toast.LENGTH_SHORT).show()
                                    finish()
                                }
                            }
                        } else {
                            Toast.makeText(this@CaptureActivity, "Desenhe um retângulo válido.", Toast.LENGTH_SHORT).show()
                        }
                    }
                )
            }
        }
    }

    private fun captureScreen(projection: MediaProjection) {
        val metrics = resources.displayMetrics
        val width = metrics.widthPixels
        val height = metrics.heightPixels
        val density = metrics.densityDpi

        val projectionCallback = object : MediaProjection.Callback() {
            override fun onStop() {
                super.onStop()
                projection.unregisterCallback(this)
            }
        }
        projection.registerCallback(projectionCallback, Handler(Looper.getMainLooper()))

        val imageReader = ImageReader.newInstance(width, height, PixelFormat.RGBA_8888, 2)

        val virtualDisplay = projection.createVirtualDisplay(
            "ScreenCapture",
            width, height, density,
            DisplayManager.VIRTUAL_DISPLAY_FLAG_AUTO_MIRROR,
            imageReader.surface, null, null
        )

        imageReader.setOnImageAvailableListener({ reader ->
            try {
                val image = reader.acquireLatestImage()
                if (image != null) {
                    val planes = image.planes
                    val buffer = planes[0].buffer
                    val pixelStride = planes[0].pixelStride
                    val rowStride = planes[0].rowStride
                    val rowPadding = rowStride - pixelStride * width

                    val bitmapWidth = width + rowPadding / pixelStride
                    val bitmap = Bitmap.createBitmap(bitmapWidth, height, Bitmap.Config.ARGB_8888)

                    buffer.position(0)
                    bitmap.copyPixelsFromBuffer(buffer)
                    val finalBitmap = Bitmap.createBitmap(bitmap, 0, 0, width, height)

                    runOnUiThread {
                        capturedBitmap = finalBitmap
                    }

                    image.close()
                    virtualDisplay?.release()
                    projection.stop()
                    reader.setOnImageAvailableListener(null, null)
                }
            } catch (e: Exception) {
                Log.e("IATHENA", "Erro ao processar imagem", e)
            }
        }, Handler(Looper.getMainLooper()))
    }

    private fun cropBitmap(original: Bitmap, rect: Rect): Bitmap? {
        // Usa min e max para garantir coordenadas corretas, independentemente de onde o arrasto começou
        val left = min(rect.left, rect.right).toInt().coerceAtLeast(0)
        val top = min(rect.top, rect.bottom).toInt().coerceAtLeast(0)
        val right = max(rect.left, rect.right).toInt().coerceAtMost(original.width)
        val bottom = max(rect.top, rect.bottom).toInt().coerceAtMost(original.height)

        val width = right - left
        val height = bottom - top

        // Evita travamento se o usuário apenas clicar na tela sem arrastar
        if (width <= 0 || height <= 0) return null

        return Bitmap.createBitmap(original, left, top, width, height)
    }
}

@Composable
fun CropScreen(bitmap: Bitmap, onCancel: () -> Unit, onCrop: (Rect) -> Unit) {
    var startOffset by remember { mutableStateOf<Offset?>(null) }
    var endOffset by remember { mutableStateOf<Offset?>(null) }

    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            bitmap = bitmap.asImageBitmap(),
            contentDescription = "Print da tela",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        Canvas(
            modifier = Modifier
                .fillMaxSize()
                .graphicsLayer { alpha = 0.99f }
                .pointerInput(Unit) {
                    detectDragGestures(
                        onDragStart = { offset ->
                            startOffset = offset
                            endOffset = offset
                        },
                        onDrag = { change, _ ->
                            endOffset = change.position
                        }
                    )
                }
        ) {
            drawRect(Color.Black.copy(alpha = 0.6f))

            if (startOffset != null && endOffset != null) {
                val rect = Rect(startOffset!!, endOffset!!)
                drawRect(Color.Transparent, topLeft = rect.topLeft, size = rect.size, blendMode = BlendMode.Clear)
                drawRect(Color(0xFF6A4CF4), topLeft = rect.topLeft, size = rect.size, style = Stroke(width = 6f))
            }
        }

        Text(
            text = "Desenhe um retângulo sobre o texto",
            color = Color.White,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = 48.dp)
                .background(Color.Black.copy(alpha = 0.5f), RoundedCornerShape(8.dp))
                .padding(horizontal = 16.dp, vertical = 8.dp)
        )

        Row(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 48.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            FloatingActionButton(onClick = onCancel, containerColor = Color.White) {
                Icon(Icons.Default.Close, contentDescription = "Cancelar", tint = Color.Red)
            }

            if (startOffset != null && endOffset != null) {
                ExtendedFloatingActionButton(
                    onClick = { onCrop(Rect(startOffset!!, endOffset!!)) },
                    containerColor = Color(0xFF6A4CF4),
                    contentColor = Color.White
                ) {
                    Icon(Icons.Default.Check, contentDescription = null)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Analisar Texto", fontWeight = FontWeight.Bold, fontSize = 16.sp)
                }
            }
        }
    }
}