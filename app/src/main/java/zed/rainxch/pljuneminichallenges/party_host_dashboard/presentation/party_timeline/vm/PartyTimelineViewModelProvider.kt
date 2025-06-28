package zed.rainxch.pljuneminichallenges.party_host_dashboard.presentation.party_timeline.vm

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import zed.rainxch.pljuneminichallenges.core.presentation.utils.StringResourceProvider
import zed.rainxch.pljuneminichallenges.party_host_dashboard.presentation.guest_list.GuestListViewModel
import zed.rainxch.pljuneminichallenges.party_host_dashboard.presentation.party_timeline.PartyTimelineViewModel

class PartyTimelineViewModelProvider(
    private val context: Context
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PartyTimelineViewModel::class.java)) {
            val stringResourceProvider = StringResourceProvider(context = context)
            val viewModel = PartyTimelineViewModel(stringResourceProvider)
            return viewModel as T
        }
        throw IllegalArgumentException("Couldn't initalize viewmodel for some rezon")
    }
}