package zed.rainxch.pljuneminichallenges.cake_lighting_controller.presentation.components

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import zed.rainxch.pljuneminichallenges.core.presentation.designsystem.ui.theme.CandleChallengeColors
import zed.rainxch.pljuneminichallenges.core.presentation.designsystem.ui.theme.maliFont
import zed.rainxch.pljuneminichallenges.core.presentation.designsystem.ui.theme.toColorX

@Composable
fun CandleChallengeButton(
    modifier: Modifier = Modifier,
    text: String = "Light all candles",
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        modifier = modifier
            .height(60.dp)
            .shadow(
                elevation = 4.dp,
                shape = CircleShape,
                clip = true,
                ambientColor = CandleChallengeColors.ON_SURFACE.toColorX().copy(alpha = .32f),
                spotColor = CandleChallengeColors.ON_SURFACE.toColorX().copy(alpha = .32f),
            ),
        shape = CircleShape,
        colors = ButtonDefaults.buttonColors(
            containerColor = CandleChallengeColors.SURFACE_HIGHER.toColorX(),
            contentColor = CandleChallengeColors.ON_SURFACE.toColorX()
        ),
        elevation = ButtonDefaults.elevatedButtonElevation(
            defaultElevation = 0.dp
        )
    ) {
        Text(
            text = text,
            fontSize = 24.sp,
            fontFamily = maliFont,
            fontWeight = FontWeight.SemiBold
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun CandleChallengeButtonPreview() {
    CandleChallengeButton {}
}