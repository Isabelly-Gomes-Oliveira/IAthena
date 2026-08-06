package com.example.iathena.screens

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.iathena.R
import com.example.iathena.ui.theme.NunitoFont
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(onTimeout: () -> Unit) {
    var progress by remember { mutableStateOf(0f) }
    val animatedProgress by animateFloatAsState(
        targetValue = progress,
        animationSpec = tween(durationMillis = 3500, easing = LinearEasing),
        label = "ProgressBarAnimation"
    )

    LaunchedEffect(Unit) {
        progress = 1f
        delay(3500)
        onTimeout()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF3E2898)),
        contentAlignment = Alignment.Center // Tudo aqui dentro parte do CENTRO exato da tela
    ) {

        // --- AS ESTRELAS (Agora usando Icon e posições fixas relativas ao centro) ---

        // Estrela 1 (Esquerda do logo)
        androidx.compose.material3.Icon(
            painter = painterResource(id = R.drawable.ic_estrela_brilho),
            contentDescription = null,
            tint = Color.White.copy(alpha = 0.8f),
            modifier = Modifier
                .offset(x = (-130).dp, y = (-40).dp) // Negativo vai pra esquerda/cima
                .size(16.dp)
        )

        // Estrela 2 (Direita, mais acima)
        androidx.compose.material3.Icon(
            painter = painterResource(id = R.drawable.ic_estrela_brilho),
            contentDescription = null,
            tint = Color.White.copy(alpha = 0.6f),
            modifier = Modifier
                .offset(x = 110.dp, y = (-160).dp) // Positivo vai pra direita/baixo
                .size(12.dp)
        )

        // Estrela 3 (Direita, mais abaixo)
        androidx.compose.material3.Icon(
            painter = painterResource(id = R.drawable.ic_estrela_brilho),
            contentDescription = null,
            tint = Color.White.copy(alpha = 0.9f),
            modifier = Modifier
                .offset(x = 140.dp, y = (-80).dp)
                .size(18.dp)
        )

        // Estrela 4 (Esquerda Alta)
        androidx.compose.material3.Icon(
            painter = painterResource(id = R.drawable.ic_estrela_brilho),
            contentDescription = null,
            tint = Color.White.copy(alpha = 0.9f),
            modifier = Modifier
                .offset(x = (-90).dp, y = (-200).dp)
                .size(20.dp)
        )

        // Estrela 5 (A roxinha lá embaixo)
        androidx.compose.material3.Icon(
            painter = painterResource(id = R.drawable.ic_estrela_brilho),
            contentDescription = null,
            tint = Color(0xFF886CF4).copy(alpha = 0.9f),
            modifier = Modifier
                .offset(x = 0.dp, y = 190.dp) // Exatamente no meio horizontal (x=0), mas para baixo (y=220)
                .size(30.dp)
        )


        // --- CONTEÚDO PRINCIPAL (Logo, Texto, Barrinha) ---
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize()
        ) {

            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(280.dp)
            ) {
                Box(
                    modifier = Modifier
                        .size(220.dp)
                        .offset(y = (-20).dp, x=(-15).dp)
                        .background(Color(0xFF4F3AA4), shape = CircleShape)
                )

                Image(
                    painter = painterResource(id = R.drawable.logo_iathena),
                    contentDescription = "Logo Coruja",
                    modifier = Modifier
                        .size(220.dp)
                        .offset(y = 25.dp)
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = buildAnnotatedString {
                    withStyle(style = SpanStyle(color = Color(0xFFAF9CF6))) {
                        append("IA")
                    }
                    withStyle(style = SpanStyle(color = Color.White)) {
                        append("thena")
                    }
                },
                fontSize = 64.sp,
                fontWeight = FontWeight.Normal,
                letterSpacing = 3.sp,
                fontFamily = NunitoFont
            )

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                HorizontalDivider(
                    modifier = Modifier.width(60.dp),
                    thickness = 2.dp,
                    color = Color(0xFF886CF4)
                )

                Spacer(modifier = Modifier.width(50.dp))

                HorizontalDivider(
                    modifier = Modifier.width(60.dp),
                    thickness = 2.dp,
                    color = Color(0xFF886CF4)
                )
            }

            Spacer(modifier = Modifier.height(34.dp))

            LinearProgressIndicator(
                progress = { animatedProgress },
                modifier = Modifier
                    .fillMaxWidth(0.5f)
                    .height(6.dp)
                    .clip(RoundedCornerShape(4.dp)),
                color = Color(0xFFAB98F2),
                trackColor = Color(0xFF332081)
            )
        }
    }
}
