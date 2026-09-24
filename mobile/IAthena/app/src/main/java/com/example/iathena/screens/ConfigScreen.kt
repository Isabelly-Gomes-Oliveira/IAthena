// app/src/main/java/com/example/iathena/screens/ConfiguracoesScreen.kt
package com.example.iathena.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private val BgGray = Color(0xFFF8F9FA)

@Composable
fun ConfiguracoesScreen(onNavigateBack: () -> Unit) {
    Scaffold(
        containerColor = BgGray,
        topBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp, vertical = 20.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Surface(
                    onClick = onNavigateBack,
                    shape = RoundedCornerShape(12.dp),
                    color = Color.White,
                    shadowElevation = 2.dp,
                    modifier = Modifier.size(40.dp)
                ) {
                    Icon(
                        Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                        contentDescription = "Voltar",
                        tint = TextDark,
                        modifier = Modifier.padding(8.dp)
                    )
                }
                Spacer(modifier = Modifier.weight(1f))
                Text("Configurações", fontSize = 20.sp, fontWeight = FontWeight.ExtraBold, color = TextDark)
                Spacer(modifier = Modifier.weight(1f))

                // Ícone Decorativo
                Surface(
                    shape = RoundedCornerShape(12.dp),
                    color = Color.White,
                    shadowElevation = 2.dp,
                    modifier = Modifier.size(40.dp)
                ) {
                    Box(contentAlignment = Alignment.Center) {
                        Icon(Icons.Default.Settings, contentDescription = null, tint = PurplePrimary, modifier = Modifier.size(24.dp))
                        Box(modifier = Modifier
                            .align(Alignment.TopEnd)
                            .offset(x = (-6).dp, y = 6.dp)
                            .size(8.dp)
                            .background(Color(0xFFFF3B30), CircleShape))
                    }
                }
            }
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 20.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // HERO CARD
            item {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(24.dp),
                    colors = CardDefaults.cardColors(containerColor = PurpleLight),
                    elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
                ) {
                    Row(
                        modifier = Modifier.padding(20.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        // Placeholder Coruja
                        Box(
                            modifier = Modifier
                                .size(70.dp)
                                .background(Color.White, CircleShape),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(Icons.Default.Face, contentDescription = null, tint = PurplePrimary, modifier = Modifier.size(40.dp))
                        }
                        Spacer(modifier = Modifier.width(16.dp))
                        Column {
                            Text("Olá, Rafael!", fontSize = 18.sp, fontWeight = FontWeight.ExtraBold, color = TextDark)
                            Spacer(modifier = Modifier.height(4.dp))
                            Text("Aqui você pode personalizar o app\ndo seu jeito.", fontSize = 13.sp, color = TextDark, lineHeight = 16.sp)
                        }
                    }
                }
            }

            // ÍCONE FLUTUANTE (Toggle)
            item {
                var isFloatingEnabled by remember { mutableStateOf(true) }
                SettingsToggleCard(
                    icon = Icons.Default.AddCircle,
                    title = "Ícone flutuante",
                    description = "Ative para exibir o ícone do app sobre outras telas. Assim, você pode escanear notícias e textos de qualquer lugar.",
                    isChecked = isFloatingEnabled,
                    onCheckedChange = { isFloatingEnabled = it }
                )
            }

            // MODO ESCURO (Toggle)
            item {
                var isDarkMode by remember { mutableStateOf(false) }
                SettingsToggleCard(
                    icon = Icons.Default.Star, // Substituir por icone de Lua
                    title = "Modo escuro",
                    description = "Deixe o app mais confortável para seus olhos, especialmente à noite.",
                    isChecked = isDarkMode,
                    onCheckedChange = { isDarkMode = it }
                )
            }

            // IDIOMA (Dropdown/Text)
            item {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White)
                ) {
                    Row(
                        modifier = Modifier.padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(modifier = Modifier.size(48.dp).background(PurpleLight, RoundedCornerShape(12.dp)), contentAlignment = Alignment.Center) {
                            Icon(Icons.Default.Place, contentDescription = null, tint = PurplePrimary) // Ícone Globo
                        }
                        Spacer(modifier = Modifier.width(16.dp))
                        Column(modifier = Modifier.weight(1f)) {
                            Text("Idioma", fontWeight = FontWeight.Bold, fontSize = 15.sp, color = TextDark)
                            Text("Escolha o idioma do aplicativo.", fontSize = 12.sp, color = TextGray)
                        }
                        Surface(shape = RoundedCornerShape(20.dp), color = PurpleLight) {
                            Row(modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp), verticalAlignment = Alignment.CenterVertically) {
                                Text("Português (BR)", color = PurplePrimary, fontSize = 12.sp, fontWeight = FontWeight.Bold)
                                Spacer(modifier = Modifier.width(4.dp))
                                Icon(Icons.Default.KeyboardArrowDown, contentDescription = null, tint = PurplePrimary, modifier = Modifier.size(16.dp))
                            }
                        }
                    }
                }
            }

            // PRIVACIDADE (Expansível)
            item {
                var isPrivacyExpanded by remember { mutableStateOf(false) }
                Card(
                    modifier = Modifier.fillMaxWidth().clickable { isPrivacyExpanded = !isPrivacyExpanded },
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Box(modifier = Modifier.size(48.dp).background(PurpleLight, RoundedCornerShape(12.dp)), contentAlignment = Alignment.Center) {
                                Icon(Icons.Default.Lock, contentDescription = null, tint = PurplePrimary)
                            }
                            Spacer(modifier = Modifier.width(16.dp))
                            Column(modifier = Modifier.weight(1f)) {
                                Text("Privacidade", fontWeight = FontWeight.Bold, fontSize = 15.sp, color = TextDark)
                                Text("Gerencie seus dados e como eles são\nutilizados no app.", fontSize = 12.sp, color = TextGray)
                            }
                            Icon(
                                if (isPrivacyExpanded) Icons.Default.KeyboardArrowDown else Icons.Default.KeyboardArrowRight,
                                contentDescription = null,
                                tint = PurplePrimary
                            )
                        }
                        AnimatedVisibility(visible = isPrivacyExpanded) {
                            Column {
                                Spacer(modifier = Modifier.height(16.dp))
                                Surface(shape = RoundedCornerShape(12.dp), color = PurpleLight, modifier = Modifier.fillMaxWidth()) {
                                    Column(modifier = Modifier.padding(16.dp)) {
                                        Row(verticalAlignment = Alignment.CenterVertically) {
                                            Icon(Icons.Default.CheckCircle, contentDescription = null, tint = PurplePrimary, modifier = Modifier.size(18.dp))
                                            Spacer(modifier = Modifier.width(8.dp))
                                            Text("Seus dados estão protegidos", color = PurplePrimary, fontWeight = FontWeight.Bold, fontSize = 13.sp)
                                        }
                                        Spacer(modifier = Modifier.height(6.dp))
                                        Text(
                                            "Nenhum dado pessoal seu é salvo em rede. Todo o processamento de análise de veracidade é feito e armazenado localmente no seu dispositivo.",
                                            color = TextGray, fontSize = 12.sp, lineHeight = 16.sp
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }

            // PERMISSÕES DE OVERLAY (Toggle)
            item {
                var isOverlayEnabled by remember { mutableStateOf(true) }
                SettingsToggleCard(
                    icon = Icons.Default.Settings,
                    title = "Permissão de Sobreposição",
                    description = "Autorize o aplicativo a se sobrepor a outros apps para usar o scanner rapidamente.",
                    isChecked = isOverlayEnabled,
                    onCheckedChange = { isOverlayEnabled = it }
                )
            }

            // RODAPÉ
            item {
                Spacer(modifier = Modifier.height(24.dp))
                Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
                    Text("IAthena v1.0.0", color = TextGray, fontSize = 12.sp)
                    Text("Informação também é proteção.", color = TextGray, fontSize = 12.sp)
                }
                Spacer(modifier = Modifier.height(32.dp))
            }
        }
    }
}

@Composable
fun SettingsToggleCard(
    icon: ImageVector,
    title: String,
    description: String,
    isChecked: Boolean,
    onCheckedChange: (Boolean) -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier.size(48.dp).background(PurpleLight, RoundedCornerShape(12.dp)),
                contentAlignment = Alignment.Center
            ) {
                Icon(icon, contentDescription = null, tint = PurplePrimary)
            }
            Spacer(modifier = Modifier.width(16.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(title, fontWeight = FontWeight.Bold, fontSize = 15.sp, color = TextDark)
                Spacer(modifier = Modifier.height(2.dp))
                Text(description, fontSize = 12.sp, color = TextGray, lineHeight = 16.sp)
            }
            Spacer(modifier = Modifier.width(16.dp))

            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Switch(
                    checked = isChecked,
                    onCheckedChange = onCheckedChange,
                    colors = SwitchDefaults.colors(
                        checkedThumbColor = Color.White,
                        checkedTrackColor = PurplePrimary,
                        uncheckedThumbColor = Color.White,
                        uncheckedTrackColor = Color(0xFFE5E5EA),
                        uncheckedBorderColor = Color.Transparent
                    )
                )
                Text(
                    text = if (isChecked) "Ativado" else "Desativado",
                    fontSize = 10.sp,
                    color = if (isChecked) PurplePrimary else TextGray,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}