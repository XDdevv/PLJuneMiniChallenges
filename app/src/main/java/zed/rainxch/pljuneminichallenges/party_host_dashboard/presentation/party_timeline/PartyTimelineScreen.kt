package zed.rainxch.pljuneminichallenges.party_host_dashboard.presentation.party_timeline

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.PreviewScreenSizes
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import zed.rainxch.pljuneminichallenges.birthday_invite_card.presentation.isTablet
import zed.rainxch.pljuneminichallenges.core.presentation.designsystem.ui.theme.PartyHostDashboardColors
import zed.rainxch.pljuneminichallenges.core.presentation.designsystem.ui.theme.nunitoFont
import zed.rainxch.pljuneminichallenges.core.presentation.designsystem.ui.theme.toColorX
import zed.rainxch.pljuneminichallenges.party_host_dashboard.presentation.model.Event
import zed.rainxch.pljuneminichallenges.party_host_dashboard.presentation.party_timeline.components.EventItem
import zed.rainxch.pljuneminichallenges.party_host_dashboard.presentation.party_timeline.components.EventTabletDetailsScreen
import zed.rainxch.pljuneminichallenges.party_host_dashboard.presentation.party_timeline.vm.PartyTimelineViewModelProvider

@Composable
fun PartyTimelineScreenRoot(
    onNavigateToDetailsScreen: (Event) -> Unit,
) {
    val context = LocalContext.current
    val viewModelFactory = PartyTimelineViewModelProvider(context = context)
    val viewModel = viewModel<PartyTimelineViewModel>(factory = viewModelFactory)
    val state by viewModel.state.collectAsState()

    val isTablet = isTablet(context)

    if (isTablet) {
        PartyTimelineTabletScreen(
            state = state,
            onAction = viewModel::onAction
        )
    } else {
        PartyTimelineScreen(
            state = state,
            onNavigateToDetailsScreen = onNavigateToDetailsScreen
        )
    }
}

@Composable
fun PartyTimelineTabletScreen(
    state: PartyTimelineUiState,
    onAction: (EventTimelineActions) -> Unit,
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
                items(state.eventList) { event ->
                    EventItem(
                        event = event,
                        onClick = {
                            onAction(EventTimelineActions.OnEventSelected(event))
                        }
                    )
                }
            }

            state.currentSelectedGuest?.let {
                EventTabletDetailsScreen(
                    event = state.currentSelectedGuest,
                    modifier = Modifier
                        .weight(1f)
                        .clip(RoundedCornerShape(12.dp))
                        .shadow(
                            4.dp,
                            RoundedCornerShape(12.dp)
                        )
                        .background(PartyHostDashboardColors.SURFACE_HIGHER_VAR.toColorX())
                        .padding(vertical = 8.dp)
                )
            }
        }
    }
}

@Composable
fun PartyTimelineScreen(
    state: PartyTimelineUiState,
    onNavigateToDetailsScreen: (Event) -> Unit,
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
            items(state.eventList) { event ->
                EventItem(
                    event = event,
                    onClick = {
                        onNavigateToDetailsScreen(event)
                    }
                )
            }
        }

    }
}

@PreviewScreenSizes
@Composable
fun PartyTimelineScreenPreview() {
    PartyTimelineScreen(
        state = PartyTimelineUiState(),
        {}
    )
}