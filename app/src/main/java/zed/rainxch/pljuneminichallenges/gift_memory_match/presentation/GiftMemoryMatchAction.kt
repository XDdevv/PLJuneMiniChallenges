package zed.rainxch.pljuneminichallenges.gift_memory_match.presentation

import zed.rainxch.pljuneminichallenges.gift_memory_match.presentation.static_.GiftItem

sealed interface GiftMemoryMatchAction {
    data object OnStartClick : GiftMemoryMatchAction
    data class OnGiftSelected(val giftItem: GiftItem) : GiftMemoryMatchAction
    data object OnTryAgainClick : GiftMemoryMatchAction
}