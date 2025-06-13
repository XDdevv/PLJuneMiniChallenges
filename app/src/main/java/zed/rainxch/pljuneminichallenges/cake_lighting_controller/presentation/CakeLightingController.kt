package zed.rainxch.pljuneminichallenges.cake_lighting_controller.presentation

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import zed.rainxch.pljuneminichallenges.cake_lighting_controller.presentation.components.Candle
import zed.rainxch.pljuneminichallenges.cake_lighting_controller.presentation.components.CandleChallengeButton
import zed.rainxch.pljuneminichallenges.core.presentation.designsystem.ui.theme.CandleChallengeColors
import zed.rainxch.pljuneminichallenges.core.presentation.designsystem.ui.theme.maliFont
import zed.rainxch.pljuneminichallenges.core.presentation.designsystem.ui.theme.toColorX

@Composable
fun CakeLightingControllerRoot() {
    val viewModel = viewModel<CakeLightningViewModel>()
    val state by viewModel.state.collectAsState()
    CakeLightingController(
        state = state,
        onAction = viewModel::onAction
    )
}

@Composable
fun CakeLightingController(
    modifier: Modifier = Modifier,
    state: CakeLightningUiState,
    onAction: (CakeLightningAction) -> Unit
) {
    val candleList = remember { state.candleMap.keys.toList() }
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(CandleChallengeColors.BACKGROUND.toColorX())
            .padding(32.dp)
            .windowInsetsPadding(WindowInsets.safeDrawing),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val candleTitle = remember(state.candleOnCount) { state.getCandleTitle() }
        AnimatedContent(
            targetState = candleTitle
        ) {
            if (it.isEmpty()) {
                Spacer(Modifier.height(40.dp))
            } else {
                Text(
                    text = it,
                    fontSize = 33.sp,
                    fontFamily = maliFont,
                    color = CandleChallengeColors.ON_BACKGROUND.toColorX(),
                    textAlign = TextAlign.Center,
                    modifier = Modifier.height(40.dp)
                )
            }
        }

        Spacer(Modifier.weight(1f))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .offset(y = (248 / 3).dp, x = 0.dp),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            candleList.take(2).forEach { candle ->
                Candle(
                    candle = candle,
                    onClick = {
                        onAction(CakeLightningAction.OnCandleTouch(candle))
                    },
                    isFired = state.candleMap[candle] == true
                )
            }
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            candleList.subList(2, 5).forEach { candle ->
                Candle(
                    candle = candle,
                    onClick = {
                        onAction(CakeLightningAction.OnCandleTouch(candle))
                    },
                    isFired = state.candleMap[candle] == true
                )
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .offset(y = (-248 / 3).dp, x = 0.dp),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            candleList.takeLast(2).forEach { candle ->
                Candle(
                    candle = candle,
                    isFired = state.candleMap[candle] == true,
                    onClick = {
                        onAction(CakeLightningAction.OnCandleTouch(candle))
                    }
                )
            }
        }

        Spacer(Modifier.weight(1f))

        val buttonEnabled = remember(state.candleOnCount) { state.getButtonEnabled() }
        AnimatedContent(
            targetState = buttonEnabled
        ) {
            if (!it) {
                Spacer(Modifier.height(60.dp))
            } else {
                CandleChallengeButton {
                    onAction(CakeLightningAction.OnLightAllButtonClicked)
                }
            }
        }
    }
}

@Preview
@Composable
private fun CakeLightningControllerPreview() {
    CakeLightingController(
        state = CakeLightningUiState()
    ) { }
}