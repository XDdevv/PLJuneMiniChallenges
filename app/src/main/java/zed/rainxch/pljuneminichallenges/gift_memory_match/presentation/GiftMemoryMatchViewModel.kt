package zed.rainxch.pljuneminichallenges.gift_memory_match.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import zed.rainxch.pljuneminichallenges.gift_memory_match.presentation.models.GiftCardSide
import zed.rainxch.pljuneminichallenges.gift_memory_match.presentation.static_.GameCard
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
                val index = _state.value.gifts.indexOf(action.gameCard)

                if (_state.value.gifts[index].third.not()) return  // disabled
                if (_state.value.selectedIndices.size >= 2) return   // already selected 2 cards

                val updatedList = _state.value.gifts.toMutableList()
                val (side, card, _) = updatedList[index]
                updatedList[index] = Triple(side.next(), card, false)

                val newSelected = _state.value.selectedIndices + index

                _state.update {
                    it.copy(
                        gifts = updatedList,
                        selectedIndices = newSelected
                    )
                }

                if (newSelected.size == 2) {
                    val disabledList = updatedList.map { (s, c, _) ->
                        Triple(s, c, false)
                    }

                    _state.update {
                        it.copy(gifts = disabledList)
                    }

                    viewModelScope.launch {
                        delay(1000)
                        evaluateSelectedCards(newSelected)
                    }
                }
            }


            GiftMemoryMatchAction.OnTryAgainClick -> {
                finishGame()
            }
        }
    }

    private fun evaluateSelectedCards(selectedIndices: List<Int>) {
        val gifts = _state.value.gifts.toMutableList()
        val card1 = gifts[selectedIndices[0]]
        val card2 = gifts[selectedIndices[1]]

        val matched = card1.second.colorName == card2.second.colorName

        if (matched) {
            val nextMatchedCount = _state.value.matchedCardCount + 1

            val updated = gifts.map { (side, card, _) ->
                if (side == GiftCardSide.FRONT) {
                    Triple(side, card, true)
                } else {
                    Triple(side, card, false)
                }
            }

            _state.update {
                it.copy(
                    matchedCardCount = nextMatchedCount,
                    gifts = updated,
                    selectedIndices = emptyList(),
                    gameState = if (nextMatchedCount == it.totalGiftCount) {
                        GiftMemoryGameState.Finished
                    } else {
                        it.gameState
                    }
                )
            }

        } else {
            selectedIndices.forEach { i ->
                val (side, card, _) = gifts[i]
                gifts[i] = Triple(side.next(), card, true)
            }

            val updated = gifts.map { (side, card, _) ->
                if (side == GiftCardSide.FRONT) {
                    Triple(side, card, true)
                } else {
                    Triple(side, card, false)
                }
            }

            _state.update {
                it.copy(
                    gifts = updated,
                    selectedIndices = emptyList()
                )
            }
        }
    }

    private fun finishGame() {
        initializeGame()
    }

    private fun initializeGame() {
        val gameList = giftsList.shuffled().take(6).toGameCards().shuffled()

        val cardSides = Array(gameList.size) {
            GiftCardSide.FRONT
        }.toList()

        val cardSideStatuses = Array(gameList.size) {
            false
        }.toList()

        _state.update {
            it.copy(
                gifts = zip3(cardSides, gameList, cardSideStatuses),
                gameState = GiftMemoryGameState.Idle
            )
        }
    }

    private fun startGame() {
        viewModelScope.launch {
            var gameCards = _state.value.gifts.toMutableList()
            gameCards = gameCards.map { (_, card, enabled) ->
                Triple(GiftCardSide.BACK, card, false)
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

            gameCards = gameCards.map { (_, card, enabled) ->
                Triple(GiftCardSide.FRONT, card, true)
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

fun <A, B, C> zip3(
    list1: List<A>,
    list2: List<B>,
    list3: List<C>
): List<Triple<A, B, C>> {
    return list1.indices
        .asSequence()
        .take(minOf(list1.size, list2.size, list3.size))
        .map { i -> Triple(list1[i], list2[i], list3[i]) }
        .toList()
}
