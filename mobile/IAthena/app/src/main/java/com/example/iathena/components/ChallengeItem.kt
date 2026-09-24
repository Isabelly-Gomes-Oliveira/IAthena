import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.iathena.screens.BackgroundGray
import com.example.iathena.screens.GreenSuccess
import com.example.iathena.screens.PurplePrimary
import com.example.iathena.screens.TextDark

@Composable
fun ChallengeItem(icon: androidx.compose.ui.graphics.vector.ImageVector, title: String, progressText: String, progress: Float, xp: String, iconColor: Color = PurplePrimary) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Box(
            modifier = Modifier
                .size(36.dp)
                .background(iconColor.copy(alpha = 0.1f), CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Icon(icon, contentDescription = null, tint = iconColor, modifier = Modifier.size(16.dp))
        }
        Spacer(modifier = Modifier.width(8.dp))
        Column(modifier = Modifier.weight(1f)) {
            Text(title, fontWeight = FontWeight.Bold, fontSize = 11.sp, lineHeight = 12.sp, color = TextDark)
            Spacer(modifier = Modifier.height(4.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(progressText, fontSize = 9.sp, color = TextDark, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.width(6.dp))

                // BARRA DE PROGRESSO CUSTOMIZADA (Sem bolinha/falha)
                Box(
                    modifier = Modifier
                        .height(4.dp)
                        .weight(1f)
                        .clip(RoundedCornerShape(2.dp))
                        .background(BackgroundGray)
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth(fraction = progress)
                            .fillMaxHeight()
                            .clip(RoundedCornerShape(2.dp))
                            .background(GreenSuccess)
                    )
                }

            }
        }
        Spacer(modifier = Modifier.width(6.dp))
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(xp, color = Color(0xFFFFA000), fontWeight = FontWeight.ExtraBold, fontSize = 12.sp)
            Box(
                modifier = Modifier
                    .background(PurplePrimary, RoundedCornerShape(4.dp))
                    .padding(horizontal = 6.dp, vertical = 1.dp)
            ) {
                Text("XP", color = Color.White, fontSize = 9.sp, fontWeight = FontWeight.Bold)
            }
        }
    }
}
