package zed.rainxch.pljuneminichallenges.cake_lighting_controller.presentation

import zed.rainxch.pljuneminichallenges.cake_lighting_controller.presentation.model.Candle

sealed interface CakeLightningAction {
    data class OnCandleTouch(
        val candle: Candle
    ) : CakeLightningAction

    data object OnLightAllButtonClicked : CakeLightningAction
}