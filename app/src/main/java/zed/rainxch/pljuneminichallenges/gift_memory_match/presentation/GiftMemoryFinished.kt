package zed.rainxch.pljuneminichallenges.gift_memory_match.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.DialogProperties
import zed.rainxch.pljuneminichallenges.R
import zed.rainxch.pljuneminichallenges.birthday_celebration.presentation.components.BirthdayCelebrationButton
import zed.rainxch.pljuneminichallenges.core.presentation.designsystem.ui.theme.GiftMemoryCatchColors
import zed.rainxch.pljuneminichallenges.core.presentation.designsystem.ui.theme.maliFont
import zed.rainxch.pljuneminichallenges.core.presentation.designsystem.ui.theme.toColorX

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GiftMemoryFinishedDialog(
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier,
) {
    BasicAlertDialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(
            dismissOnBackPress = false,
            dismissOnClickOutside = false,
            usePlatformDefaultWidth = false
        ),
        modifier = modifier
            .fillMaxWidth()
            .height(480.dp)
            .padding(horizontal = 16.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(GiftMemoryCatchColors.SURFACE.toColorX())
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Party on!",
                color = GiftMemoryCatchColors.ON_SURFACE.toColorX(),
                fontFamily = maliFont,
                fontWeight = FontWeight.Bold,
                fontSize = 36.sp
            )

            Text(
                text = "All gifts matched!",
                color = GiftMemoryCatchColors.ON_SURFACE.toColorX(),
                fontFamily = maliFont,
                fontWeight = FontWeight.Medium,
                fontSize = 21.sp
            )

            Spacer(Modifier.height(32.dp))

            BirthdayCelebrationButton(
                text = "Try again!",
                containerColor = GiftMemoryCatchColors.SURFACE_HIGHER.toColorX(),
                textColor = GiftMemoryCatchColors.ON_SURFACE.toColorX()
            ) {
                onDismiss()
            }
        }

        Image(
            painter = painterResource(R.drawable.decor),
            contentDescription = null,
            contentScale = ContentScale.Fit
        )
    }
}

@Preview
@Composable
private fun GiftMemoryFinishedPreview() {
    GiftMemoryFinishedDialog(
        {},
    )
}