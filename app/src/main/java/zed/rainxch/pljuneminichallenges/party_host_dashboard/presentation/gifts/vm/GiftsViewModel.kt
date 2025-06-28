package zed.rainxch.pljuneminichallenges.party_host_dashboard.presentation.gifts.vm

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import zed.rainxch.pljuneminichallenges.core.presentation.utils.StringResourceProvider
import zed.rainxch.pljuneminichallenges.party_host_dashboard.presentation.model.Gift
import zed.rainxch.pljuneminichallenges.party_host_dashboard.presentation.model.toGift

data class GiftsUiState(
    val gifts: List<Gift> = emptyList()
)

class GiftsViewModel(
    private val stringResourceProvider: StringResourceProvider
) : ViewModel() {
    private val _state = MutableStateFlow(GiftsUiState())
    val state = _state.asStateFlow()

    init {
        initGiftList()
    }

    private fun initGiftList() {
        val guestNames = listOf(
            "Alice Bennett",
            "Daniel Cho",
            "Zoë Linde",
            "Mr. Whiskers",
            "Ava Rodriguez",
            "Chinedu Okwudili",
            "Lily Thompson",
            "James “Jimmy” Kwon",
            "Priya Mehra",
            "Gabriella De La Fuente"
        )
        val prize = stringResourceProvider.getPrizeList()
        val giftList = prize.map { it.toGift(receiverList = guestNames) }
        _state.update { it.copy(gifts = giftList) }
    }
}