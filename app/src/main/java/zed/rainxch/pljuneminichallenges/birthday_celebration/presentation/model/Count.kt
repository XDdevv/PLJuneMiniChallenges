package zed.rainxch.pljuneminichallenges.birthday_celebration.presentation.model

import androidx.annotation.DrawableRes
import zed.rainxch.pljuneminichallenges.R

enum class Count (
    @DrawableRes val iconRes: Int
) {
    ONE(R.drawable.one),
    TWO(R.drawable.two),
    THREE(R.drawable.three),
    FOUR(R.drawable.four),
    FIVE(R.drawable.five)
}