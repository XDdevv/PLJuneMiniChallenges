package zed.rainxch.pljuneminichallenges.party_host_dashboard.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import kotlinx.serialization.serializer
import zed.rainxch.pljuneminichallenges.R
import zed.rainxch.pljuneminichallenges.core.presentation.designsystem.ui.theme.PartyHostDashboardColors
import zed.rainxch.pljuneminichallenges.core.presentation.designsystem.ui.theme.gift
import zed.rainxch.pljuneminichallenges.core.presentation.designsystem.ui.theme.guests
import zed.rainxch.pljuneminichallenges.core.presentation.designsystem.ui.theme.toColorX
import zed.rainxch.pljuneminichallenges.core.presentation.designsystem.ui.theme.watch
import zed.rainxch.pljuneminichallenges.party_host_dashboard.presentation.components.AppNavigationBar
import zed.rainxch.pljuneminichallenges.party_host_dashboard.presentation.components.MenuItem
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
    val navHostController = rememberNavController()
    val currentEntry by navHostController.currentBackStackEntryAsState()
    var selectedItemIndex by rememberSaveable { mutableIntStateOf(0) }
    var showNavigationBar by rememberSaveable { mutableStateOf(true) }
    val navigationItems = listOf(
        MenuItem(
            title = "Guest list",
            icon = Icons.Outlined.guests,
            screen = NavGraph.GuestListScreen
        ),
        MenuItem(
            title = "Party Timeline",
            icon = Icons.Outlined.watch,
            screen = NavGraph.PartyTimelineScreen
        ),
        MenuItem(
            title = "Gifts",
            icon = Icons.Outlined.gift,
            screen = NavGraph.GiftsScreen
        )
    )

    LaunchedEffect(currentEntry) {
        val currentRoute = currentEntry?.destination?.route?.split(".")
            ?.lastOrNull()
            ?: "GuestListScreen"
        showNavigationBar = navigationItems.any { currentRoute == it.screen.route }
        selectedItemIndex = navigationItems.indexOfLast { currentRoute == it.screen.route }
    }

    AppNavigationBar(
        selectedItemIndex = selectedItemIndex,
        onNavItemClick = { index, destination ->
            val currentRoute = currentEntry?.destination?.route?.split(".")
                ?.lastOrNull()
                ?: "GuestListScreen"
            if (currentRoute != destination.screen.route) {
                selectedItemIndex = index
                navHostController.navigate(destination.screen)
            }
        },
        navigationItems = navigationItems,
        showNavigationBar = showNavigationBar
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(PartyHostDashboardColors.SURFACE.toColorX())
        ) {
            Image(
                painter = painterResource(R.drawable.ic_celebration_decor),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxSize()
                    .align(Alignment.Center),
                contentScale = ContentScale.Crop
            )
            NavHost(
                navController = navHostController,
                startDestination = NavGraph.GuestListScreen,
                modifier = Modifier
                    .safeDrawingPadding()
            ) {
                composable<NavGraph.GuestListScreen> {
                    GuestListScreenRoot(
                        onNavigateToDetailsScreen = { guest ->
                            navHostController.navigate(NavGraph.GuestListDetailsScreen(guest))
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
                            navHostController.popBackStack()
                        }
                    )
                }
                composable<NavGraph.PartyTimelineScreen> {
                    PartyTimelineScreenRoot(
                        onNavigateToDetailsScreen = { event ->
                            navHostController.navigate(NavGraph.PartyTimelineDetailsScreen(event))
                        }
                    )
                }

                composable<NavGraph.PartyTimelineDetailsScreen>(
                    typeMap = mapOf(
                        typeOf<Event>() to SerializableNavType.create(serializer<Event>())
                    )
                ) { backStackEntry ->
                    val arguments = backStackEntry.toRoute<NavGraph.PartyTimelineDetailsScreen>()
                    EventTimelineDetailsScreen(
                        event = arguments.event,
                        onNavigateBack = {
                            navHostController.popBackStack()
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