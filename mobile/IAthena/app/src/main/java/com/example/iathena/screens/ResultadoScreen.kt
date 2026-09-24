// app/src/main/java/com/example/iathena/screens/ResultadoScreen.kt
package com.example.iathena.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.iathena.data.MockRepository
import com.example.iathena.data.StatusAnalise

// Cores fieis ao design
private val BgGray = Color(0xFFF8F9FA)
private val RedDanger = Color(0xFFF04454)
private val RedLight = Color(0xFFFDECEE)
private val GoldCoin = Color(0xFFFFC107)

@Composable
fun ResultadoScreen(analiseId: String, onNavigateBack: () -> Unit) {
    // Busca os dados baseados no ID passado
    val analise = MockRepository.getResultById(analiseId)

    // Se por algum motivo o ID não for encontrado, mostra tela vazia ou erro
    if (analise == null) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text("Resultado não encontrado.")
        }
        return
    }

    // Cores Dinâmicas
    val statusColor = Color(analise.status.colorHex)
    val statusBgColor = statusColor.copy(alpha = 0.1f)
    val isSafe = analise.status == StatusAnalise.CONFIAVEL
    val statusIcon = if (isSafe) Icons.Default.CheckCircle else Icons.Default.Warning

    Scaffold(
        containerColor = Color(0xFFF8F9FA),
        topBar = {
            // CABEÇALHO
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 20.dp),
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
                Text(
                    text = "Resultado da análise",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = TextDark
                )
                Spacer(modifier = Modifier.weight(1f))
                Spacer(modifier = Modifier.size(40.dp)) // Para balancear o título no centro
            }
        },
        bottomBar = {
            // BARRA DE RECOMPENSA (Fixa no final)
            Surface(
                color = Color.White,
                shadowElevation = 8.dp,
                shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp, vertical = 16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Badge XP
                    Surface(
                        shape = RoundedCornerShape(12.dp),
                        color = PurpleLight
                    ) {
                        Column(
                            modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text("+50", fontSize = 14.sp, fontWeight = FontWeight.ExtraBold, color = PurplePrimary)
                            Text("XP", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = PurplePrimary)
                        }
                    }

                    Spacer(modifier = Modifier.width(12.dp))

                    // Textos e Barra de Progresso
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = "Você ganhou 50 XP por verificar esta informação!",
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Medium,
                            color = TextDark,
                            lineHeight = 14.sp
                        )
                        Spacer(modifier = Modifier.height(6.dp))
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(6.dp)
                                .background(Color(0xFFE5E5EA), CircleShape)
                        ) {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth(0.65f)
                                    .fillMaxHeight()
                                    .background(GreenSuccess, CircleShape)
                            )
                        }
                        Spacer(modifier = Modifier.height(4.dp))
                        Text("1.300 / 2.000 XP", fontSize = 10.sp, color = TextGray, fontWeight = FontWeight.Bold)
                    }

                    Spacer(modifier = Modifier.width(12.dp))

                    // Badge Moedas
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Icon(Icons.Default.Star, contentDescription = "Moedas", tint = GoldCoin, modifier = Modifier.size(24.dp))
                        Text("+20", fontSize = 14.sp, fontWeight = FontWeight.ExtraBold, color = TextDark)
                        Text("Moedas", fontSize = 10.sp, color = TextGray)
                    }
                }
            }
        }
    ) { paddingValues ->
        LazyColumn(modifier = Modifier.padding(paddingValues).padding(horizontal = 16.dp)) {
            item {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(24.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White)
                ) {
                    Column(modifier = Modifier.padding(20.dp)) {

                        // 1. BLOCO DE VEREDITO DINÂMICO
                        Surface(shape = RoundedCornerShape(20.dp), color = statusBgColor) {
                            Row(modifier = Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
                                // Placeholder Coruja
                                Box(modifier = Modifier.size(80.dp).background(Color.White, CircleShape))
                                Spacer(modifier = Modifier.width(16.dp))

                                Column {
                                    Row(verticalAlignment = Alignment.CenterVertically) {
                                        Icon(statusIcon, contentDescription = null, tint = statusColor, modifier = Modifier.size(20.dp))
                                        Spacer(modifier = Modifier.width(6.dp))
                                        Text(analise.status.label, fontSize = 18.sp, fontWeight = FontWeight.ExtraBold, color = statusColor)
                                    }
                                    Spacer(modifier = Modifier.height(6.dp))
                                    Text(analise.explicacaoIa, fontSize = 12.sp, color = Color(0xFF1C1C1C), lineHeight = 16.sp, maxLines = 2)
                                    Spacer(modifier = Modifier.height(12.dp))

                                    // Confiabilidade Barra
                                    Row(
                                        modifier = Modifier.fillMaxWidth(),
                                        horizontalArrangement = Arrangement.SpaceBetween,
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Text("Confiabilidade", fontSize = 12.sp, fontWeight = FontWeight.Bold)
                                        Text("${analise.porcentagem}%", fontSize = 16.sp, fontWeight = FontWeight.ExtraBold, color = statusColor)
                                    }
                                    Spacer(modifier = Modifier.height(4.dp))
                                    Box(modifier = Modifier.fillMaxWidth().height(6.dp).background(Color(0xFFE5E5EA), CircleShape)) {
                                        Box(modifier = Modifier.fillMaxWidth(analise.porcentagem / 100f).fillMaxHeight().background(statusColor, CircleShape))
                                    }
                                }
                            }
                        }

                        Spacer(modifier = Modifier.height(24.dp))

                        // 2. TEXTO ANALISADO
                        Text("Texto analisado", fontSize = 14.sp, fontWeight = FontWeight.Bold, color = Color(0xFF6A4CF4))
                        Spacer(modifier = Modifier.height(8.dp))
                        Surface(shape = RoundedCornerShape(12.dp), color = Color(0xFFF8F9FA), modifier = Modifier.fillMaxWidth()) {
                            Text(analise.textoAnalisado, fontSize = 14.sp, modifier = Modifier.padding(16.dp))
                        }

                        Spacer(modifier = Modifier.height(24.dp))

                        // 3. EXPLICAÇÃO (MOTIVOS)
                        // Repete usando um forEach nos motivos da nossa classe
                        Text("Explicação da IA", fontSize = 16.sp, fontWeight = FontWeight.Bold, color = Color(0xFF6A4CF4))
                        Spacer(modifier = Modifier.height(12.dp))
                        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                            analise.motivos.forEach { motivo ->
                                MotivoItem(Icons.Default.Info, motivo) // Você pode customizar o ícone depois
                            }
                        }

                        Spacer(modifier = Modifier.height(24.dp))

                        // 4. FONTES VERIFICADAS
                        Text("Fontes verificadas", fontSize = 16.sp, fontWeight = FontWeight.Bold, color = Color(0xFF6A4CF4))
                        Spacer(modifier = Modifier.height(12.dp))
                        analise.fontes.forEachIndexed { index, fonte ->
                            FonteItem(
                                nome = fonte.nome,
                                url = fonte.url,
                                logoBgColor = Color(fonte.logoBgHex),
                                logoText = fonte.logoText,
                                logoIconColor = Color.White
                            )
                            if (index < analise.fontes.size - 1) {
                                HorizontalDivider(color = Color(0xFFF8F9FA), modifier = Modifier.padding(vertical = 8.dp))
                            }
                        }
                    }
                }
            }
        }
    }
}

