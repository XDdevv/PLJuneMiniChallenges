package zed.rainxch.pljuneminichallenges.party_host_dashboard.presentation.guest_list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsTopHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.currentComposer
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.PreviewScreenSizes
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import zed.rainxch.pljuneminichallenges.birthday_invite_card.presentation.isTablet
import zed.rainxch.pljuneminichallenges.core.presentation.designsystem.ui.theme.PartyHostDashboardColors
import zed.rainxch.pljuneminichallenges.core.presentation.designsystem.ui.theme.nunitoFont
import zed.rainxch.pljuneminichallenges.core.presentation.designsystem.ui.theme.toColorX
import zed.rainxch.pljuneminichallenges.party_host_dashboard.presentation.guest_list.components.GuestItem
import zed.rainxch.pljuneminichallenges.party_host_dashboard.presentation.guest_list.components.GuestTabletDetailsScreen
import zed.rainxch.pljuneminichallenges.party_host_dashboard.presentation.guest_list.vm.GuestListViewModelProvider
import zed.rainxch.pljuneminichallenges.party_host_dashboard.presentation.model.Guest

@Composable
fun GuestListScreenRoot(
    onNavigateToDetailsScreen: (Guest) -> Unit
) {
    val context = LocalContext.current
    val viewModelFactory = GuestListViewModelProvider(context = context)
    val viewModel = viewModel<GuestListViewModel>(factory = viewModelFactory)
    val state by viewModel.state.collectAsState()

    val isTablet = isTablet(context)

    if (isTablet) {
        GuestListTabletScreen(
            state = state,
            onAction = viewModel::onAction
        )
    } else {
        GuestListScreen(
            state = state,
            onNavigateToDetailsScreen = onNavigateToDetailsScreen
        )
    }
}

@Composable
fun GuestListTabletScreen(
    state: GuestListUiState,
    onAction: (GuestListActions) -> Unit,
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

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(1f)
                    .clip(RoundedCornerShape(12.dp))
                    .shadow(
                        4.dp,
                        RoundedCornerShape(12.dp)
                    )
                    .background(PartyHostDashboardColors.SURFACE_HIGHER_VAR.toColorX())
                    .padding(vertical = 8.dp)
            ) {
                items(state.guestList) { guest ->
                    GuestItem(
                        guest = guest,
                        onClick = {
                            onAction(GuestListActions.OnGuestSelected(guest))
                        }
                    )
                }
            }

            state.currentSelectedGuest?.let {
                GuestTabletDetailsScreen(
                    guest = state.currentSelectedGuest,
                    modifier = Modifier.weight(1f)
                )
            }
        }
    }
}

@Composable
fun GuestListScreen(
    state: GuestListUiState,
    onNavigateToDetailsScreen: (Guest) -> Unit,
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
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(12.dp))
                .shadow(
                    4.dp,
                    RoundedCornerShape(12.dp)
                )
                .background(PartyHostDashboardColors.SURFACE_HIGHER_VAR.toColorX())
                .padding(vertical = 8.dp)
        ) {
            items(state.guestList) { guest ->
                GuestItem(
                    guest = guest,
                    onClick = {
                        onNavigateToDetailsScreen(guest)
                    }
                )
            }
        }

    }
}

@PreviewScreenSizes
@Composable
fun GuestListScreenPreview() {
    GuestListScreen(
        state = GuestListUiState(),
        {}
    )
}