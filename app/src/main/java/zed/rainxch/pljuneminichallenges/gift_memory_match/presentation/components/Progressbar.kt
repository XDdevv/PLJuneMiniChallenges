package zed.rainxch.pljuneminichallenges.gift_memory_match.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import zed.rainxch.pljuneminichallenges.core.presentation.designsystem.ui.theme.GiftMemoryCatchColors
import zed.rainxch.pljuneminichallenges.core.presentation.designsystem.ui.theme.toColorX

@Composable
fun GiftMemoryProgressbar(
    currentProgress: Int,
    totalProgress: Int,
    modifier: Modifier = Modifier
) {
    require(totalProgress >= currentProgress)

    Row(
        modifier = modifier.fillMaxWidth()
    ) {
        repeat(totalProgress) {
            val color = if (currentProgress > it) {
                GiftMemoryCatchColors.SUCCESS.toColorX()
            } else GiftMemoryCatchColors.SURFACE_HIGHER.toColorX().copy(alpha = .15f)
            Box(
                modifier = Modifier
                    .height(8.dp)
                    .weight(1f)
                    .padding(horizontal = 4.dp)
                    .clip(CircleShape)
                    .background(color)
            )
        }
    }
}

@Preview
@Composable
private fun GiftMemoryProgressbarPreview() {
    GiftMemoryProgressbar(
        currentProgress = 2,
        totalProgress = 6
    )
}