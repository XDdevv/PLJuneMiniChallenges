package zed.rainxch.pljuneminichallenges.gift_memory_match.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import zed.rainxch.pljuneminichallenges.gift_memory_match.presentation.components.GiftCardSide
import zed.rainxch.pljuneminichallenges.gift_memory_match.presentation.static_.giftsList
import zed.rainxch.pljuneminichallenges.gift_memory_match.presentation.static_.toGameCards

class GiftMemoryMatchViewModel : ViewModel() {
    private val _state = MutableStateFlow(GiftMemoryMatchUiState())
    val state = _state.asStateFlow()

    init {
        initializeGame()
    }

    fun onAction(action: GiftMemoryMatchAction) {
        when (action) {
            GiftMemoryMatchAction.OnStartClick -> {
                startGame()
            }

            is GiftMemoryMatchAction.OnCardSelected -> {
                val list = _state.value.gifts.toMutableList()
                list.find { it.second == action.gameCard.second }?.let {
                }

            }

            GiftMemoryMatchAction.OnTryAgainClick -> {
                finishGame()
            }
        }
    }

    private fun finishGame() {
        initializeGame()
    }

    private fun initializeGame() {
        val gameList = giftsList.shuffled().take(6).toGameCards().shuffled()
        val list = Array(giftsList.size) {
            GiftCardSide.FRONT
        }
        _state.update {
            it.copy(
                gifts = list.zip(gameList),
                gameState = GiftMemoryGameState.Idle
            )
        }
    }

    private fun startGame() {
        viewModelScope.launch {
            var gameCards = _state.value.gifts.toMutableList()
            gameCards = gameCards.map { (_, card) ->
                GiftCardSide.BACK to card
            }.toMutableList()

            _state.update {
                it.copy(
                    matchedCardCount = 0,
                    gifts = gameCards,
                    enabled = false,
                    gameState = GiftMemoryGameState.Starting
                )
            }

            delay(3000)

            gameCards = gameCards.map { (_, card) ->
                GiftCardSide.FRONT to card
            }.toMutableList()

            _state.update {
                it.copy(
                    gifts = gameCards,
                    enabled = true,
                    gameState = GiftMemoryGameState.Playing
                )
            }
        }
    }
}