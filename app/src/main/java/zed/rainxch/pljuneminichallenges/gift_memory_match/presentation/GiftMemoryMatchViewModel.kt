package zed.rainxch.pljuneminichallenges.gift_memory_match.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import zed.rainxch.pljuneminichallenges.gift_memory_match.presentation.static_.giftsList
import zed.rainxch.pljuneminichallenges.gift_memory_match.presentation.static_.toGameCards
import kotlin.time.Duration.Companion.seconds

class GiftMemoryMatchViewModel : ViewModel() {
    private val _state = MutableStateFlow(GiftMemoryMatchUiState())
    val state = _state.asStateFlow()

    fun onAction(action: GiftMemoryMatchAction) {
        when (action) {
            GiftMemoryMatchAction.OnStartClick -> {
                initializeGame()
            }
            is GiftMemoryMatchAction.OnCardSelected -> {

            }
            GiftMemoryMatchAction.OnTryAgainClick -> {

            }
        }
    }

    private fun initializeGame() {
        val gameList = giftsList.shuffled().take(6).toGameCards()
        _state.update { it.copy(
            gifts = gameList,
        ) }

        viewModelScope.launch {
            val gameListIndexes = gameList.indices.toList()

            _state.update { it.copy(
                matchedCardCount = 0,
                shownCardIndexes = gameListIndexes
            ) }

            delay(3.seconds)

            _state.update { it.copy(
                shownCardIndexes = emptyList()
            ) }
        }

    }
}