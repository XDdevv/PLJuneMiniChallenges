package zed.rainxch.pljuneminichallenges.core.presentation.designsystem.ui.theme

import androidx.compose.ui.graphics.Color

val Purple80 = Color(0xFFD0BCFF)
val PurpleGrey80 = Color(0xFFCCC2DC)
val Pink80 = Color(0xFFEFB8C8)

val Purple40 = Color(0xFF6650a4)
val PurpleGrey40 = Color(0xFF625b71)
val Pink40 = Color(0xFF7D5260)

object BirthdayInviteColors {
    const val BACKGROUND = 0XFF1A597B
    const val SECONDARY = 0XFF113345
    const val SURFACE = 0xffFFF5EB
}

object CandleChallengeColors {
    const val BACKGROUND = 0XFF1A597B
    const val ON_BACKGROUND = 0XFFFFF5EB
    const val ON_SURFACE = 0XFF113345
    const val SURFACE_HIGHER = 0XFF95D3ED
}

fun Long.toColorX() : Color {
    return Color(this)
}