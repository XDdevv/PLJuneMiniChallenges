package zed.rainxch.pljuneminichallenges.gift_memory_match.presentation

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import zed.rainxch.pljuneminichallenges.birthday_celebration.presentation.vm.BirthdayCelebrationActions

class GiftMemoryMatchViewModel : ViewModel() {
    private val _state = MutableStateFlow(GiftMemoryMatchUiState())
    val state = _state.asStateFlow()

    fun onAction(action: GiftMemoryMatchAction) {
        when (action) {
            is GiftMemoryMatchAction.OnGiftSelected -> {
                
            }
            GiftMemoryMatchAction.OnStartClick -> {

            }
            GiftMemoryMatchAction.OnTryAgainClick -> {

            }
        }
    }
}