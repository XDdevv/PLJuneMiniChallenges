package zed.rainxch.pljuneminichallenges.core.presentation.utils

import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale

fun LocalDate.toFormattedDate(
    pattern: String = "MMMM d, yyyy"
): String {
    val formatter = DateTimeFormatter.ofPattern(pattern, Locale.ENGLISH)
    return formatter.format(this)
}

fun LocalDateTime.toFormattedDate(
    pattern: String = "h:mm a"
): String {
    val formatter = DateTimeFormatter.ofPattern(pattern, Locale.ENGLISH)
    return formatter.format(this)
}