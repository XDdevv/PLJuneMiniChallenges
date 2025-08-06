package zed.rainxch.pljuneminichallenges.gift_memory_match.presentation.models

enum class GiftCardSide {
    FRONT,
    BACK;

    fun next(): GiftCardSide {
        return when (this) {
            FRONT -> BACK
            BACK -> FRONT
        }
    }
}