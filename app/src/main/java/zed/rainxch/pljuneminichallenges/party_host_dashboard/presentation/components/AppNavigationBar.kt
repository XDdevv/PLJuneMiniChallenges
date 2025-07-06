package zed.rainxch.pljuneminichallenges.party_host_dashboard.presentation.components

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.NavigationRailItemDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteDefaults
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteItemColors
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScaffold
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScaffoldDefaults
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteType
import androidx.compose.runtime.Composable
import androidx.window.core.layout.WindowWidthSizeClass
import zed.rainxch.pljuneminichallenges.core.presentation.designsystem.ui.theme.PartyHostDashboardColors
import zed.rainxch.pljuneminichallenges.core.presentation.designsystem.ui.theme.toColorX

@Composable
fun AppNavigationBar(
    selectedItemIndex: Int,
    onNavItemClick: (index : Int, menu:  MenuItem) -> Unit,
    showNavigationBar: Boolean,
    navigationItems: List<MenuItem>,
    content: @Composable () -> Unit,
) {

    val windowWidthClass = currentWindowAdaptiveInfo().windowSizeClass.windowWidthSizeClass
    val navBottomBarColors = NavigationBarItemDefaults.colors(
        indicatorColor = PartyHostDashboardColors.SURFACE_HIGHER.toColorX(),
        selectedTextColor = PartyHostDashboardColors.ON_SURFACE.toColorX(),
        unselectedTextColor = PartyHostDashboardColors.ON_SURFACE.toColorX().copy(alpha = .8f),
    )
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
            navigationItems.forEachIndexed { index, destination ->
                item(
                    selected = index == selectedItemIndex,
                    onClick = {
                        onNavItemClick(index, destination)
                    },
                    icon = {
                        Icon(
                            imageVector = destination.icon,
                            contentDescription = destination.title
                        )
                    },
                    label = {
                        Text(
                            text = destination.title
                        )
                    },
                    alwaysShowLabel = true,
                    colors = NavigationSuiteItemColors(
                        navigationBarItemColors = navBottomBarColors,
                        navigationRailItemColors = navBarRailColors,
                        navigationDrawerItemColors = navDrawerColors,
                    )
                )
            }
        },
        navigationSuiteColors = NavigationSuiteDefaults.colors(
            navigationBarContainerColor = PartyHostDashboardColors.SURFACE_HIGHER_VAR.toColorX(),
            navigationRailContainerColor = PartyHostDashboardColors.SURFACE_HIGHER_VAR.toColorX(),
            navigationDrawerContainerColor = PartyHostDashboardColors.SURFACE_HIGHER_VAR.toColorX()
        ),
        layoutType =
            if (showNavigationBar) {
                when (windowWidthClass) {
                    WindowWidthSizeClass.EXPANDED, WindowWidthSizeClass.MEDIUM -> {
                        NavigationSuiteType.NavigationDrawer
                    }

                    WindowWidthSizeClass.COMPACT -> {
                        NavigationSuiteType.NavigationBar
                    }

                    else -> NavigationSuiteScaffoldDefaults.calculateFromAdaptiveInfo(
                        currentWindowAdaptiveInfo()
                    )
                }
            } else {
                NavigationSuiteType.None
            },
        content = content
    )
}