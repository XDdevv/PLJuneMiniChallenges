package zed.rainxch.pljuneminichallenges.party_host_dashboard.presentation.guest_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import zed.rainxch.pljuneminichallenges.core.presentation.utils.StringResourceProvider
import zed.rainxch.pljuneminichallenges.party_host_dashboard.presentation.model.Guest
import zed.rainxch.pljuneminichallenges.party_host_dashboard.presentation.model.toGuest

data class GuestListUiState(
    val guestList: List<Guest> = emptyList(),
    val currentSelectedGuest: Guest? = null
)

class GuestListViewModel (
    private val stringResourceProvider : StringResourceProvider
) : ViewModel() {
    private val _state = MutableStateFlow(GuestListUiState())
    val state = _state
        .onStart {
            initGuestList()
            _state.update { it.copy(currentSelectedGuest = it.guestList.first()) }
        }.stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            initialValue = GuestListUiState()
        )

    fun initGuestList() {
        val guestNames = listOf(
            "Alice Bennett",
            "Daniel Cho",
            "Zoë Linde",
            "Mr. Whiskers",
            "Ava Rodriguez",
            "Chinedu Okwudili",
            "Lily Thompson",
            "James “Jimmy” Kwon",
            "Priya Mehra",
            "Gabriella De La Fuente"
        )
        val guestList = guestNames.map { it.toGuest(stringResourceProvider.getPrizeList()) }
        _state.update { it.copy(guestList = guestList) }
    }

    fun onAction(action: GuestListActions) {
        when (action) {
            is GuestListActions.OnGuestSelected -> {
                _state.update { it.copy(currentSelectedGuest = action.guest) }
            }
        }
    }

}