// COMPONENTES AUXILIARES

@Composable
fun MotivoItem(icon: ImageVector, text: String) {
    Surface(
        shape = RoundedCornerShape(12.dp),
        border = androidx.compose.foundation.BorderStroke(1.dp, BgGray),
        color = Color.Transparent,
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(icon, contentDescription = null, tint = RedDanger, modifier = Modifier.size(16.dp))
            Spacer(modifier = Modifier.width(12.dp))
            Text(text, fontSize = 13.sp, color = TextDark)
        }
    }
}

@Composable
fun FonteItem(nome: String, url: String, logoBgColor: Color, logoText: String, logoIconColor: Color = TextDark) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Logo Placeholder
        Box(
            modifier = Modifier.size(36.dp).background(logoBgColor, CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Text(logoText, color = logoIconColor, fontSize = 10.sp, fontWeight = FontWeight.Bold)
        }

        Spacer(modifier = Modifier.width(12.dp))

        Column(modifier = Modifier.weight(1f)) {
            Text(nome, fontSize = 13.sp, fontWeight = FontWeight.Bold, color = TextDark)
            Text(url, fontSize = 11.sp, color = TextGray)
        }

        Text("Não encontrou evidências", fontSize = 11.sp, color = GreenSuccess, fontWeight = FontWeight.Medium)
        Spacer(modifier = Modifier.width(8.dp))
        Icon(Icons.Default.ExitToApp, contentDescription = "Abrir Link", tint = PurplePrimary, modifier = Modifier.size(18.dp))
    }
}