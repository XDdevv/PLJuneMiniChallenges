package zed.rainxch.pljuneminichallenges.gift_memory_match.presentation

import zed.rainxch.pljuneminichallenges.gift_memory_match.presentation.static_.GameCard

data class GiftMemoryMatchUiState(
    val gifts: List<GameCard> = emptyList(),
    val matchedCardCount: Int = 0,
    val selectedColor1: String? = null,
    val selectedColor2: String? = null,
    val shownCardIndexes : List<Int> = emptyList()
) {
    val totalGiftCount: Int
        get() = gifts.size
}
