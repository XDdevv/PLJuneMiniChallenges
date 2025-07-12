package zed.rainxch.pljuneminichallenges.gift_memory_match.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.graphics.toColor
import zed.rainxch.pljuneminichallenges.R
import zed.rainxch.pljuneminichallenges.core.presentation.designsystem.ui.theme.GiftMemoryCatchColors
import zed.rainxch.pljuneminichallenges.core.presentation.designsystem.ui.theme.toColorX
import zed.rainxch.pljuneminichallenges.gift_memory_match.presentation.static_.GiftItem
import zed.rainxch.pljuneminichallenges.gift_memory_match.presentation.static_.giftsList

enum class GiftCardSide {
    FRONT,
    BACK
}

enum class GiftCardIcon(val iconRes: Int) {
    HUMAN(R.drawable.ic_guest),
    GIFT(R.drawable.ic_memory_catch_gift)
}

@Composable
fun GiftCardItem(
    giftItem: GiftItem,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    var giftCardSide by remember { mutableStateOf(GiftCardSide.BACK) }
    Box(
        modifier = modifier
            .size(120.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(GiftMemoryCatchColors.SURFACE.toColorX()),
        contentAlignment = Alignment.Center
    ) {
        when (giftCardSide) {
            GiftCardSide.FRONT -> {
                Image(
                    painter = painterResource(R.drawable.ic_stripes),
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize()
                )
            }

            GiftCardSide.BACK -> {
                Icon(
                    painter = painterResource(GiftCardIcon.entries.random().iconRes),
                    contentDescription = null,
                    tint = GiftMemoryCatchColors.SURFACE_VAR.toColorX(),
                    modifier = Modifier.size(72.dp)
                )

                Text(
                    text = giftItem.gift
                )
            }
        }
    }
}

@Preview
@Composable
private fun GiftCardItemPreview() {
    GiftCardItem(
        giftItem = giftsList.first(),
        onClick = {}
    )
}