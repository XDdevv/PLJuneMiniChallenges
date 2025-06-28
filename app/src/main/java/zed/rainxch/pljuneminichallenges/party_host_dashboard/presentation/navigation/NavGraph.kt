package zed.rainxch.pljuneminichallenges.party_host_dashboard.presentation.navigation

import kotlinx.serialization.Serializable
import zed.rainxch.pljuneminichallenges.party_host_dashboard.presentation.model.Event
import zed.rainxch.pljuneminichallenges.party_host_dashboard.presentation.model.Guest

@Serializable
sealed class NavGraph(
    val route: String
) {
    @Serializable
    data object GuestListScreen : NavGraph("GuestListScreen")
    @Serializable
    data class GuestListDetailsScreen(
        val guest: Guest
    ) : NavGraph("GuestListDetailsScreen")
    @Serializable
    data object PartyTimelineScreen : NavGraph("PartyTimelineScreen")
    @Serializable
    data class PartyTimelineDetailsScreen (
        val event: Event
    ) : NavGraph("PartyTimelineDetailsScreen")
    @Serializable
    data object GiftsScreen : NavGraph("GiftsScreen")
}