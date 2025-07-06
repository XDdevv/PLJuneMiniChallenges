package zed.rainxch.pljuneminichallenges.birthday_celebration.presentation.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onPlaced
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import zed.rainxch.pljuneminichallenges.core.presentation.designsystem.ui.theme.maliFont

@Composable
fun BirthdayCelebrationButton(
    text: String,
    containerColor: Color,
    textColor: Color,
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = containerColor,
            contentColor = textColor
        ),
        contentPadding = PaddingValues(
            horizontal = 28.dp,
            vertical = 12.dp
        ),
        modifier = modifier
    ) {
        Text(
            text = text,
            fontSize = 24.sp,
            fontFamily = maliFont,
            fontWeight = FontWeight.SemiBold
        )
    }
}