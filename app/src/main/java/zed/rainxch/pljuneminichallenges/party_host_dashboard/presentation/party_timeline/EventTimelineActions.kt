package zed.rainxch.pljuneminichallenges.party_host_dashboard.presentation.party_timeline

import zed.rainxch.pljuneminichallenges.party_host_dashboard.presentation.model.Event

sealed interface EventTimelineActions {
    data class OnEventSelected(val event: Event) : EventTimelineActions
}