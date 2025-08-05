package zed.rainxch.pljuneminichallenges.gift_memory_match.presentation.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import zed.rainxch.pljuneminichallenges.R
import zed.rainxch.pljuneminichallenges.core.presentation.designsystem.ui.theme.GiftMemoryCatchColors
import zed.rainxch.pljuneminichallenges.core.presentation.designsystem.ui.theme.maliFont
import zed.rainxch.pljuneminichallenges.core.presentation.designsystem.ui.theme.toColorX
import zed.rainxch.pljuneminichallenges.gift_memory_match.presentation.static_.GameCard
import zed.rainxch.pljuneminichallenges.gift_memory_match.presentation.static_.giftsList
import zed.rainxch.pljuneminichallenges.gift_memory_match.presentation.static_.toGameCards

enum class GiftCardSide {
    FRONT,
    BACK
}

enum class GiftCardIcon(val iconRes: Int) {
    Recipent(R.drawable.ic_guest),
    GIFT(R.drawable.ic_memory_catch_gift)
}

@Composable
fun GiftCardItem(
    gameCard: Pair<GiftCardSide, GameCard>,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
) {
    val rotationY by animateFloatAsState(
        targetValue = if (gameCard.first == GiftCardSide.BACK) 0f else 180f,
        animationSpec = tween(durationMillis = 1000), label = ""
    )

    val showFront by remember {
        derivedStateOf { rotationY > 90f }
    }

    Box(
        modifier = modifier
            .size(120.dp)
            .graphicsLayer {
                this.rotationY = rotationY
                cameraDistance = 12 * density
            }
            .clip(RoundedCornerShape(16.dp))
            .clickable(
                enabled = enabled,
                onClick = onClick
            )
            .background(GiftMemoryCatchColors.SURFACE.toColorX()),
    ) {
        if (showFront) {
            Image(
                painter = painterResource(R.drawable.ic_stripes),
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )

        } else {
            GameCardContent(
                title = gameCard.second.name,
                borderColor = gameCard.second.color,
                giftType = gameCard.second
            )
        }
    }
}

@Composable
private fun GameCardContent(
    title: String,
    borderColor: Color,
    giftType: GameCard,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(2.dp)
            .border(4.dp, borderColor, RoundedCornerShape(14.dp))
            .padding(horizontal = 8.dp),
        contentAlignment = Alignment.Center
    ) {
        val iconRes = remember(giftType) {
            when (giftType) {
                is GameCard.Gift -> GiftCardIcon.GIFT.iconRes
                is GameCard.Recipient -> GiftCardIcon.Recipent.iconRes
            }
        }

        Icon(
            painter = painterResource(iconRes),
            contentDescription = null,
            tint = GiftMemoryCatchColors.SURFACE_VAR.toColorX(),
            modifier = Modifier.size(72.dp)
        )

        Text(
            text = title,
            fontFamily = maliFont,
            fontWeight = FontWeight.Bold,
            color = GiftMemoryCatchColors.ON_SURFACE.toColorX(),
            textAlign = TextAlign.Center
        )
    }
}