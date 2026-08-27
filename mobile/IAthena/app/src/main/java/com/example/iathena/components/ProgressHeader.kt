package com.example.iathena.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun ProgressHeader(
    currentPage: Int,
    totalPages: Int,
    onPageSelected: (Int) -> Unit,
    onSkip: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row {
            repeat(totalPages) { page ->
                Box(
                    modifier = Modifier
                        .padding(end = 8.dp)
                        .width(42.dp)
                        .height(8.dp)
                        .clip(RoundedCornerShape(10.dp))
                        .background(
                            // Se a página já passou ou é a atual, fica roxa
                            if (page <= currentPage) Color(0xFF6A4CF4) else Color(0xFFE7E0FF)
                        )
                        .clickable { onPageSelected(page) }
                )
            }
        }
        Text(
            text = "Pular",
            color = Color(0xFF6A4CF4),
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .clip(RoundedCornerShape(8.dp))
                .clickable { onSkip() }
                .padding(8.dp) // Área de clique um pouco maior
        )
    }
}