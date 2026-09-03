package com.example.iathena.screens

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
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
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.iathena.R
import com.example.iathena.components.LevelCard

// Cores do App baseadas na imagem
val PurplePrimary = Color(0xFF6A4CF4)
val PurpleDark = Color(0xFF5332D8)
val PurpleLight = Color(0xFFF4F0FF)
val GreenSuccess = Color(0xFF34C759)
val RedAlert = Color(0xFFFF3B30)
val YellowCoin = Color(0xFFFFD60A)
val BackgroundGray = Color(0xFFFAFAFA)
val TextDark = Color(0xFF1C1C1C)
val TextGray = Color(0xFF8E8E93)

@Composable
fun HomeScreen(onActivateOverlay: () -> Unit) { // <-- Parâmetro adicionado aqui
    Scaffold(
        bottomBar = { HomeBottomNavigation() },
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
            item { HomeHeader() }
            item { LevelCard(
                nivel = 12,
                titulo = "Guardião da Informação",
                xpAtual = 1250,
                xpMaximo = 2000,
                moedas = 2450
            ) }
            item { ScannerCard(onActivateOverlay = onActivateOverlay) } // <-- Parâmetro repassado aqui
            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Box(modifier = Modifier.weight(1.3f)) { ChallengesCard() }
                    Box(modifier = Modifier.weight(1f)) { StreakCard() }
                }
            }
            item { RecentVerifications() }
            item { Spacer(modifier = Modifier.height(20.dp)) }
        }
    }
}

@Composable
fun HomeHeader() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            // Imagem de Perfil (Coruja)
            Box(
                modifier = Modifier
                    .size(50.dp)
                    .clip(CircleShape)
                    .background(PurpleLight),
                contentAlignment = Alignment.Center
            ) {
                Icon(Icons.Default.Face, contentDescription = "Perfil", tint = PurplePrimary)
            }
            Spacer(modifier = Modifier.width(12.dp))
            Column {
                Text(
                    text = "Olá, Rafael! 👋",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.ExtraBold,
                    color = TextDark
                )
                Text(
                    text = "Pronto para proteger a verdade hoje?",
                    fontSize = 13.sp,
                    color = TextGray
                )
            }
        }
        // Ícone de Notificação
        Box(
            modifier = Modifier
                .size(45.dp)
                .background(Color.White, RoundedCornerShape(12.dp)),
            contentAlignment = Alignment.Center
        ) {
            Icon(Icons.Outlined.Notifications, contentDescription = "Notificações", tint = PurplePrimary)
        }
    }
}



@Composable
fun ScannerCard(onActivateOverlay: () -> Unit) { // <-- Parâmetro recebido aqui
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = PurpleLight)
    ) {
        Box(modifier = Modifier.fillMaxWidth()) {
            Column(modifier = Modifier.padding(20.dp)) {
                Text(
                    text = buildAnnotatedString {
                        append("Escaneie qualquer\ntexto e ")
                        withStyle(style = SpanStyle(color = PurplePrimary)) {
                            append("descubra\na verdade!")
                        }
                    },
                    fontSize = 22.sp,
                    fontWeight = FontWeight.ExtraBold,
                    lineHeight = 28.sp,
                    color = TextDark,
                    modifier = Modifier.fillMaxWidth(0.6f)
                )
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = "Use o ícone flutuante para\nanalisar conteúdos em\nqualquer aplicativo.",
                    fontSize = 13.sp,
                    color = TextGray,
                    modifier = Modifier.fillMaxWidth(0.6f)
                )
                Spacer(modifier = Modifier.height(20.dp))
                Button(
                    onClick = onActivateOverlay, // <-- Ação vinculada ao clique
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(55.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = PurplePrimary),
                    shape = RoundedCornerShape(16.dp)
                ) {
                    Icon(Icons.Default.Search, contentDescription = "Scan")
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Ativar agora", fontSize = 16.sp, fontWeight = FontWeight.Bold) // <-- Texto alterado
                }
            }
            // Espaço para a imagem da coruja com a lupa
            Box(
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(top = 40.dp, end = 25.dp)
                    .size(150.dp)
                    .background(Color.Transparent),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.img_owl),
                    contentDescription = "Owl with magnifying glass",
                    modifier = Modifier.size(130.dp)
                )
            }
        }
    }
}

