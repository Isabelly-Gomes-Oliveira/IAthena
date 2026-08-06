package com.example.iathena.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.iathena.components.Indicador
import com.example.iathena.components.MessageCard
import com.example.iathena.components.ProgressHeader
import com.example.iathena.components.TutorialCard

@Composable
fun TutorialScreen(onComecar: () -> Unit) { // Parâmetro injetado pelo Navigation!

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(20.dp)
    ) {
        item {
            ProgressHeader()

            Spacer(modifier = Modifier.height(25.dp))

            Text(
                text = "Bem-vindo\nao IAthena!",
                fontSize = 34.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = "Aprenda rapidinho como usar o app e se tornar um mestre da informação!",
                fontSize = 18.sp,
                color = Color.Gray
            )

            Spacer(modifier = Modifier.height(25.dp))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(170.dp),
                contentAlignment = Alignment.Center
            ) {
                Box(
                    Modifier
                        .size(140.dp)
                        .background(
                            Color(0xFFE7E0FF),
                            RoundedCornerShape(20.dp)
                        )
                )
                Text("Imagem da Coruja")
            }

            Spacer(modifier = Modifier.height(25.dp))

            TutorialCard(
                numero = "1",
                titulo = "Ative o ícone flutuante",
                descricao = "Ative o ícone flutuante nas configurações para que ele fique sempre sobre as telas."
            )

            TutorialCard(
                numero = "2",
                titulo = "Escaneie qualquer texto",
                descricao = "Toque no ícone e escolha 'Escanear tela'. A IA irá ler e analisar o conteúdo."
            )

            TutorialCard(
                numero = "3",
                titulo = "Ganhe XP e moedas",
                descricao = "Cada verificação correta, missão concluída ou quiz te dá XP e moedas!"
            )

            TutorialCard(
                numero = "4",
                titulo = "Identifique fake news",
                descricao = "A IA mostra se o conteúdo é confiável ou suspeito e explica o porquê."
            )

            Spacer(modifier = Modifier.height(18.dp))

            MessageCard()

            Spacer(modifier = Modifier.height(20.dp))

            Button(
                onClick = onComecar, // O clique agora avança para a HomeScreen!
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp),
                shape = RoundedCornerShape(18.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF6A4CF4)
                )
            ) {
                Text(
                    "Vamos começar!",
                    fontSize = 22.sp
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            Indicador()

            Spacer(modifier = Modifier.height(30.dp))
        }
    }
}