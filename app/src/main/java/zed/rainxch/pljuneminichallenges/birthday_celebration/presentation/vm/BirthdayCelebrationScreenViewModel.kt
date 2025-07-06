package zed.rainxch.pljuneminichallenges.birthday_celebration.presentation.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import zed.rainxch.pljuneminichallenges.birthday_celebration.presentation.model.Count

data class BirthdayCelebrationUiState(
    val countingDown: Boolean = false,
    val count: Count = Count.FIVE,
)

sealed interface BirthdayCelebrationActions {
    data object StartCountdown : BirthdayCelebrationActions
    data object StopCountdown : BirthdayCelebrationActions
}

sealed interface BirthdayCelebrationEvents {
    data class OnSentMessage(val message: String) : BirthdayCelebrationEvents
}

class BirthdayCelebrationScreenViewModel : ViewModel() {
    private val _state = MutableStateFlow(BirthdayCelebrationUiState())
    val state = _state.asStateFlow()

    private val _counterFlow = MutableStateFlow<Int>(5)

    private var counterJob: Job? = null

    private val _eventsChannel = Channel<BirthdayCelebrationEvents>()
    val events = _eventsChannel.receiveAsFlow()

    fun onAction(action: BirthdayCelebrationActions) {
        when (action) {
            BirthdayCelebrationActions.StartCountdown -> {
                startCountdown()
            }

            BirthdayCelebrationActions.StopCountdown -> {
                stopCountdown()
            }
        }
    }

    private fun startCountdown() {
        counterJob?.cancel()
        counterJob = viewModelScope.launch {
            _state.update { it.copy(countingDown = true) }
            while (_counterFlow.value > 0) {
                delay(1000)
                val newValue = _counterFlow.value - 1
                _counterFlow.update { newValue }
                _state.update { it.copy(count = newValue.toCount()) }
            }

            _state.update { it.copy(countingDown = false) }

            _eventsChannel.send(
                BirthdayCelebrationEvents.OnSentMessage(
                    message = "\uD83C\uDF89 Itʼs cake time !"
                )
            )

            stopCountdown()
        }
    }

    private fun Int.toCount(): Count {
        return when (this) {
            1 -> Count.ONE
            2 -> Count.TWO
            3 -> Count.THREE
            4 -> Count.FOUR
            5 -> Count.FIVE
            else -> Count.FIVE
        }
    }

    private fun stopCountdown() {
        counterJob?.cancel()
        counterJob = null
        _counterFlow.update { 5 }
        _state.update { it.copy(countingDown = false, count = Count.FIVE) }
    }
}