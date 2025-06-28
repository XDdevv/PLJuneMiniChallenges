package zed.rainxch.pljuneminichallenges.party_host_dashboard.presentation.model

import kotlinx.serialization.Serializable

@Serializable
data class Guest(
    val name: String,
    val status: GuestStatus,
    val gift : String
)

fun String.toGuest(prizeList: List<String>): Guest {
    return Guest(
        name = this,
        status = GuestStatus.entries.toTypedArray().random(),
        gift = prizeList.random()
    )
}
