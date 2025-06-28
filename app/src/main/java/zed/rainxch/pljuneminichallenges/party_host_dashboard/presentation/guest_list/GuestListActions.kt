package zed.rainxch.pljuneminichallenges.party_host_dashboard.presentation.guest_list

import zed.rainxch.pljuneminichallenges.party_host_dashboard.presentation.model.Guest

sealed interface GuestListActions {
    data class OnGuestSelected(val guest: Guest) : GuestListActions
}