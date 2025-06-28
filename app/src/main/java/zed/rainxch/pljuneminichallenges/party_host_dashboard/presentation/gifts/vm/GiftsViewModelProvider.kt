package zed.rainxch.pljuneminichallenges.party_host_dashboard.presentation.gifts.vm

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import zed.rainxch.pljuneminichallenges.core.presentation.utils.StringResourceProvider

class GiftsViewModelProvider (
    private val context: Context
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(GiftsViewModel::class.java)) {
            val stringProvider = StringResourceProvider(context = context)
            return GiftsViewModel(stringProvider) as T
        }
        return super.create(modelClass)
    }
}