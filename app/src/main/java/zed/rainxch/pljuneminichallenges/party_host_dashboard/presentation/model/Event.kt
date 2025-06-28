package zed.rainxch.pljuneminichallenges.party_host_dashboard.presentation.model

import kotlinx.serialization.Serializable

@Serializable
data class Event(
    val time: String,
    val title: String,
    val description: String
)

fun String.toEvent(
    timelines: List<String>,
    descriptions: List<String>
): Event {
    return Event(
        title = this,
        time = timelines.random(),
        description = descriptions.random()
    )
}
