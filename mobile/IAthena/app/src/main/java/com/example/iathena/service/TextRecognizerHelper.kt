package com.example.iathena.service

import android.graphics.Bitmap
import android.util.Log
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.text.TextRecognition
import com.google.mlkit.vision.text.latin.TextRecognizerOptions
import kotlinx.coroutines.tasks.await

class TextRecognizerHelper {
    // Inicializa o reconhecedor padrão (alfabeto latino, que inclui português)
    private val recognizer = TextRecognition.getClient(TextRecognizerOptions.DEFAULT_OPTIONS)

    suspend fun extractTextFromBitmap(bitmap: Bitmap): String? {
        return try {
            val image = InputImage.fromBitmap(bitmap, 0)
            val result = recognizer.process(image).await()

            Log.d("IATHENA_OCR", "Texto extraído: ${result.text}")
            result.text
        } catch (e: Exception) {
            Log.e("IATHENA_OCR", "Erro ao extrair texto", e)
            null
        }
    }
}