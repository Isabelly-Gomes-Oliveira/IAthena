package com.example.iathena.navigation

import android.content.Intent
import android.net.Uri
import android.provider.Settings
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.iathena.screens.HistoricoScreen
import com.example.iathena.screens.HomeScreen
import com.example.iathena.screens.MissoesScreen
import com.example.iathena.screens.ProfileScreen
import com.example.iathena.screens.RecompensasScreen
import com.example.iathena.screens.ResultadoScreen
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
                },
                onNavigate = { route -> navController.navigate(route) }
            )
        }
        composable("missoes") {
            MissoesScreen(onNavigate = { route -> navController.navigate(route) })
        }
        composable("recompensas") {
            RecompensasScreen(onNavigate = { route -> navController.navigate(route) })
        }

        composable("profile") {
            ProfileScreen(onNavigate = { route -> navController.navigate(route) })
        }
        composable("historico") {
            HistoricoScreen(
                onNavigate = { route -> navController.navigate(route) },
                onNavigateToResult = { id -> navController.navigate("resultado/$id") } // Passa o ID na rota
            )
        }
        composable(
            route = "resultado/{id}", // Define que essa rota espera um parâmetro {id}
            arguments = listOf(navArgument("id") { type = NavType.StringType })
        ) { backStackEntry ->
            // Extrai o ID da rota
            val analiseId = backStackEntry.arguments?.getString("id") ?: "1"

            ResultadoScreen(
                analiseId = analiseId,
                onNavigateBack = { navController.popBackStack() }
            )
        }
    }
}