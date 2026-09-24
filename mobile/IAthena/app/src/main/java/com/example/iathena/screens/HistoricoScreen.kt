// app/src/main/java/com/example/iathena/screens/HistoricoScreen.kt
package com.example.iathena.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.iathena.components.AppBottomNavigation
import com.example.iathena.data.AnalysisResult
import com.example.iathena.data.MockRepository
import com.example.iathena.data.StatusAnalise

@Composable
fun HistoricoScreen(onNavigate: (String) -> Unit, onNavigateToResult: (String) -> Unit) {
    val historico = MockRepository.historico

    Scaffold(
        bottomBar = { AppBottomNavigation(currentRoute = "historico", onNavigate = onNavigate) },
        containerColor = Color(0xFFFAFAFA)
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 20.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            item { Spacer(modifier = Modifier.height(10.dp)) }

            item {
                Text(
                    text = "Histórico de Análises",
                    fontSize = 28.sp,
                    fontWeight = FontWeight.ExtraBold,
                    color = Color(0xFF1C1C1C)
                )
                Text(
                    text = "Reveja tudo o que você já verificou.",
                    fontSize = 14.sp,
                    color = Color(0xFF8E8E93)
                )
                Spacer(modifier = Modifier.height(16.dp))
            }

            // Mapeando a lista do nosso MockRepository
            items(historico) { analise ->
                HistoryCardDynamic(
                    analise = analise,
                    onClick = { onNavigateToResult(analise.id) }
                )
            }

            item { Spacer(modifier = Modifier.height(20.dp)) }
        }
    }
}

@Composable
fun HistoryCardDynamic(analise: AnalysisResult, onClick: () -> Unit) {
    val statusColor = Color(analise.status.colorHex)
    val isSafe = analise.status == StatusAnalise.CONFIAVEL

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier.padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Miniatura
            Box(
                modifier = Modifier
                    .size(56.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .background(Color(0xFFE5E5EA))
            ) {
                Box(
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .offset(x = 6.dp, y = 6.dp)
                        .size(20.dp)
                        .background(statusColor, CircleShape)
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
                Text(analise.tituloCurto, fontWeight = FontWeight.Bold, fontSize = 14.sp, color = Color(0xFF1C1C1C), maxLines = 2)
                Spacer(modifier = Modifier.height(4.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(analise.status.label, color = statusColor, fontSize = 12.sp, fontWeight = FontWeight.Bold)
                    Text(" • ${analise.tempoAtras}", color = Color(0xFF8E8E93), fontSize = 12.sp)
                }
            }
            Spacer(modifier = Modifier.width(12.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text("${analise.porcentagem}%", color = statusColor, fontSize = 16.sp, fontWeight = FontWeight.ExtraBold)
                Spacer(modifier = Modifier.width(4.dp))
                Icon(Icons.Default.KeyboardArrowRight, contentDescription = "Ver mais", tint = Color(0xFF8E8E93))
            }
        }
    }
}