@Composable
fun ChallengesCard() {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        // Reduzimos o padding geral de 16.dp para 12.dp para ganhar espaço
        Column(modifier = Modifier.padding(12.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.CheckCircle, contentDescription = "Alvo", tint = PurplePrimary, modifier = Modifier.size(16.dp))
                    Spacer(modifier = Modifier.width(6.dp))
                    Text("Desafios do dia", fontWeight = FontWeight.Bold, fontSize = 13.sp, color = TextDark)
                }
                Text("Ver todos", color = PurplePrimary, fontSize = 11.sp, fontWeight = FontWeight.Bold)
            }
            Spacer(modifier = Modifier.height(10.dp)) // Reduzido de 16.dp

            ChallengeItem(Icons.Default.Search, "Verifique 3 notícias", "Verifique 3 conteúdos hoje", "1 / 3", 0.33f, "+50")
            Divider(color = BackgroundGray, modifier = Modifier.padding(vertical = 4.dp)) // Reduzido de 8.dp
            ChallengeItem(Icons.Default.Check, "Faça o quiz diário", "Responda 5 perguntas", "0 / 5", 0f, "+40", iconColor = GreenSuccess)
            Divider(color = BackgroundGray, modifier = Modifier.padding(vertical = 4.dp)) // Reduzido de 8.dp
            ChallengeItem(Icons.Default.Share, "Compartilhe com amigos", "Convide 1 amigo para o app", "0 / 1", 0f, "+30", iconColor = Color(0xFFFFA000))
        }
    }
}

@Composable
fun ChallengeItem(icon: androidx.compose.ui.graphics.vector.ImageVector, title: String, subtitle: String, progressText: String, progress: Float, xp: String, iconColor: Color = PurplePrimary) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Box(
            modifier = Modifier
                .size(36.dp) // Ícone ligeiramente menor (era 36.dp)
                .background(iconColor.copy(alpha = 0.1f), CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Icon(icon, contentDescription = null, tint = iconColor, modifier = Modifier.size(16.dp))
        }
        Spacer(modifier = Modifier.width(8.dp))
        Column(modifier = Modifier.weight(1f)) {
            Text(title, fontWeight = FontWeight.Bold, fontSize = 11.sp, color = TextDark)
            Text(subtitle, fontSize = 9.sp, color = TextGray)
            Spacer(modifier = Modifier.height(2.dp)) // Reduzido de 4.dp
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(progressText, fontSize = 9.sp, color = TextDark, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.width(6.dp))
                LinearProgressIndicator(
                    progress = progress,
                    modifier = Modifier
                        .height(4.dp)
                        .weight(1f)
                        .clip(RoundedCornerShape(2.dp)),
                    color = GreenSuccess,
                    trackColor = BackgroundGray
                )
            }
        }
        Spacer(modifier = Modifier.width(6.dp))
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(xp, color = Color(0xFFFFA000), fontWeight = FontWeight.ExtraBold, fontSize = 12.sp)
            Box(
                modifier = Modifier
                    .background(PurplePrimary, RoundedCornerShape(4.dp))
                    .padding(horizontal = 4.dp, vertical = 2.dp)
            ) {
                Text("XP", color = Color.White, fontSize = 7.sp, fontWeight = FontWeight.Bold)
            }
        }
    }
}

@Composable
fun StreakCard() {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.Default.Warning, contentDescription = "Fogo", tint = Color(0xFFFF9500), modifier = Modifier.size(18.dp))
                Spacer(modifier = Modifier.width(6.dp))
                Text("Sequência de dias", fontWeight = FontWeight.Bold, fontSize = 14.sp, color = TextDark)
            }
            Spacer(modifier = Modifier.height(10.dp))
            Row(verticalAlignment = Alignment.Bottom) {
                Text("7", fontSize = 36.sp, fontWeight = FontWeight.ExtraBold, color = PurplePrimary)
                Spacer(modifier = Modifier.width(4.dp))
                Text("dias\nmandando bem! 🔥", fontSize = 10.sp, color = TextGray, lineHeight = 12.sp, modifier = Modifier.padding(bottom = 6.dp))
            }
            Spacer(modifier = Modifier.height(10.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                val dias = listOf("S", "T", "Q", "Q", "S", "S", "D")
                dias.forEachIndexed { index, dia ->
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Box(
                            modifier = Modifier
                                .size(20.dp)
                                .background(if (index < 6) GreenSuccess else Color.Transparent, CircleShape)
                                .border(1.dp, if (index < 6) Color.Transparent else Color.LightGray, CircleShape),
                            contentAlignment = Alignment.Center
                        ) {
                            if (index < 6) {
                                Icon(Icons.Default.Check, contentDescription = null, tint = Color.White, modifier = Modifier.size(12.dp))
                            }
                        }
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(dia, fontSize = 10.sp, color = TextGray, fontWeight = FontWeight.Bold)
                    }
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(containerColor = PurpleDark)
            ) {
                Row(
                    modifier = Modifier.padding(12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(Icons.Default.Star, contentDescription = "Estrela", tint = YellowCoin, modifier = Modifier.size(12.dp))
                            Spacer(modifier = Modifier.width(4.dp))
                            Text("Meta semanal", color = Color.White, fontSize = 10.sp, fontWeight = FontWeight.Bold)
                        }
                        Spacer(modifier = Modifier.height(2.dp))
                        Text("Verifique 10 conteúdos", color = Color.White.copy(alpha = 0.8f), fontSize = 8.sp)
                        Spacer(modifier = Modifier.height(6.dp))
                        LinearProgressIndicator(
                            progress = 0.6f,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(4.dp)
                                .clip(RoundedCornerShape(2.dp)),
                            color = GreenSuccess,
                            trackColor = Color(0xFF4A2CBA)
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text("6 / 10", color = Color.White, fontSize = 8.sp, fontWeight = FontWeight.Bold)
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    Icon(Icons.Default.Lock, contentDescription = "Baú", tint = YellowCoin, modifier = Modifier.size(30.dp))
                }
            }
        }
    }
}

