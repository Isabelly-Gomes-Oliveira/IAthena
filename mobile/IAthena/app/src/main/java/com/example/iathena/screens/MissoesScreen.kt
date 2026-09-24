// app/src/main/java/com/example/iathena/screens/MissoesScreen.kt
package com.example.iathena.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.iathena.R
import com.example.iathena.components.AppBottomNavigation

// Cores baseadas no design
private val BgGray = Color(0xFFF8F9FA)
private val GoldCoin = Color(0xFFFFC107)
private val GreenCheck = Color(0xFF34C759)

@Composable
fun MissoesScreen(onNavigate: (String) -> Unit) {
    Scaffold(
        bottomBar = { AppBottomNavigation(currentRoute = "missoes", onNavigate = onNavigate) },
        containerColor = BgGray
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            item { Spacer(modifier = Modifier.height(10.dp)) }

            // CABEÇALHO
            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Placeholder Coruja
                    Box(
                        modifier = Modifier
                            .size(50.dp)
                            .background(Color(0xFFE5E5EA), CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(Icons.Default.Face, contentDescription = "Coruja Placeholder", tint = Color.Gray)
                    }

                    Text("Missões", fontSize = 24.sp, fontWeight = FontWeight.ExtraBold, color = TextDark)

                    // Chip de Moedas
                    Surface(
                        shape = RoundedCornerShape(20.dp),
                        color = Color.White,
                        shadowElevation = 2.dp
                    ) {
                        Row(
                            modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.coin_icon),
                                contentDescription = "Moeda",
                                modifier = Modifier.size(16.dp)
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Text("2.450", fontWeight = FontWeight.Bold, fontSize = 14.sp)
                        }
                    }
                }
            }

            // SEQUÊNCIA DE DIAS
            item {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp),
                    shape = RoundedCornerShape(20.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
                ) {
                    Row(
                        modifier = Modifier.padding(20.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        // Fogo e Textos
                        Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.weight(0.4f)) {
                            Row(verticalAlignment = Alignment.Bottom) {
                                Icon(Icons.Default.Warning, contentDescription = "Fogo", tint = Color(0xFFFF3B30), modifier = Modifier.size(32.dp))
                                Text("7", fontSize = 32.sp, fontWeight = FontWeight.ExtraBold, color = TextDark)
                                Text(" dias", fontSize = 12.sp, color = TextDark, modifier = Modifier.padding(bottom = 6.dp))
                            }
                            Text("Sequência atual", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = PurplePrimary)
                            Text("Continue assim!", fontSize = 10.sp, color = TextGray)
                        }

                        // Dias da Semana
                        Row(
                            modifier = Modifier.weight(0.6f),
                            horizontalArrangement = Arrangement.SpaceEvenly
                        ) {
                            val dias = listOf("S", "T", "Q", "Q", "S")
                            dias.forEach { dia ->
                                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                    Box(
                                        modifier = Modifier.size(24.dp).background(GreenCheck, CircleShape),
                                        contentAlignment = Alignment.Center
                                    ) {
                                        Icon(Icons.Default.Check, contentDescription = null, tint = Color.White, modifier = Modifier.size(16.dp))
                                    }
                                    Spacer(modifier = Modifier.height(4.dp))
                                    Text(dia, fontSize = 12.sp, color = TextGray, fontWeight = FontWeight.Bold)
                                }
                            }
                            // Dia de hoje (não concluído)
                            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                Box(
                                    modifier = Modifier.size(24.dp).border(2.dp, PurplePrimary, CircleShape),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text("O", color = PurplePrimary, fontSize = 10.sp, fontWeight = FontWeight.Bold)
                                }
                                Spacer(modifier = Modifier.height(4.dp))
                                Text("D", fontSize = 12.sp, color = TextDark, fontWeight = FontWeight.Bold)
                            }
                        }
                    }
                }
            }

            // MISSÕES DIÁRIAS
            item {
                Column(modifier = Modifier.padding(horizontal = 20.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.Bottom
                    ) {
                        Text("Missões diárias", fontSize = 20.sp, fontWeight = FontWeight.ExtraBold, color = TextDark)
                        Text("Atualiza em 10h 24m", fontSize = 12.sp, color = PurplePrimary, fontWeight = FontWeight.Medium)
                    }
                    Spacer(modifier = Modifier.height(16.dp))

                    // Lista de Missões
                    DailyMissionCard(Icons.Default.Search, PurplePrimary, "Verifique 3 notícias", "Use o scanner para verificar\n3 conteúdos hoje", "2 / 3", 0.66f)
                    Spacer(modifier = Modifier.height(12.dp))
                    DailyMissionCard(Icons.Default.List, GreenCheck, "Complete o quiz diário", "Responda 5 perguntas\ne teste seus conhecimentos", "5 / 5", 1f, true)
                    Spacer(modifier = Modifier.height(12.dp))
                    DailyMissionCard(Icons.Default.Face, GoldCoin, "Detecte informações...", "Identifique 2 conteúdos\nsuspeitos", "1 / 2", 0.5f)
                    Spacer(modifier = Modifier.height(12.dp))
                    DailyMissionCard(Icons.Default.DateRange, Color(0xFF5AC8FA), "Faça sequência de 7 dias", "Mantenha sua sequência\nativa por 7 dias", "7 / 7", 1f, true)
                }
            }

            // DESAFIOS ESPECIAIS (Scroll Horizontal)
            item {
                Column {
                    Row(
                        modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text("Desafios especiais", fontSize = 20.sp, fontWeight = FontWeight.ExtraBold, color = TextDark)
                        Text("Ver todos >", fontSize = 14.sp, color = PurplePrimary, fontWeight = FontWeight.Bold)
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    LazyRow(
                        contentPadding = PaddingValues(horizontal = 20.dp),
                        horizontalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        item { SpecialChallengeCard("Desafio Semanal", PurplePrimary, "Investigador\nExperiente", "6 / 10", 0.6f) }
                        item { SpecialChallengeCard("Desafio de Quiz", GreenCheck, "Mestre do\nConhecimento", "10 / 15", 0.66f) }
                        item { SpecialChallengeCard("Desafio Detector", Color(0xFFFF9500), "Caçador de\nFake News", "3 / 5", 0.6f) }
                    }
                }
            }

            // FOOTER ROXO
            item {
                Card(
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp),
                    shape = RoundedCornerShape(20.dp),
                    colors = CardDefaults.cardColors(containerColor = PurpleLight),
                    elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
                ) {
                    Row(
                        modifier = Modifier.padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        // Placeholder Mini Coruja
                        Box(
                            modifier = Modifier.size(60.dp).background(Color.White, CircleShape),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(Icons.Default.Face, contentDescription = null, tint = PurplePrimary)
                        }
                        Spacer(modifier = Modifier.width(16.dp))
                        Text(
                            text = "Complete missões para ganhar XP, moedas\ne subir de nível! Quanto mais informado,\nmais você protege a verdade! 💜",
                            fontSize = 12.sp,
                            lineHeight = 16.sp,
                            color = TextDark
                        )
                    }
                }
            }
            item { Spacer(modifier = Modifier.height(20.dp)) }
        }
    }
}

