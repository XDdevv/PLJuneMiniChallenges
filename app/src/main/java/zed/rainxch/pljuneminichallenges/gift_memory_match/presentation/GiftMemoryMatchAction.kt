package zed.rainxch.pljuneminichallenges.gift_memory_match.presentation

import zed.rainxch.pljuneminichallenges.gift_memory_match.presentation.models.GiftCardSide
import zed.rainxch.pljuneminichallenges.gift_memory_match.presentation.static_.GameCard

sealed interface GiftMemoryMatchAction {
    data object OnStartClick : GiftMemoryMatchAction
    data class OnCardSelected(val gameCard: Triple<GiftCardSide, GameCard, Boolean>) : GiftMemoryMatchAction
    data object OnTryAgainClick : GiftMemoryMatchAction
}