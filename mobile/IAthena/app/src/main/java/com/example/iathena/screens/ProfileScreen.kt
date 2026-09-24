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
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.iathena.R
import com.example.iathena.components.AppBottomNavigation

@Composable
fun ProfileScreen(onNavigate: (String) -> Unit) {
    Scaffold(
        bottomBar = { AppBottomNavigation(currentRoute = "missoes", onNavigate = onNavigate) },
        containerColor = BackgroundGray
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 20.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            item { Spacer(modifier = Modifier.height(10.dp)) }
            item { ProfileTopBar() }
            item { UserMainCard() }
            item { StatsRowCard() }
            item { DailyStreakTimelineCard() }
            item { GeneralProgressCard() }
            item { AchievementsSection() }
            item { Spacer(modifier = Modifier.height(20.dp)) }
        }
    }
}

@Composable
fun ProfileTopBar() {
    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        // Título Centralizado
        Text(
            text = "Perfil",
            fontSize = 22.sp,
            fontWeight = FontWeight.ExtraBold,
            color = TextDark
        )
        // Ícone de Notificação alinhado à direita (sem o ícone de configurações!)
        Box(
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .size(45.dp)
                .background(Color.White, RoundedCornerShape(12.dp)),
            contentAlignment = Alignment.Center
        ) {
            Icon(Icons.Outlined.Notifications, contentDescription = "Notificações", tint = PurplePrimary)
            // Bolinha vermelha de notificação
            Box(
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .offset(x = (-8).dp, y = 8.dp)
                    .size(8.dp)
                    .background(RedAlert, CircleShape)
            )
        }
    }
}

@Composable
fun UserMainCard() {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(containerColor = PurpleDark)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Foto de Perfil com Botão de Editar
            Box(modifier = Modifier.size(80.dp)) {
                Box(
                    modifier = Modifier
                        .size(80.dp)
                        .background(Color.White, CircleShape)
                        .padding(2.dp)
                        .background(PurpleLight, CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(Icons.Default.Face, contentDescription = "Avatar", tint = PurplePrimary, modifier = Modifier.size(50.dp))
                }

                // Botão de Editar flutuante
                Box(
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .size(26.dp)
                        .background(Color(0xFF333333), CircleShape)
                        .border(2.dp, PurpleDark, CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(Icons.Default.Edit, contentDescription = "Editar", tint = Color.White, modifier = Modifier.size(12.dp))
                }
            }

            Spacer(modifier = Modifier.width(16.dp))

            // Informações Centrais (Nome, XP)
            Column(modifier = Modifier.weight(1f)) {
                Text(text = "Rafael", color = Color.White, fontSize = 22.sp, fontWeight = FontWeight.ExtraBold)
                Spacer(modifier = Modifier.height(4.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.Build, contentDescription = "Guardião", tint = Color.White.copy(alpha = 0.7f), modifier = Modifier.size(14.dp))
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(text = "Guardião da Informação", color = Color.White.copy(alpha = 0.9f), fontSize = 12.sp, lineHeight = 14.sp)
                }

                Spacer(modifier = Modifier.height(12.dp))

                Row {
                    Text(text = "1.250 / 2.000 ", color = Color.White, fontSize = 12.sp, fontWeight = FontWeight.Bold)
                    Text(text = "XP", color = GreenSuccess, fontSize = 12.sp, fontWeight = FontWeight.ExtraBold)
                }
                Spacer(modifier = Modifier.height(4.dp))

                // BARRA DE PROGRESSO CUSTOMIZADA
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(6.dp)
                        .clip(RoundedCornerShape(3.dp))
                        .background(Color.White.copy(alpha = 0.2f))
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth(fraction = 0.625f)
                            .fillMaxHeight()
                            .clip(RoundedCornerShape(3.dp))
                            .background(Color(0xFFA584FF)) // Roxo mais claro do design
                    )
                }

                Spacer(modifier = Modifier.height(6.dp))
                Row {
                    Text(text = "Faltam ", color = Color.White.copy(alpha = 0.8f), fontSize = 10.sp)
                    Text(text = "750 XP ", color = GreenSuccess, fontSize = 10.sp, fontWeight = FontWeight.Bold)
                    Text(text = "para o próximo nível", color = Color.White.copy(alpha = 0.8f), fontSize = 10.sp, lineHeight = 10.sp)
                }
            }

            Spacer(modifier = Modifier.width(12.dp))

            // Hexágono do Nível na Direita
            Box(
                modifier = Modifier.size(80.dp),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.lvl_bg), // Substitua pelo seu fundo
                    contentDescription = "Nível",
                    modifier = Modifier.fillMaxSize()
                )
                Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.padding(top = 4.dp)) {
                    Text("NÍVEL", color = Color.White, fontSize = 9.sp, fontWeight = FontWeight.Bold)
                    Text("12", color = Color.White, fontSize = 22.sp, fontWeight = FontWeight.ExtraBold, lineHeight = 22.sp)
                }
            }
        }
    }
}

