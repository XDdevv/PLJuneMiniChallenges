package zed.rainxch.pljuneminichallenges.cake_lighting_controller.presentation

import androidx.compose.runtime.snapshotFlow
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import zed.rainxch.pljuneminichallenges.cake_lighting_controller.presentation.model.Candle

class CakeLightningViewModel : ViewModel() {
    private val _state = MutableStateFlow(CakeLightningUiState())
    val state = _state.asStateFlow()

    private var parentJob = Job()
    private var coroutineContext = Dispatchers.IO + parentJob

    fun onAction(cakeLightningAction: CakeLightningAction) {
        when (cakeLightningAction) {
            is CakeLightningAction.OnCandleTouch -> {
                handleCandleTouch(cakeLightningAction.candle)
            }

            CakeLightningAction.OnLightAllButtonClicked -> {
                handleLightAllButtonClick()
            }
        }

        viewModelScope.launch {
            snapshotFlow { _state.value.candleOnCount }
                .collect { count ->
                    if (count == 0) {
                        restartParentJob()
                    }
                }
        }
    }

    private fun handleLightAllButtonClick() {
        val newCandles = _state.value.candleMap
        newCandles.keys.forEach {
            newCandles[it] = true
        }
        _state.update {
            it.copy(
                candleOnCount = 7,
                candleMap = newCandles
            )
        }
        restartParentJob()
    }

    private fun handleCandleTouch(
        candle: Candle
    ) {
        val isFired = _state.value.candleMap[candle] == true

        if (isFired) {
            val updatedCandles = _state.value.candleMap
            updatedCandles.put(candle, false)
            _state.update {
                it.copy(
                    candleOnCount = it.candleOnCount - 1,
                    candleMap = updatedCandles
                )
            }
            startTimerJob(candle)
        } else {
            val updatedCandles = _state.value.candleMap
            updatedCandles.put(candle, true)
            _state.update {
                it.copy(
                    it.candleOnCount + 1,
                    candleMap = updatedCandles
                )
            }
        }
    }

    private fun startTimerJob(candle: Candle) {
        viewModelScope.launch(coroutineContext) {
            delay(4000)
            if (!parentJob.isCancelled) {
                val updatedCandles = _state.value.candleMap
                updatedCandles.put(candle, true)
                _state.update { state ->
                    state.copy(
                        candleOnCount = state.candleOnCount + 1,
                        candleMap = updatedCandles
                    )
                }
            }
        }
    }

    private fun restartParentJob() {
        parentJob.cancel()
        parentJob = Job()
        coroutineContext = Dispatchers.IO + parentJob
    }

}