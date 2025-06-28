package zed.rainxch.pljuneminichallenges.party_host_dashboard.presentation.gifts

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import zed.rainxch.pljuneminichallenges.core.presentation.designsystem.ui.theme.PartyHostDashboardColors
import zed.rainxch.pljuneminichallenges.core.presentation.designsystem.ui.theme.nunitoFont
import zed.rainxch.pljuneminichallenges.core.presentation.designsystem.ui.theme.toColorX
import zed.rainxch.pljuneminichallenges.party_host_dashboard.presentation.gifts.components.GiftItem
import zed.rainxch.pljuneminichallenges.party_host_dashboard.presentation.gifts.vm.GiftsUiState
import zed.rainxch.pljuneminichallenges.party_host_dashboard.presentation.gifts.vm.GiftsViewModel
import zed.rainxch.pljuneminichallenges.party_host_dashboard.presentation.gifts.vm.GiftsViewModelProvider

@Composable
fun GiftsScreenRoot() {
    val context = LocalContext.current
    val factory = GiftsViewModelProvider(context = context)
    val viewModel = viewModel<GiftsViewModel>(factory = factory)
    val state by viewModel.state.collectAsState()
    GiftsScreen(
        state = state
    )
}

@Composable
fun GiftsScreen(
    state: GiftsUiState,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp)
    ) {
        Text(
            text = "Party Host Dashboard",
            fontSize = 24.sp,
            color = PartyHostDashboardColors.ON_SURFACE.toColorX(),
            fontFamily = nunitoFont,
            fontWeight = FontWeight.ExtraBold
        )

        Spacer(Modifier.height(8.dp))

        LazyColumn(
            modifier = Modifier.fillMaxWidth()
                .padding(horizontal = 2.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(state.gifts) { gift ->
                GiftItem(
                    gift = gift,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}