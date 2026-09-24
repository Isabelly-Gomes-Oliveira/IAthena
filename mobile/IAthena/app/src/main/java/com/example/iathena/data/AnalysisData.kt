// app/src/main/java/com/example/iathena/data/AnalysisData.kt
package com.example.iathena.data

import androidx.compose.ui.graphics.Color

enum class StatusAnalise(val label: String, val colorHex: Long) {
    CONFIAVEL("Confiável", 0xFF34C759), // Verde
    SUSPEITO("Possível fake news", 0xFFF04454), // Vermelho
    INCONCLUSIVO("Inconclusivo", 0xFFFFC107) // Amarelo
}

data class Fonte(
    val nome: String,
    val url: String,
    val status: String,
    val logoText: String,
    val logoBgHex: Long
)

data class AnalysisResult(
    val id: String,
    val tituloCurto: String,
    val textoAnalisado: String,
    val status: StatusAnalise,
    val porcentagem: Int,
    val tempoAtras: String,
    val explicacaoIa: String,
    val motivos: List<String>,
    val fontes: List<Fonte>
)

// DADOS FALSOS PARA TESTARMOS
object MockRepository {
    val historico = listOf(
        AnalysisResult(
            id = "1",
            tituloCurto = "“Novo golpe do WhatsApp rouba dados pelo link”",
            textoAnalisado = "Novo golpe do WhatsApp rouba dados pelo link que promete mostrar quem visitou seu perfil.",
            status = StatusAnalise.SUSPEITO,
            porcentagem = 23,
            tempoAtras = "5h",
            explicacaoIa = "O conteúdo apresenta características comuns de fake news: senso de urgência, promessa de benefício, falta de fontes confiáveis e linguagem alarmista.",
            motivos = listOf(
                "Uso de gatilhos de urgência (\"novo golpe\")",
                "Promessa de algo que não existe oficialmente",
                "Linguagem alarmista e apelativa",
                "Não há fontes ou dados concretos"
            ),
            fontes = listOf(
                Fonte("Agência Lupa", "lupa.uol.com.br", "Não encontrou evidências", "Lupa", 0xFFFFD60A),
                Fonte("Agência Aos Fatos", "aosfatos.org", "Não encontrou evidências", "Aos", 0xFF1C1C1C),
                Fonte("Manual do WhatsApp", "faq.whatsapp.com", "Não encontrou evidências", "W", 0xFF25D366)
            )
        ),
        AnalysisResult(
            id = "2",
            tituloCurto = "“Chuvas fortes vão acabar com o verão esse ano”",
            textoAnalisado = "Previsão do tempo indica que fortes massas de ar polar trarão chuvas constantes, anulando os dias de sol do verão na região sudeste.",
            status = StatusAnalise.CONFIAVEL,
            porcentagem = 92,
            tempoAtras = "2h",
            explicacaoIa = "A notícia está alinhada com os boletins meteorológicos oficiais recentes do INMET e outras agências climáticas.",
            motivos = listOf(
                "Citação de órgãos oficiais (INMET)",
                "Linguagem jornalística padrão",
                "Dados climáticos condizentes"
            ),
            fontes = listOf(
                Fonte("INMET", "portal.inmet.gov.br", "Confirmou a previsão", "IN", 0xFF007AFF),
                Fonte("Climatempo", "climatempo.com.br", "Confirmou a previsão", "C", 0xFFFF9500)
            )
        ),
        AnalysisResult(
            id = "3",
            tituloCurto = "“Laranja com bicarbonato cura doenças”",
            textoAnalisado = "Misture suco de meia laranja com uma colher de bicarbonato de sódio para curar instantaneamente dores crônicas e limpar o fígado.",
            status = StatusAnalise.SUSPEITO,
            porcentagem = 18,
            tempoAtras = "1d",
            explicacaoIa = "Trata-se de uma receita caseira sem comprovação científica que circula frequentemente em correntes de redes sociais.",
            motivos = listOf(
                "Promessa de cura milagrosa",
                "Falta de embasamento científico",
                "Corrente típica de WhatsApp"
            ),
            fontes = listOf(
                Fonte("Ministério da Saúde", "saude.gov.br", "Desmentiu o boato", "MS", 0xFF00B050)
            )
        )
    )

    fun getResultById(id: String): AnalysisResult? {
        return historico.find { it.id == id }
    }
}