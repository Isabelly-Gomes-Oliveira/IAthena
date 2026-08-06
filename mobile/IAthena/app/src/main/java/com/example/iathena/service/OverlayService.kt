package com.example.iathena.service

import android.app.Service
import android.content.Intent
import android.graphics.Color
import android.graphics.PixelFormat
import android.graphics.drawable.GradientDrawable
import android.os.IBinder
import android.text.method.ScrollingMovementMethod
import android.util.Log
import android.view.Gravity
import android.view.MotionEvent
import android.view.View
import android.view.WindowManager
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject

class OverlayService : Service() {

    private lateinit var windowManager: WindowManager
    private lateinit var overlayContainer: LinearLayout
    private lateinit var resultTextView: TextView

    private val client = OkHttpClient()

    override fun onBind(intent: Intent?): IBinder? = null

    override fun onCreate() {
        super.onCreate()

        windowManager = getSystemService(WINDOW_SERVICE) as WindowManager

        val layoutParams = WindowManager.LayoutParams(
            WindowManager.LayoutParams.WRAP_CONTENT,
            WindowManager.LayoutParams.WRAP_CONTENT,
            WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY,
            WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
            PixelFormat.TRANSLUCENT
        )

        layoutParams.gravity = Gravity.TOP or Gravity.START
        layoutParams.x = 0
        layoutParams.y = 200

        overlayContainer = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            gravity = Gravity.CENTER_HORIZONTAL
        }

        // 1. CORREÇÃO DA CAIXA DE TEXTO (Quebra de linha e largura máxima)
        val desiredWidthPx = (resources.displayMetrics.widthPixels * 0.85).toInt() // LARGURA FIXA: 85% da tela
        val maxHeightPx = (resources.displayMetrics.heightPixels * 0.5).toInt()    // Limita a 50% da altura

        resultTextView = TextView(this).apply {
            // 👇 O SEGREDO ESTÁ AQUI 👇
            // Em vez de deixar a caixa tentar adivinhar a largura, nós cravamos o tamanho dela!
            this.layoutParams = LinearLayout.LayoutParams(desiredWidthPx, LinearLayout.LayoutParams.WRAP_CONTENT)

            text = "..."
            setTextColor(Color.WHITE)
            setPadding(32, 32, 32, 32)
            visibility = View.GONE

            isSingleLine = false
            maxHeight = maxHeightPx
            movementMethod = ScrollingMovementMethod()

            background = GradientDrawable().apply {
                setColor(Color.parseColor("#CC000000"))
                cornerRadius = 16f
            }
        }

        val sizeInPx = (64 * resources.displayMetrics.density).toInt()
        val button = Button(this).apply {
            this.layoutParams = LinearLayout.LayoutParams(sizeInPx, sizeInPx).apply {
                bottomMargin = 16
            }
            text = "🔎"
            textSize = 24f

            val roundBackground = GradientDrawable().apply {
                shape = GradientDrawable.OVAL
                setColor(Color.parseColor("#6200EE"))
            }
            background = roundBackground

            var isCapturing = false

            setOnClickListener {
                isCapturing = !isCapturing

                if (isCapturing) {
                    text = "⏳"
                    roundBackground.setColor(Color.parseColor("#03DAC5"))
                    resultTextView.visibility = View.VISIBLE
                    resultTextView.text = "Pensando..."
                    enviarMensagemParaApi()
                } else {
                    text = "🔎"
                    roundBackground.setColor(Color.parseColor("#6200EE"))
                    resultTextView.visibility = View.GONE
                }
            }

            setOnLongClickListener {
                stopSelf()
                true
            }
        }

        overlayContainer.addView(button)
        overlayContainer.addView(resultTextView)

        // 2. CORREÇÃO DO ARRASTE: Dizemos que o botão escuta o toque, mas o Container inteiro se move
        setupDragListener(button, overlayContainer, layoutParams)

        windowManager.addView(overlayContainer, layoutParams)
    }

    private fun enviarMensagemParaApi() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val jsonBody = JSONObject().apply {
                    put("texto", "Oi")
                }

                val requestBody = jsonBody.toString().toRequestBody("application/json; charset=utf-8".toMediaType())

                val request = Request.Builder()
                    .url("http://10.0.2.2:3000/teste")
                    .post(requestBody)
                    .build()

                val response = client.newCall(request).execute()
                val responseString = response.body?.string()

                withContext(Dispatchers.Main) {
                    if (response.isSuccessful && responseString != null) {
                        try {
                            val jsonResponse = JSONObject(responseString)
                            val respObj = jsonResponse.getJSONObject("respostaCompleta")
                            val textoFinal = respObj.getString("textoResultado")
                            resultTextView.text = textoFinal
                        } catch (e: Exception) {
                            Log.e("IAthenaDebug", "Erro ao processar o JSON da API", e)
                            resultTextView.text = "Erro ao ler os dados da API"
                        }
                    } else {
                        resultTextView.text = "Erro da API: ${response.code}"
                    }
                }
            } catch (e: Exception) {
                Log.e("IAthenaDebug", "Erro ao conectar com a API", e)
                withContext(Dispatchers.Main) {
                    resultTextView.text = "Falha na conexão!"
                }
            }
        }
    }

    // 3. FUNÇÃO DE ARRASTAR ATUALIZADA
    private fun setupDragListener(touchView: View, rootView: View, params: WindowManager.LayoutParams) {
        var initialX = 0
        var initialY = 0
        var initialTouchX = 0f
        var initialTouchY = 0f
        var isDragging = false

        touchView.setOnTouchListener { v, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    initialX = params.x
                    initialY = params.y
                    initialTouchX = event.rawX
                    initialTouchY = event.rawY
                    isDragging = false
                    false
                }
                MotionEvent.ACTION_MOVE -> {
                    val diffX = Math.abs(event.rawX - initialTouchX)
                    val diffY = Math.abs(event.rawY - initialTouchY)

                    if (diffX > 10 || diffY > 10) {
                        if (!isDragging) {
                            isDragging = true
                            v.cancelLongPress()
                        }

                        params.x = initialX + (event.rawX - initialTouchX).toInt()
                        params.y = initialY + (event.rawY - initialTouchY).toInt()

                        // Atualiza o rootView (a janela toda) em vez do v (o botão)
                        windowManager.updateViewLayout(rootView, params)
                        true
                    } else {
                        false
                    }
                }
                MotionEvent.ACTION_UP -> {
                    if (isDragging) true else false
                }
                else -> false
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        if (::overlayContainer.isInitialized) {
            windowManager.removeView(overlayContainer)
        }
    }
}