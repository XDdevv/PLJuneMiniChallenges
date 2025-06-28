package zed.rainxch.pljuneminichallenges.party_host_dashboard.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.NavigationDrawerItemColors
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.NavigationRailItemColors
import androidx.compose.material3.NavigationRailItemDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteDefaults
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteItemColors
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScaffold
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScaffoldDefaults
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteType
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.core.graphics.toColor
import androidx.window.core.layout.WindowWidthSizeClass
import zed.rainxch.pljuneminichallenges.core.presentation.designsystem.ui.theme.PartyHostDashboardColors
import zed.rainxch.pljuneminichallenges.core.presentation.designsystem.ui.theme.gift
import zed.rainxch.pljuneminichallenges.core.presentation.designsystem.ui.theme.guests
import zed.rainxch.pljuneminichallenges.core.presentation.designsystem.ui.theme.toColorX
import zed.rainxch.pljuneminichallenges.core.presentation.designsystem.ui.theme.watch
import zed.rainxch.pljuneminichallenges.party_host_dashboard.presentation.navigation.NavGraph

@Composable
fun AppNavigationBar(
    selectedItemIndex: Int,
    onNavItemClick: (Int, Destination) -> Unit,
    content : @Composable () -> Unit,
) {
    val navigationItems = listOf(
        Destination(
            title = "Guest list",
            icon = Icons.Outlined.guests,
            screen = NavGraph.GuestListScreen
        ),
        Destination(
            title = "Party Timeline",
            icon = Icons.Outlined.watch,
            screen = NavGraph.PartyTimelineScreen
        ),
        Destination(
            title = "Gifts",
            icon = Icons.Outlined.gift,
            screen = NavGraph.GiftsScreen
        )
    )

    val navBarItemColors = NavigationBarItemDefaults.colors(
        indicatorColor = PartyHostDashboardColors.SURFACE_HIGHER.toColorX(),
        selectedTextColor = PartyHostDashboardColors.ON_SURFACE.toColorX(),
        unselectedTextColor = PartyHostDashboardColors.ON_SURFACE.toColorX().copy(alpha = .8f),
    )
    val windowWidthClass = currentWindowAdaptiveInfo().windowSizeClass.windowWidthSizeClass
    val navBarRailColors = NavigationRailItemDefaults.colors(
        selectedTextColor = PartyHostDashboardColors.ON_SURFACE.toColorX(),
        unselectedTextColor = PartyHostDashboardColors.ON_SURFACE.toColorX().copy(alpha = .8f),
        indicatorColor = PartyHostDashboardColors.SURFACE_HIGHER.toColorX(),
    )
    val navDrawerColors = NavigationDrawerItemDefaults.colors(
        selectedTextColor = PartyHostDashboardColors.ON_SURFACE.toColorX(),
        unselectedTextColor = PartyHostDashboardColors.ON_SURFACE.toColorX().copy(alpha = .8f),
        selectedContainerColor = PartyHostDashboardColors.SURFACE_HIGHER.toColorX(),
        unselectedContainerColor = PartyHostDashboardColors.SURFACE_HIGHER_VAR.toColorX(),
    )
    NavigationSuiteScaffold(
        navigationSuiteItems = {
            navigationItems.forEachIndexed { index, item ->
                item(
                    selected = index == selectedItemIndex,
                    onClick = {
                        onNavItemClick(index, item)
                    },
                    icon = {
                        Icon(
                            imageVector = item.icon,
                            contentDescription = item.title
                        )
                    },
                    label = {
                        Text(
                            text = item.title
                        )
                    },
                    alwaysShowLabel = true,
                    colors = NavigationSuiteItemColors(
                        navigationBarItemColors = navBarItemColors,
                        navigationRailItemColors = navBarRailColors,
                        navigationDrawerItemColors = navDrawerColors,
                    )
                )
            }
        },
        navigationSuiteColors = NavigationSuiteDefaults.colors(
            navigationBarContainerColor = PartyHostDashboardColors.SURFACE_HIGHER_VAR.toColorX(),
            navigationRailContainerColor = PartyHostDashboardColors.SURFACE_HIGHER_VAR.toColorX()
        ),        layoutType = when (windowWidthClass) {
            WindowWidthSizeClass.EXPANDED, WindowWidthSizeClass.MEDIUM -> {
                NavigationSuiteType.NavigationDrawer
            }

            WindowWidthSizeClass.COMPACT -> {
                NavigationSuiteType.NavigationBar
            }

            else -> NavigationSuiteScaffoldDefaults.calculateFromAdaptiveInfo(
                currentWindowAdaptiveInfo()
            )
        },
    ) {
        content()
    }
}