// COMPONENTES AUXILIARES DA TELA DE MISSÕES
@Composable
fun DailyMissionCard(icon: ImageVector, color: Color, title: String, desc: String, progress: String, fraction: Float, isDone: Boolean = false) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Icon Box
            Box(
                modifier = Modifier.size(56.dp).background(color, RoundedCornerShape(16.dp)),
                contentAlignment = Alignment.Center
            ) {
                Icon(icon, contentDescription = null, tint = Color.White, modifier = Modifier.size(32.dp))
            }
            Spacer(modifier = Modifier.width(16.dp))

            // Text & Progress
            Column(modifier = Modifier.weight(1f)) {
                Text(title, fontWeight = FontWeight.Bold, fontSize = 15.sp, color = TextDark)
                Text(desc, fontSize = 12.sp, color = TextGray, lineHeight = 14.sp)
                Spacer(modifier = Modifier.height(8.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    // Custom Progress Bar
                    Box(modifier = Modifier.weight(1f).height(6.dp).background(Color(0xFFE5E5EA), CircleShape)) {
                        Box(modifier = Modifier.fillMaxWidth(fraction).fillMaxHeight().background(color, CircleShape))
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(progress, fontSize = 12.sp, fontWeight = FontWeight.Bold, color = TextDark)
                }
            }
            Spacer(modifier = Modifier.width(16.dp))

            // Badges or Check
            if (isDone) {
                Box(modifier = Modifier.size(32.dp).background(GreenCheck, CircleShape), contentAlignment = Alignment.Center) {
                    Icon(Icons.Default.Check, contentDescription = null, tint = Color.White)
                }
            } else {
                Column(horizontalAlignment = Alignment.End) {
                    // XP Badge
                    Surface(shape = RoundedCornerShape(6.dp), color = PurpleLight) {
                        Text("+50\nXP", fontSize = 10.sp, fontWeight = FontWeight.Bold, color = PurplePrimary, modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp))
                    }
                    Spacer(modifier = Modifier.height(6.dp))
                    // Coin Badge
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.Star, contentDescription = null, tint = GoldCoin, modifier = Modifier.size(12.dp))
                        Spacer(modifier = Modifier.width(2.dp))
                        Text("+20", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = TextDark)
                    }
                }
            }
        }
    }
}

@Composable
fun SpecialChallengeCard(tag: String, color: Color, title: String, progress: String, fraction: Float) {
    Card(
        modifier = Modifier.width(160.dp),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = color.copy(alpha = 0.1f)),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
    ) {
        Column(
            modifier = Modifier.padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Top Tag
            Surface(shape = RoundedCornerShape(10.dp), color = color) {
                Text(tag, color = Color.White, fontSize = 10.sp, fontWeight = FontWeight.Bold, modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp))
            }
            Spacer(modifier = Modifier.height(16.dp))
            // Big Hexagon Icon Placeholder
            Box(modifier = Modifier.size(64.dp).background(color, CircleShape), contentAlignment = Alignment.Center) {
                Icon(Icons.Default.Check, contentDescription = null, tint = Color.White, modifier = Modifier.size(32.dp))
            }
            Spacer(modifier = Modifier.height(12.dp))
            Text(title, fontWeight = FontWeight.Bold, fontSize = 14.sp, color = TextDark, textAlign = androidx.compose.ui.text.style.TextAlign.Center, lineHeight = 16.sp)
            Spacer(modifier = Modifier.height(12.dp))

            // Progress
            Box(modifier = Modifier.fillMaxWidth().height(6.dp).background(Color(0xFFE5E5EA), CircleShape)) {
                Box(modifier = Modifier.fillMaxWidth(fraction).fillMaxHeight().background(color, CircleShape))
            }
            Spacer(modifier = Modifier.height(4.dp))
            Text(progress, fontSize = 12.sp, fontWeight = FontWeight.Bold, color = TextDark)
        }
    }
}