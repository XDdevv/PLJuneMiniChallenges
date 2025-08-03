package zed.rainxch.pljuneminichallenges.gift_memory_match.presentation

import zed.rainxch.pljuneminichallenges.gift_memory_match.presentation.static_.GameCard

sealed interface GiftMemoryMatchAction {
    data object OnStartClick : GiftMemoryMatchAction
    data class OnCardSelected(val gameCard: GameCard) : GiftMemoryMatchAction
    data object OnTryAgainClick : GiftMemoryMatchAction
}