package zed.rainxch.pljuneminichallenges.gift_memory_match.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import zed.rainxch.pljuneminichallenges.core.presentation.designsystem.ui.theme.GiftMemoryCatchColors
import zed.rainxch.pljuneminichallenges.core.presentation.designsystem.ui.theme.maliFont
import zed.rainxch.pljuneminichallenges.core.presentation.designsystem.ui.theme.toColorX

@Composable
fun GiftMemoryButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Transparent
        ),
        modifier = modifier
            .background(
                color = GiftMemoryCatchColors.SURFACE_HIGHER.toColorX(),
                shape = CircleShape
            )
            .padding(vertical = 8.dp, horizontal = 8.dp)
    ) {
        Text(
            text = text,
            fontFamily = maliFont,
            fontWeight = FontWeight.SemiBold,
            fontSize = 24.sp,
            color = GiftMemoryCatchColors.ON_SURFACE.toColorX()
        )
    }
}