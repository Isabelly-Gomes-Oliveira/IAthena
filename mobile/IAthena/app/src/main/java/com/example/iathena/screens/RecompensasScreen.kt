// app/src/main/java/com/example/iathena/screens/RecompensasScreen.kt
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
fun RecompensasScreen(onNavigate: (String) -> Unit) {
    Scaffold(
        bottomBar = { AppBottomNavigation(currentRoute = "recompensas", onNavigate = onNavigate) },
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
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Spacer(modifier = Modifier.weight(1f))
                    Text("Recompensas", fontSize = 24.sp, fontWeight = FontWeight.ExtraBold, color = TextDark)
                    Spacer(modifier = Modifier.weight(0.3f))
                    // Chip de Moedas
                    Surface(shape = RoundedCornerShape(20.dp), color = Color.White, shadowElevation = 2.dp) {
                        Row(modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp), verticalAlignment = Alignment.CenterVertically) {
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

            // HERO BANNER (Com placeholder da Coruja sobreposto)
            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp)
                        .height(130.dp)
                ) {
                    // Card Roxo Fundo
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 20.dp)
                            .align(Alignment.BottomCenter),
                        shape = RoundedCornerShape(24.dp),
                        colors = CardDefaults.cardColors(containerColor = PurpleDark)
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(start = 120.dp, end = 16.dp), // Espaço para a coruja
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column(modifier = Modifier.weight(1f)) {
                                Text("Guardião da Informação", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 14.sp)
                                Spacer(modifier = Modifier.height(4.dp))
                                Text("1.250 / 2.000 XP", color = Color.White.copy(alpha = 0.8f), fontSize = 12.sp)
                                Spacer(modifier = Modifier.height(8.dp))
                                // Barra
                                Box(modifier = Modifier.fillMaxWidth().height(6.dp).background(Color(0xFF4A2CBA), CircleShape)) {
                                    Box(modifier = Modifier.fillMaxWidth(0.6f).fillMaxHeight().background(GreenCheck, CircleShape))
                                }
                            }
                            Spacer(modifier = Modifier.width(16.dp))
                            Column(horizontalAlignment = Alignment.End) {
                                Text("Faltam 750 XP", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 12.sp)
                                Text("para o próximo nível", color = Color.White.copy(alpha = 0.7f), fontSize = 10.sp)
                            }
                        }
                    }

                    // Imagem Placeholder (Coruja e Baú) sobrepondo
                    Box(
                        modifier = Modifier
                            .align(Alignment.BottomStart)
                            .padding(start = 8.dp, bottom = 8.dp)
                            .size(110.dp)
                            .background(PurpleLight, CircleShape)
                            .border(4.dp, Color.White, CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(Icons.Default.Face, contentDescription = "Coruja Grande", tint = PurplePrimary, modifier = Modifier.size(60.dp))
                    }

                    // Badge do Nível 12
                    Box(
                        modifier = Modifier
                            .align(Alignment.CenterStart)
                            .offset(x = 90.dp, y = (10).dp)
                            .size(46.dp)
                            .background(Color.White, CircleShape)
                            .border(3.dp, PurplePrimary, CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Text("NÍVEL", fontSize = 8.sp, color = PurplePrimary, fontWeight = FontWeight.Bold)
                            Text("12", fontSize = 16.sp, color = PurplePrimary, fontWeight = FontWeight.ExtraBold, lineHeight = 16.sp)
                        }
                    }
                }
            }

            // SKINS
            item { SectionHeader("Skins") }
            item {
                LazyRow(
                    contentPadding = PaddingValues(horizontal = 20.dp),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    item { SkinCard("Athena Clássica", isOwned = true, price = "0") }
                    item { SkinCard("Detetive", isOwned = false, price = "1.200") }
                    item { SkinCard("Guardião Cyber", isOwned = false, price = "1.500") }
                }
            }

            // BADGES
            item { SectionHeader("Badges") }
            item {
                LazyRow(
                    contentPadding = PaddingValues(horizontal = 20.dp),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    item { BadgeCard(Icons.Default.Search, PurplePrimary, "Investigador", "Verificou 10 conteúdos", isUnlocked = true) }
                    item { BadgeCard(Icons.Default.Build, Color(0xFF007AFF), "Protetor", "Verificou 50 conteúdos", isUnlocked = false) }
                    item { BadgeCard(Icons.Default.Check, GreenCheck, "Caçador", "Verificou 100 conteúdos", isUnlocked = false) }
                    item { BadgeCard(Icons.Default.Star, GoldCoin, "Mestre", "Verificou 500 conteúdos", isUnlocked = false) }
                }
            }

            // TÍTULOS
            item { SectionHeader("Títulos") }
            item {
                LazyRow(
                    contentPadding = PaddingValues(horizontal = 20.dp),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    item { TitleCard("Guardião da\nInformação", "Nível 10+", PurpleLight, PurplePrimary, isUnlocked = true) }
                    item { TitleCard("Investigador\nDigital", "Nível 20+", Color.White, Color(0xFF007AFF), isUnlocked = false) }
                    item { TitleCard("Analista\nSênior", "Nível 30+", Color.White, GreenCheck, isUnlocked = false) }
                }
            }

            item { Spacer(modifier = Modifier.height(20.dp)) }
        }
    }
}

