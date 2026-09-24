// app/src/main/java/com/example/iathena/components/AppBottomNavigation.kt
package com.example.iathena.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun AppBottomNavigation(currentRoute: String, onNavigate: (String) -> Unit) {
    val PurplePrimary = Color(0xFF6A4CF4)
    val PurpleLight = Color(0xFFF4F0FF)
    val TextGray = Color(0xFF8E8E93)

    NavigationBar(
        containerColor = Color.White,
        contentColor = TextGray,
        tonalElevation = 8.dp
    ) {
        NavigationBarItem(
            icon = { Icon(Icons.Default.Home, contentDescription = "Início") },
            label = { Text("Início") },
            selected = currentRoute == "home",
            onClick = { if (currentRoute != "home") onNavigate("home") },
            colors = NavigationBarItemDefaults.colors(selectedIconColor = PurplePrimary, selectedTextColor = PurplePrimary, indicatorColor = PurpleLight)
        )
        NavigationBarItem(
            icon = { Icon(Icons.Default.Star, contentDescription = "Missões") }, // Pode usar outro ícone de alvo/missão
            label = { Text("Missões") },
            selected = currentRoute == "missoes",
            onClick = { if (currentRoute != "missoes") onNavigate("missoes") },
            colors = NavigationBarItemDefaults.colors(selectedIconColor = PurplePrimary, selectedTextColor = PurplePrimary, indicatorColor = PurpleLight)
        )
        NavigationBarItem(
            icon = { Icon(Icons.Default.List, contentDescription = "Histórico") }, // Substituiu o Quiz
            label = { Text("Histórico") },
            selected = currentRoute == "historico",
            onClick = { if (currentRoute != "historico") onNavigate("historico") },
            colors = NavigationBarItemDefaults.colors(selectedIconColor = PurplePrimary, selectedTextColor = PurplePrimary, indicatorColor = PurpleLight)
        )
        NavigationBarItem(
            icon = { Icon(Icons.Default.Build, contentDescription = "Recompensas") }, // Pode usar ícone de Baú/Presente se tiver
            label = { Text("Recompensas") },
            selected = currentRoute == "recompensas",
            onClick = { if (currentRoute != "recompensas") onNavigate("recompensas") },
            colors = NavigationBarItemDefaults.colors(selectedIconColor = PurplePrimary, selectedTextColor = PurplePrimary, indicatorColor = PurpleLight)
        )
        NavigationBarItem(
            icon = { Icon(Icons.Default.Person, contentDescription = "Perfil") },
            label = { Text("Perfil") },
            selected = currentRoute == "profile",
            onClick = { if (currentRoute != "profile") onNavigate("profile") },
            colors = NavigationBarItemDefaults.colors(selectedIconColor = PurplePrimary, selectedTextColor = PurplePrimary, indicatorColor = PurpleLight)
        )
    }
}