@Composable
fun RecentVerifications() {
    Column(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("Verificações recentes", fontSize = 18.sp, fontWeight = FontWeight.Bold, color = TextDark)
            Text("Ver histórico", color = PurplePrimary, fontSize = 14.sp, fontWeight = FontWeight.Bold, modifier = Modifier.clickable { /* Navegar Histórico */ })
        }
        Spacer(modifier = Modifier.height(12.dp))

        HistoryCard(title = "“Chuvas fortes vão acabar com o verão esse ano”", status = "Confiável", time = "2h", percentage = "92%", isSafe = true)
        Spacer(modifier = Modifier.height(8.dp))
        HistoryCard(title = "“Novo golpe do WhatsApp rouba dados pelo link”", status = "Suspeito", time = "5h", percentage = "23%", isSafe = false)
        Spacer(modifier = Modifier.height(8.dp))
        HistoryCard(title = "“Laranja com bicarbonato cura doenças”", status = "Suspeito", time = "1d", percentage = "18%", isSafe = false)
    }
}

@Composable
fun HistoryCard(title: String, status: String, time: String, percentage: String, isSafe: Boolean) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { /* Navegar para Detalhes */ },
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Row(
            modifier = Modifier.padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(56.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .background(Color.LightGray)
            ) {
                // Aqui entraria a imagem da miniatura

                Box(
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .offset(x = 6.dp, y = 6.dp)
                        .size(20.dp)
                        .background(if (isSafe) GreenSuccess else RedAlert, CircleShape)
                        .border(2.dp, Color.White, CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        if (isSafe) Icons.Default.Check else Icons.Default.Warning,
                        contentDescription = null,
                        tint = Color.White,
                        modifier = Modifier.size(12.dp)
                    )
                }
            }
            Spacer(modifier = Modifier.width(16.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(title, fontWeight = FontWeight.Bold, fontSize = 14.sp, color = TextDark, maxLines = 2)
                Spacer(modifier = Modifier.height(4.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(status, color = if (isSafe) GreenSuccess else RedAlert, fontSize = 12.sp, fontWeight = FontWeight.Bold)
                    Text(" • analisado há $time", color = TextGray, fontSize = 12.sp)
                }
            }
            Spacer(modifier = Modifier.width(12.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(percentage, color = if (isSafe) GreenSuccess else RedAlert, fontSize = 16.sp, fontWeight = FontWeight.ExtraBold)
                Spacer(modifier = Modifier.width(4.dp))
                Icon(Icons.Default.KeyboardArrowRight, contentDescription = "Ver mais", tint = TextGray)
            }
        }
    }
}

@Composable
fun HomeBottomNavigation() {
    NavigationBar(
        containerColor = Color.White,
        contentColor = TextGray,
        tonalElevation = 8.dp
    ) {
        NavigationBarItem(icon = { Icon(Icons.Default.Home, contentDescription = "Início") }, label = { Text("Início") }, selected = true, onClick = { }, colors = NavigationBarItemDefaults.colors(selectedIconColor = PurplePrimary, selectedTextColor = PurplePrimary, indicatorColor = PurpleLight))
        NavigationBarItem(icon = { Icon(Icons.Default.Build, contentDescription = "Missões") }, label = { Text("Missões") }, selected = false, onClick = { })
        NavigationBarItem(icon = { Icon(Icons.Default.Search, contentDescription = "Quiz") }, label = { Text("Quiz") }, selected = false, onClick = { })
        NavigationBarItem(icon = { Icon(Icons.Default.Star, contentDescription = "Recompensas") }, label = { Text("Recompensas") }, selected = false, onClick = { })
        NavigationBarItem(icon = { Icon(Icons.Default.Person, contentDescription = "Perfil") }, label = { Text("Perfil") }, selected = false, onClick = { })
    }
}