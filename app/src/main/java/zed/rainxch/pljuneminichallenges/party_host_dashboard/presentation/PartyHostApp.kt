package zed.rainxch.pljuneminichallenges.party_host_dashboard.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import kotlinx.serialization.serializer
import zed.rainxch.pljuneminichallenges.R
import zed.rainxch.pljuneminichallenges.core.presentation.designsystem.ui.theme.PartyHostDashboardColors
import zed.rainxch.pljuneminichallenges.core.presentation.designsystem.ui.theme.toColorX
import zed.rainxch.pljuneminichallenges.party_host_dashboard.presentation.components.AppNavigationBar
import zed.rainxch.pljuneminichallenges.party_host_dashboard.presentation.gifts.GiftsScreenRoot
import zed.rainxch.pljuneminichallenges.party_host_dashboard.presentation.guest_list.GuestListDetailsScreen
import zed.rainxch.pljuneminichallenges.party_host_dashboard.presentation.guest_list.GuestListScreenRoot
import zed.rainxch.pljuneminichallenges.party_host_dashboard.presentation.model.Event
import zed.rainxch.pljuneminichallenges.party_host_dashboard.presentation.model.Guest
import zed.rainxch.pljuneminichallenges.party_host_dashboard.presentation.navigation.NavGraph
import zed.rainxch.pljuneminichallenges.party_host_dashboard.presentation.navigation.SerializableNavType
import zed.rainxch.pljuneminichallenges.party_host_dashboard.presentation.party_timeline.EventTimelineDetailsScreen
import zed.rainxch.pljuneminichallenges.party_host_dashboard.presentation.party_timeline.PartyTimelineScreenRoot
import kotlin.reflect.typeOf

@Composable
fun PartyHostDashboardRoot() {
    PartyHostApp()
}

@Composable
fun PartyHostApp() {
    var selectedItemIndex by remember { mutableIntStateOf(0) }
    val navHost = rememberNavController()
    AppNavigationBar(
        selectedItemIndex = selectedItemIndex,
        onNavItemClick = { index, destination ->
            selectedItemIndex = index
            navHost.navigate(destination.screen)
        },
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(PartyHostDashboardColors.SURFACE.toColorX())
        ) {
            Image(
                painter = painterResource(R.drawable.ic_decor),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxSize()
                    .align(Alignment.Center),
                contentScale = ContentScale.Crop
            )
            NavHost(
                navController = navHost,
                startDestination = NavGraph.GuestListScreen,
                modifier = Modifier
                    .safeDrawingPadding()
            ) {
                composable<NavGraph.GuestListScreen> {
                    GuestListScreenRoot(
                        onNavigateToDetailsScreen = { guest ->
                            navHost.navigate(NavGraph.GuestListDetailsScreen(guest))
                        }
                    )
                }
                composable<NavGraph.GuestListDetailsScreen>(
                    typeMap = mapOf(typeOf<Guest>() to SerializableNavType.create(serializer<Guest>()))
                ) { backStackEntry ->
                    val arguments = backStackEntry.toRoute<NavGraph.GuestListDetailsScreen>()
                    GuestListDetailsScreen(
                        guest = arguments.guest,
                        onNavigateBack = {
                            navHost.popBackStack()
                        }
                    )
                }
                composable<NavGraph.PartyTimelineScreen> {
                    PartyTimelineScreenRoot(
                        onNavigateToDetailsScreen = { event ->
                            navHost.navigate(NavGraph.PartyTimelineDetailsScreen(event))
                        }
                    )
                }

                composable<NavGraph.PartyTimelineDetailsScreen> (
                    typeMap = mapOf(
                        typeOf<Event>() to SerializableNavType.create(serializer<Event>())
                    )
                ) { backStackEntry ->
                    val arguments = backStackEntry.toRoute<NavGraph.PartyTimelineDetailsScreen>()
                    EventTimelineDetailsScreen(
                        event = arguments.event,
                        onNavigateBack = {
                            navHost.popBackStack()
                        }
                    )
                }

                composable<NavGraph.GiftsScreen> {
                    GiftsScreenRoot()
                }
            }
        }
    }
}

@Preview
@Composable
private fun PartyHostDashboardPreview() {
    PartyHostApp()
}