package zed.rainxch.pljuneminichallenges.gift_memory_match.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import zed.rainxch.pljuneminichallenges.R
import zed.rainxch.pljuneminichallenges.core.presentation.designsystem.ui.theme.GiftMemoryCatchColors
import zed.rainxch.pljuneminichallenges.core.presentation.designsystem.ui.theme.maliFont
import zed.rainxch.pljuneminichallenges.core.presentation.designsystem.ui.theme.toColorX
import zed.rainxch.pljuneminichallenges.gift_memory_match.presentation.components.GiftCardItem
import zed.rainxch.pljuneminichallenges.gift_memory_match.presentation.components.GiftCardSide
import zed.rainxch.pljuneminichallenges.gift_memory_match.presentation.components.GiftMemoryButton

@Composable
fun GiftMemoryMatchScreenRoot() {
    val viewModel = viewModel<GiftMemoryMatchViewModel>()
    val state by viewModel.state.collectAsState()

    GiftMemoryMatchScreen(
        state = state,
        onAction = viewModel::onAction
    )
}

@Composable
fun GiftMemoryMatchScreen(
    state: GiftMemoryMatchUiState,
    onAction: (GiftMemoryMatchAction) -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(GiftMemoryCatchColors.BACKGROUND.toColorX()),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(R.drawable.ic_celebration_decor),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "${state.matchedCardCount} of ${state.totalGiftCount} matches found",
                color = GiftMemoryCatchColors.ON_BACKGROUND.toColorX(),
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = maliFont
            )

            HorizontalDivider()

            LazyVerticalGrid(
                columns = GridCells.Fixed(3),
                contentPadding = PaddingValues(12.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                items(state.gifts) { gift ->
                    var giftCardSide by remember { mutableStateOf(GiftCardSide.FRONT) }
                    GiftCardItem(
                        gameCard = gift,
                        giftCardSide = giftCardSide,
                        onClick = {
                            giftCardSide = if (giftCardSide == GiftCardSide.FRONT) {
                                GiftCardSide.BACK
                            } else GiftCardSide.FRONT

                            onAction(GiftMemoryMatchAction.OnCardSelected(gift))
                        }
                    )
                }
            }
        }

        GiftMemoryButton(
            text = "Start",
            onClick = {
                onAction(GiftMemoryMatchAction.OnStartClick)
            },
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .navigationBarsPadding()
                .padding(bottom = 32.dp)
        )
    }
}

@Preview
@Composable
private fun GiftMemoryMatchScreenPreview() {
    GiftMemoryMatchScreen(
        state = GiftMemoryMatchUiState(),
        onAction = {

        }
    )
}