package zed.rainxch.pljuneminichallenges.cake_lighting_controller.presentation

import zed.rainxch.pljuneminichallenges.R
import zed.rainxch.pljuneminichallenges.cake_lighting_controller.presentation.model.Candle

data class CakeLightningUiState(
    val candleOnCount: Int = 0,
    val candleMap: HashMap<Candle, Boolean> = hashMapOf(
        Candle(id = 0, imageRes = R.drawable.ic_candle_one) to false,
        Candle(id = 1, imageRes = R.drawable.ic_candle_two) to false,
        Candle(id = 2, imageRes = R.drawable.ic_candle_three) to false,
        Candle(id = 3, imageRes = R.drawable.ic_candle_four) to false,
        Candle(id = 4, imageRes = R.drawable.ic_candle_five) to false,
        Candle(id = 5, imageRes = R.drawable.ic_candle_six) to false,
        Candle(id = 6, imageRes = R.drawable.ic_candle_seven) to false,
    )
) {
//    val candleCount: Int
//        get() = candleMap.count { it.value }

    fun getCandleTitle(): String {
        return when (candleOnCount) {
            0 -> "Make a wish... \uD83D\uDCA8"
            7 -> "Ready for cake \uD83C\uDF89"
            else -> ""
        }
    }

    fun getButtonEnabled(): Boolean {
        return candleOnCount != 7
    }

}