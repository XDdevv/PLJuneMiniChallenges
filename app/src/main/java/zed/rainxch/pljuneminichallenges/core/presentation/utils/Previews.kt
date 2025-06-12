package zed.rainxch.pljuneminichallenges.core.presentation.utils

import zed.rainxch.pljuneminichallenges.birthday_invite_card.presentation.model.BirthdayCard
import java.time.LocalDate
import java.time.LocalDateTime

object Previews {
    internal val birthdayCardPreview : BirthdayCard = BirthdayCard(
        title = "You’re invited!",
        description = "Join us for a birthday bash \uD83C\uDF89",
        date = LocalDate.of(2025, 6, 14),
        time = LocalDateTime.of(2025, 6, 14,15,0),
        location = "Party Central, 123 Celebration Lane",
        rsvp = "June 9"
    )
}