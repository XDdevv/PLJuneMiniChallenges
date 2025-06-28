package zed.rainxch.pljuneminichallenges.core.presentation.utils

import android.content.Context
import zed.rainxch.pljuneminichallenges.R

class StringResourceProvider(
    private val context: Context
) {
    fun getPrizeList(): List<String> {
        val arr = context.resources.getTextArray(R.array.gifts)
        return arr.map { it.toString() }.toList()
    }
    fun getEventList(): List<String> {
        val arr = context.resources.getTextArray(R.array.gifts)
        return arr.map { it.toString() }.toList()
    }
}