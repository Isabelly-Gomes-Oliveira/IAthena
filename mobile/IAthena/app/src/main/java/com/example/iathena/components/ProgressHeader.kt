package com.example.iathena.components

import androidx.compose.foundation.background
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
fun ProgressHeader() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row {
            repeat(5) {
                Box(
                    modifier = Modifier
                        .padding(end = 8.dp)
                        .width(42.dp)
                        .height(8.dp)
                        .clip(RoundedCornerShape(10.dp))
                        .background(
                            if (it == 0) Color(0xFF6A4CF4) else Color(0xFFE7E0FF)
                        )
                )
            }
        }
        Text(
            "Pular",
            color = Color(0xFF6A4CF4),
            fontWeight = FontWeight.Bold
        )
    }
}