package com.example.iathena

import android.app.Service
import android.content.Intent
import android.graphics.PixelFormat
import android.os.IBinder
import android.util.Log
import android.view.Gravity
import android.view.MotionEvent
import android.view.View
import android.view.WindowManager
import android.widget.Button
import android.widget.Toast

class OverlayService : Service() {

    private lateinit var windowManager: WindowManager
    private lateinit var overlayView: View

    override fun onBind(intent: Intent?): IBinder? {
        return null // Não precisamos fazer bind neste caso
    }

    override fun onCreate() {
        super.onCreate()


        windowManager = getSystemService(WINDOW_SERVICE) as WindowManager

        // Configurações da janela do overlay
        val sizeInPx = (64 * resources.displayMetrics.density).toInt()

        // 2. Configurações da janela do overlay com tamanho fixo (quadrado)
        val layoutParams = WindowManager.LayoutParams(
            sizeInPx, // Largura igual...
            sizeInPx, // ...à altura!
            WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY,
            WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
            PixelFormat.TRANSLUCENT
        )

        // Posição inicial (canto superior esquerdo)
        layoutParams.gravity = Gravity.TOP or Gravity.START
        layoutParams.x = 0
        layoutParams.y = 200

        // Criando um botão simples via código para ser o nosso Overlay
        val button = Button(this).apply {
            text = "🔎" // Texto curto ou emoji fica melhor em botão redondo
            textSize = 24f

            // Desenhando um fundo oval (que num espaço quadrado vira um círculo perfeito)
            val roundBackground = android.graphics.drawable.GradientDrawable().apply {
                shape = android.graphics.drawable.GradientDrawable.OVAL
                setColor(android.graphics.Color.parseColor("#6200EE")) // Cor roxinha

            }
            roundBackground.alpha = 0

            background = roundBackground

            // Aqui é onde o IAthena vai entrar em ação depois
            setOnClickListener {
                Log.d("IAthenaDebug", "Botão clicado! Hora de tirar print e rodar o OCR!")
            }
        }

        overlayView = button

        // Opcional: Adicionar lógica para arrastar o botão pela tela
        setupDragListener(button, layoutParams)

        // Adiciona a View na tela do sistema
        windowManager.addView(overlayView, layoutParams)

    }

    // Função auxiliar para permitir que o usuário arraste o botão pela tela
    private fun setupDragListener(view: View, params: WindowManager.LayoutParams) {
        var initialX = 0
        var initialY = 0
        var initialTouchX = 0f
        var initialTouchY = 0f

        view.setOnTouchListener { v, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    initialX = params.x
                    initialY = params.y
                    initialTouchX = event.rawX
                    initialTouchY = event.rawY
                    true
                }
                MotionEvent.ACTION_MOVE -> {
                    params.x = initialX + (event.rawX - initialTouchX).toInt()
                    params.y = initialY + (event.rawY - initialTouchY).toInt()
                    windowManager.updateViewLayout(view, params)
                    true
                }
                MotionEvent.ACTION_UP -> {
                    // Se o usuário tocou e soltou rápido (sem arrastar muito), conta como um clique normal
                    val diffX = Math.abs(event.rawX - initialTouchX)
                    val diffY = Math.abs(event.rawY - initialTouchY)
                    if (diffX < 10 && diffY < 10) {
                        v.performClick()
                    }
                    true
                }
                else -> false
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        // Muito importante remover a View quando o serviço for destruído para não vazar memória
        if (::overlayView.isInitialized) {
            windowManager.removeView(overlayView)
        }
    }
}