@Composable
fun StatsRowCard() {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Top
        ) {
            StatItem(icon = Icons.Default.Check, iconColor = GreenSuccess, value = "128", label = "Fake news\ndetectadas")
            StatItem(icon = Icons.Default.Search, iconColor = PurplePrimary, value = "342", label = "Verificações\nrealizadas")
            StatItem(isImage = true, imageRes = R.drawable.coin_icon, value = "2.450", label = "Moedas\nacumuladas")
            StatItem(icon = Icons.Default.Star, iconColor = PurplePrimary, value = "15", label = "Quizzes\nconcluídos")
        }
    }
}

@Composable
fun StatItem(icon: androidx.compose.ui.graphics.vector.ImageVector? = null, iconColor: Color = Color.Unspecified, isImage: Boolean = false, imageRes: Int = 0, value: String, label: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.width(70.dp)) {
        Box(
            modifier = Modifier
                .size(40.dp)
                .background(
                    if (!isImage) iconColor.copy(alpha = 0.1f) else Color.Transparent,
                    CircleShape
                ),
            contentAlignment = Alignment.Center
        ) {
            if (isImage) {
                Image(painter = painterResource(id = imageRes), contentDescription = null, modifier = Modifier.size(24.dp))
            } else if (icon != null) {
                Icon(icon, contentDescription = null, tint = iconColor, modifier = Modifier.size(20.dp))
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = value, fontSize = 16.sp, fontWeight = FontWeight.ExtraBold, color = TextDark)
        Spacer(modifier = Modifier.height(4.dp))
        Text(text = label, fontSize = 10.sp, color = TextGray, textAlign = TextAlign.Center, lineHeight = 12.sp)
    }
}

@Composable
fun DailyStreakTimelineCard() {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column(modifier = Modifier.padding(20.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.Warning, contentDescription = "Fogo", tint = Color(0xFFFF9500), modifier = Modifier.size(22.dp))
                    Spacer(modifier = Modifier.width(8.dp))
                    Column {
                        Text("Sequência diária", fontWeight = FontWeight.Bold, fontSize = 15.sp, color = TextDark)
                        Spacer(modifier = Modifier.height(2.dp))
                        Text("Você está mandando muito bem!", fontSize = 11.sp, color = TextGray)
                    }
                }
                Row(verticalAlignment = Alignment.Bottom) {
                    Text("7 ", fontSize = 16.sp, fontWeight = FontWeight.ExtraBold, color = PurplePrimary)
                    Text("dias", fontSize = 14.sp, color = PurplePrimary, fontWeight = FontWeight.Bold)
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            // Linha do Tempo da Sequência
            Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                // A linha cinza de fundo que conecta os círculos
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(2.dp)
                        .background(BackgroundGray)
                        .padding(horizontal = 20.dp) // Não deixa a linha vazar para fora
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    val dias = listOf("S", "T", "Q", "Q", "S", "S", "D")
                    dias.forEachIndexed { index, dia ->
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Box(
                                modifier = Modifier
                                    .size(32.dp)
                                    .background(if (index < 6) GreenSuccess else Color.White, CircleShape)
                                    .border(2.dp, if (index < 6) GreenSuccess else PurplePrimary, CircleShape),
                                contentAlignment = Alignment.Center
                            ) {
                                if (index < 6) {
                                    Icon(Icons.Default.Check, contentDescription = null, tint = Color.White, modifier = Modifier.size(18.dp))
                                } else {
                                    Text("7", color = PurplePrimary, fontWeight = FontWeight.Bold, fontSize = 14.sp)
                                }
                            }
                            Spacer(modifier = Modifier.height(6.dp))
                            Text(dia, fontSize = 12.sp, color = TextDark, fontWeight = FontWeight.Bold)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun GeneralProgressCard() {
    // Componente sem o botão 'Ver evolução'
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier.size(60.dp),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.lvl_bg),
                    contentDescription = "Nível",
                    modifier = Modifier.fillMaxSize()
                )
                Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.padding(top = 4.dp)) {
                    Text("NÍVEL", color = Color.White, fontSize = 8.sp, fontWeight = FontWeight.Bold)
                    Text("12", color = Color.White, fontSize = 20.sp, fontWeight = FontWeight.ExtraBold, lineHeight = 20.sp)
                }
            }
            Spacer(modifier = Modifier.width(16.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(text = "Progresso geral", color = TextDark, fontWeight = FontWeight.Bold, fontSize = 14.sp)
                Spacer(modifier = Modifier.height(8.dp))

                // BARRA DE PROGRESSO CUSTOMIZADA
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(8.dp)
                        .clip(RoundedCornerShape(4.dp))
                        .background(PurpleLight)
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth(fraction = 0.625f)
                            .fillMaxHeight()
                            .clip(RoundedCornerShape(4.dp))
                            .background(PurplePrimary)
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))
                Row {
                    Text(text = "1.250 / 2.000 ", color = TextDark, fontSize = 12.sp, fontWeight = FontWeight.Bold)
                    Text(text = "XP", color = PurplePrimary, fontSize = 12.sp, fontWeight = FontWeight.ExtraBold)
                }
            }
        }
    }
}