@Composable
fun SectionHeader(title: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(title, fontSize = 20.sp, fontWeight = FontWeight.ExtraBold, color = TextDark)
        Text("Ver todas >", fontSize = 14.sp, color = PurplePrimary, fontWeight = FontWeight.Bold)
    }
}

@Composable
fun SkinCard(name: String, isOwned: Boolean, price: String) {
    Card(
        modifier = Modifier.width(130.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        border = if (isOwned) androidx.compose.foundation.BorderStroke(2.dp, PurplePrimary) else null
    ) {
        Column(
            modifier = Modifier.padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box {
                // Placeholder Skin
                Box(
                    modifier = Modifier.size(80.dp).background(PurpleLight, CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(Icons.Default.Face, contentDescription = null, tint = PurplePrimary, modifier = Modifier.size(40.dp))
                }
                if (isOwned) {
                    Box(modifier = Modifier.align(Alignment.TopEnd).size(24.dp).background(GreenCheck, CircleShape).border(2.dp, Color.White, CircleShape), contentAlignment = Alignment.Center) {
                        Icon(Icons.Default.Check, contentDescription = null, tint = Color.White, modifier = Modifier.size(14.dp))
                    }
                }
            }
            Spacer(modifier = Modifier.height(12.dp))
            Text(name, fontWeight = FontWeight.Bold, fontSize = 12.sp, color = TextDark, textAlign = androidx.compose.ui.text.style.TextAlign.Center)
            Spacer(modifier = Modifier.height(12.dp))
            if (isOwned) {
                Surface(shape = RoundedCornerShape(12.dp), color = PurpleLight, modifier = Modifier.fillMaxWidth()) {
                    Text("Em uso", color = PurplePrimary, fontSize = 12.sp, fontWeight = FontWeight.Bold, textAlign = androidx.compose.ui.text.style.TextAlign.Center, modifier = Modifier.padding(vertical = 6.dp))
                }
            } else {
                Surface(shape = RoundedCornerShape(12.dp), color = Color(0xFFFFF9E6), modifier = Modifier.fillMaxWidth()) {
                    Row(horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(vertical = 6.dp)) {
                        Icon(Icons.Default.Star, contentDescription = null, tint = GoldCoin, modifier = Modifier.size(12.dp))
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(price, color = TextDark, fontSize = 12.sp, fontWeight = FontWeight.Bold)
                    }
                }
            }
        }
    }
}

@Composable
fun BadgeCard(icon: androidx.compose.ui.graphics.vector.ImageVector, color: Color, title: String, subtitle: String, isUnlocked: Boolean) {
    val displayColor = if (isUnlocked) color else Color.LightGray
    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.width(90.dp)) {
        Box {
            // Hexagon Placeholder (usando Box arredondado como fallback)
            Box(
                modifier = Modifier.size(70.dp).background(displayColor.copy(alpha = 0.2f), RoundedCornerShape(16.dp)),
                contentAlignment = Alignment.Center
            ) {
                Box(modifier = Modifier.size(50.dp).background(displayColor, RoundedCornerShape(12.dp)), contentAlignment = Alignment.Center) {
                    Icon(icon, contentDescription = null, tint = Color.White, modifier = Modifier.size(24.dp))
                }
            }
            if (isUnlocked) {
                Box(modifier = Modifier.align(Alignment.TopEnd).offset(x = 6.dp, y = (-6).dp).size(20.dp).background(GreenCheck, CircleShape).border(2.dp, Color.White, CircleShape), contentAlignment = Alignment.Center) {
                    Icon(Icons.Default.Check, contentDescription = null, tint = Color.White, modifier = Modifier.size(12.dp))
                }
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(title, fontWeight = FontWeight.Bold, fontSize = 12.sp, color = TextDark)
        Text(subtitle, fontSize = 10.sp, color = TextGray, textAlign = androidx.compose.ui.text.style.TextAlign.Center)
    }
}

@Composable
fun TitleCard(title: String, level: String, bgColor: Color, iconColor: Color, isUnlocked: Boolean) {
    val alphaColor = if (isUnlocked) iconColor else Color.LightGray
    Card(
        modifier = Modifier.width(110.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = if(isUnlocked) bgColor else Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = if(isUnlocked) 0.dp else 2.dp),
        border = if (isUnlocked) androidx.compose.foundation.BorderStroke(1.dp, alphaColor) else null
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(if (isUnlocked) Icons.Default.Star else Icons.Default.Lock, contentDescription = null, tint = alphaColor, modifier = Modifier.size(32.dp))
            Spacer(modifier = Modifier.height(12.dp))
            Text(title, fontWeight = FontWeight.Bold, fontSize = 12.sp, color = if(isUnlocked) alphaColor else TextGray, textAlign = androidx.compose.ui.text.style.TextAlign.Center, lineHeight = 14.sp)
            Spacer(modifier = Modifier.height(8.dp))
            Text(level, fontSize = 10.sp, color = TextGray)
        }
    }
}