package zed.rainxch.pljuneminichallenges.birthday_invite_card.presentation.model

import java.time.LocalDate
import java.time.LocalDateTime

data class BirthdayCard(
    val title: String,
    val description: String,
    val date: LocalDate,
    val time: LocalDateTime,
    val location: String,
    val rsvp: String
)