@Composable
fun AchievementsSection() {
    Column(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("Conquistas", fontSize = 18.sp, fontWeight = FontWeight.Bold, color = TextDark)
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text("Ver todas", color = PurplePrimary, fontSize = 13.sp, fontWeight = FontWeight.Bold)
                Icon(Icons.Default.KeyboardArrowRight, contentDescription = null, tint = PurplePrimary, modifier = Modifier.size(16.dp))
            }
        }
        Spacer(modifier = Modifier.height(16.dp))

        // LazyRow para permitir arrastar para o lado se a tela for pequena
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            item { AchievementItem(title = "Investigador", subtitle = "Verificou 10\nconteúdos", date = "15/05/2024", color = PurplePrimary) }
            item { AchievementItem(title = "Protetor", subtitle = "Verificou 50\nconteúdos", date = "22/05/2024", color = Color(0xFF007AFF)) }
            item { AchievementItem(title = "Caçador", subtitle = "Verificou 100\nconteúdos", date = "02/06/2024", color = GreenSuccess) }
            item { AchievementItem(title = "Mestre", subtitle = "Verificou 500\nconteúdos", date = "20/06/2024", color = Color(0xFFFF9500)) }
            item { AchievementItem(title = "Guardião", subtitle = "Sequência de\n7 dias", date = "Hoje", color = PurplePrimary) }
        }
    }
}

@Composable
fun AchievementItem(title: String, subtitle: String, date: String, color: Color) {
    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.width(80.dp)) {
        // Placeholder do Hexágono da Conquista
        Box(
            modifier = Modifier
                .size(60.dp)
                .background(color.copy(alpha = 0.2f), RoundedCornerShape(16.dp)) // Substitua por sua imagem de conquista!
                .border(2.dp, color, RoundedCornerShape(16.dp)),
            contentAlignment = Alignment.Center
        ) {
            Icon(Icons.Default.Search, contentDescription = null, tint = color, modifier = Modifier.size(30.dp))

            // Simulação da bolinha verde de concluído
            Box(
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .offset(x = 6.dp, y = (-6).dp)
                    .size(20.dp)
                    .background(GreenSuccess, CircleShape)
                    .border(2.dp, Color.White, CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Icon(Icons.Default.Check, contentDescription = null, tint = Color.White, modifier = Modifier.size(12.dp))
            }
        }
        Spacer(modifier = Modifier.height(10.dp))
        Text(text = title, fontSize = 12.sp, fontWeight = FontWeight.Bold, color = TextDark)
        Spacer(modifier = Modifier.height(4.dp))
        Text(text = subtitle, fontSize = 10.sp, color = TextGray, textAlign = TextAlign.Center, lineHeight = 12.sp)
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = date, fontSize = 10.sp, color = TextGray.copy(alpha = 0.7f))
    }
}

@Composable
fun ProfileBottomNavigation() {
    NavigationBar(
        containerColor = Color.White,
        contentColor = TextGray,
        tonalElevation = 8.dp
    ) {
        NavigationBarItem(icon = { Icon(Icons.Default.Home, contentDescription = "Início") }, label = { Text("Início") }, selected = false, onClick = { })
        NavigationBarItem(icon = { Icon(Icons.Default.Build, contentDescription = "Missões") }, label = { Text("Missões") }, selected = false, onClick = { })
        NavigationBarItem(icon = { Icon(Icons.Default.Search, contentDescription = "Quiz") }, label = { Text("Quiz") }, selected = false, onClick = { })
        NavigationBarItem(icon = { Icon(Icons.Default.Star, contentDescription = "Recompensas") }, label = { Text("Recompensas") }, selected = false, onClick = { })

        // Perfil selecionado!
        NavigationBarItem(
            icon = { Icon(Icons.Default.Person, contentDescription = "Perfil") },
            label = { Text("Perfil") },
            selected = true,
            onClick = { },
            colors = NavigationBarItemDefaults.colors(selectedIconColor = PurplePrimary, selectedTextColor = PurplePrimary, indicatorColor = PurpleLight)
        )
    }
}