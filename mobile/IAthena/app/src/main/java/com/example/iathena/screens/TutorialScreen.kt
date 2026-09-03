package com.example.iathena.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.iathena.components.Indicador
import com.example.iathena.components.MessageCard
import com.example.iathena.components.ProgressHeader
import com.example.iathena.components.TutorialCard
import kotlinx.coroutines.launch
import androidx.compose.foundation.Image
import androidx.compose.ui.res.painterResource
import com.example.iathena.R
import com.example.iathena.components.IathenaButton

@Composable
fun TutorialScreen(onComecar: () -> Unit) {
    // Gerencia o estado do paginador (5 páginas no total)
    val pagerState = rememberPagerState(pageCount = { 5 })
    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(top = 20.dp, start = 20.dp, end = 20.dp)
    ) {
        // Cabeçalho com as barras e o botão pular
        ProgressHeader(
            currentPage = pagerState.currentPage,
            totalPages = 5,
            onPageSelected = { page ->
                coroutineScope.launch { pagerState.animateScrollToPage(page) }
            },
            onSkip = onComecar // Agora o "Pular" realmente fecha o tutorial!
        )

        Spacer(modifier = Modifier.height(15.dp))

        // Paginador horizontal (permite deslizar pro lado)
        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
        ) { page ->
            when (page) {
                0 -> ResumoGeralPage()
                1 -> DetalheTopicoPage(
                    img = R.drawable.img1_nobg,
                    size = 150,
                    titulo = "Ative o ícone flutuante",
                    descricao = "Ative o ícone flutuante nas configurações para que ele fique sempre sobre as telas do seu celular."
                )
                2 -> DetalheTopicoPage(
                    img = R.drawable.img2_nobg,
                    size = 150,
                    titulo = "Escaneie qualquer texto",
                    descricao = "Toque no ícone flutuante e escolha 'Escanear tela'. A Inteligência Artificial irá ler e analisar o conteúdo instantaneamente."
                )
                3 -> DetalheTopicoPage(
                    img = R.drawable.img3_nobg,
                    size = 180,
                    titulo = "Ganhe XP e moedas",
                    descricao = "Você não está só checando fatos, está jogando! Cada verificação correta, missão concluída ou quiz te dá XP e moedas."
                )
                4 -> DetalheTopicoPage(
                    img = R.drawable.img4_nobg,
                    size = 180,
                    titulo = "Identifique fake news",
                    descricao = "A IA mostra se o conteúdo é confiável ou suspeito, explicando o porquê de forma simples e direta.",
                    mostrarBotaoFinal = true,
                    onComecar = onComecar
                )
            }
        }

        // Bolinhas indicadoras no rodapé
        Indicador(
            currentPage = pagerState.currentPage,
            totalPages = 5,
            onPageSelected = { page ->
                coroutineScope.launch { pagerState.animateScrollToPage(page) }
            }
        )
        Spacer(modifier = Modifier.height(20.dp))
    }
}

@Composable
fun ResumoGeralPage() {
    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        item {
            Text(
                text = "Bem-vindo\nao IAthena!",
                fontSize = 34.sp,
                fontWeight = FontWeight.Bold,
                lineHeight = 40.sp
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
                Image(
                    painter = painterResource(id = R.drawable.img_owl_tut),
                    contentDescription = "Coruja",
                    modifier = Modifier.size(180.dp)
                )
            }
            Spacer(modifier = Modifier.height(25.dp))
            TutorialCard(
                numero = "1",
                titulo = "Ative o ícone flutuante",
                descricao = "Ative o ícone flutuante nas configurações para que ele fique sempre sobre as telas.",
                img = R.drawable.img1_nobg
            )
            TutorialCard(
                numero = "2",
                titulo = "Escaneie qualquer texto",
                descricao = "Toque no ícone e escolha 'Escanear tela'. A IA irá ler e analisar o conteúdo.",
                img = R.drawable.img2
            )
            TutorialCard(
                numero = "3",
                titulo = "Ganhe XP e moedas",
                descricao = "Cada verificação correta, missão concluída ou quiz te dá XP e moedas!",
                img = R.drawable.img3
            )
            TutorialCard(
                numero = "4",
                titulo = "Identifique fake news",
                descricao = "A IA mostra se o conteúdo é confiável ou suspeito e explica o porquê.",
                img = R.drawable.img4
            )
            Spacer(modifier = Modifier.height(20.dp))
        }
    }
}

@Composable
fun DetalheTopicoPage(
    img: Int,
    size: Int = 90,
    titulo: String,
    descricao: String,
    mostrarBotaoFinal: Boolean = false,
    onComecar: () -> Unit = {}
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Box(
            modifier = Modifier
                .size(180.dp)
                .background(Color(0xFFEAE4FF), RoundedCornerShape(100.dp)),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = img),
                contentDescription = null,
                modifier = Modifier.size(size.dp)
            )
        }
        Spacer(modifier = Modifier.height(40.dp))
        Text(
            text = titulo,
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = descricao,
            fontSize = 18.sp,
            color = Color.Gray,
            textAlign = TextAlign.Center
        )

        if (mostrarBotaoFinal) {
            Spacer(modifier = Modifier.height(40.dp))
            MessageCard()
            Spacer(modifier = Modifier.height(30.dp))
            IathenaButton(onComecar,"Vamos começar!")
        }
    }
}