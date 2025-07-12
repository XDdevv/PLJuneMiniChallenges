package zed.rainxch.pljuneminichallenges.gift_memory_match.presentation

import zed.rainxch.pljuneminichallenges.gift_memory_match.presentation.static_.GiftItem
import zed.rainxch.pljuneminichallenges.gift_memory_match.presentation.static_.giftsList

data class GiftMemoryMatchUiState(
    val gifts: List<GiftItem> = giftsList,
    val matchedGiftsCount: Int = 0,
    val selectedGiftIndexed: List<Int> = emptyList()
) {
    val totalGiftCount: Int
        get() = gifts.size
}
