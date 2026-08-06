package com.example.iathena.navigation

import android.content.Intent
import android.net.Uri
import android.provider.Settings
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.iathena.screens.HomeScreen
import com.example.iathena.screens.SplashScreen
import com.example.iathena.screens.TutorialScreen
import com.example.iathena.service.OverlayService

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val context = LocalContext.current // Pega o contexto para podermos iniciar o serviço

    NavHost(
        navController = navController,
        startDestination = "splash"
    ) {
        composable("splash") {
            SplashScreen(
                onTimeout = {
                    navController.navigate("tutorial") {
                        popUpTo("splash") { inclusive = true }
                    }
                }
            )
        }

        composable("tutorial") {
            TutorialScreen(
                onComecar = {
                    navController.navigate("home") {
                        popUpTo("tutorial") { inclusive = true }
                    }
                }
            )
        }

        composable("home") {
            HomeScreen(
                onActivateOverlay = {
                    // A lógica do OverlayService veio para cá de forma limpa
                    if (!Settings.canDrawOverlays(context)) {
                        val intent = Intent(
                            Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                            Uri.parse("package:${context.packageName}")
                        )
                        context.startActivity(intent)
                    } else {
                        val serviceIntent = Intent(context, OverlayService::class.java)
                        context.startService(serviceIntent)
                        Log.d("IATHENA", "Serviço Overlay Iniciado com sucesso!")
                    }
                }
            )
        }
    }
}