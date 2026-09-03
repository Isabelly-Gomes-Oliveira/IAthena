package com.example.iathena.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
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
import java.text.NumberFormat
import java.util.Locale

@Composable
fun LevelCard(
    nivel: Int,
    titulo: String,
    xpAtual: Int,
    xpMaximo: Int,
    moedas: Int,
    modifier: Modifier = Modifier
) {
    // Cores extraídas diretamente para o componente manter a independência
    val PurpleDark = Color(0xFF5332D8)
    val GreenSuccess = Color(0xFF34C759)

    // Cálculo automático da barra de progresso (proteção contra divisão por zero)
    val progress = if (xpMaximo > 0) xpAtual.toFloat() / xpMaximo.toFloat() else 0f

    // Formatador para colocar o ponto nas casas de milhar (ex: 1250 vira "1.250")
    val formatter = NumberFormat.getInstance(Locale("pt", "BR"))
    val xpAtualFormatado = formatter.format(xpAtual)
    val xpMaximoFormatado = formatter.format(xpMaximo)
    val moedasFormatadas = formatter.format(moedas)

    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = PurpleDark)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Hexágono do Nível feito com Imagem
            Box(
                modifier = Modifier.size(70.dp),
                contentAlignment = Alignment.Center
            ) {
                // Lembre-se de colocar a imagem correta na sua pasta res/drawable
                Image(
                    painter = painterResource(id = R.drawable.lvl_bg),
                    contentDescription = "Fundo do Nível",
                    modifier = Modifier.fillMaxSize()
                )

                // Como a palavra NÍVEL já está na imagem, mantemos apenas o número
                // Usei um padding top pequeno pois a palavra "NÍVEL" geralmente empurra o número um pouco para baixo
                Text(
                    text = nivel.toString(),
                    color = Color.White,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.ExtraBold,
                    modifier = Modifier.padding(top = 8.dp)
                )
            }
            Spacer(modifier = Modifier.width(16.dp))

            // Informações de XP Dinâmicas
            Column(modifier = Modifier.weight(1f)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(text = titulo, color = Color.White, fontWeight = FontWeight.Bold, fontSize = 14.sp)

                }
                Spacer(modifier = Modifier.height(8.dp))
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(8.dp)
                        .clip(RoundedCornerShape(10.dp))
                        .background(Color(0xFF4A2CBA)) // Cor de fundo da barra (Trilha)
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth(fraction = progress) // Enche a barra baseada na fração (0.0 a 1.0)
                            .fillMaxHeight()
                            .clip(RoundedCornerShape(10.dp))
                            .background(GreenSuccess) // Cor verde do progresso
                    )
                }
                Spacer(modifier = Modifier.height(6.dp))
                Row {
                    Text(text = "$xpAtualFormatado / $xpMaximoFormatado ", color = Color.White, fontSize = 12.sp, fontWeight = FontWeight.Bold)
                    Text(text = "XP", color = GreenSuccess, fontSize = 12.sp, fontWeight = FontWeight.ExtraBold)
                }
            }

            // Divisor
            Divider(
                color = Color.White.copy(alpha = 0.2f),
                modifier = Modifier
                    .height(50.dp)
                    .width(1.dp)
                    .padding(horizontal = 12.dp)
            )

            // Moedas Dinâmicas com Imagem
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    // Lembre-se de colocar a imagem da moeda na pasta res/drawable
                    Image(
                        painter = painterResource(id = R.drawable.coin_icon),
                        contentDescription = "Moeda",
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(text = moedasFormatadas, color = Color.White, fontWeight = FontWeight.ExtraBold, fontSize = 18.sp)
                }
                Text(text = "Moedas", color = Color.White.copy(alpha = 0.8f), fontSize = 12.sp)
            }
        }
    }
}