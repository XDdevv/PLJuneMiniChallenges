package zed.rainxch.pljuneminichallenges.gift_memory_match.presentation

import zed.rainxch.pljuneminichallenges.gift_memory_match.presentation.components.GiftCardSide
import zed.rainxch.pljuneminichallenges.gift_memory_match.presentation.static_.GameCard

data class GiftMemoryMatchUiState(
    val gifts: List<Pair<GiftCardSide, GameCard>> = emptyList(),
    val matchedCardCount: Int = 0,
    val selectedColor1: String? = null,
    val selectedColor2: String? = null,
    val enabled : Boolean = false,
    val gameState: GiftMemoryGameState = GiftMemoryGameState.Idle,
) {
    val totalGiftCount: Int
        get() = gifts.size
}

sealed class GiftMemoryGameState {
    data object Idle : GiftMemoryGameState()
    data object Starting : GiftMemoryGameState()
    data object Playing : GiftMemoryGameState()
    data object Finished : GiftMemoryGameState()
}