package zed.rainxch.pljuneminichallenges.party_host_dashboard.presentation.components

import androidx.compose.ui.graphics.vector.ImageVector
import zed.rainxch.pljuneminichallenges.party_host_dashboard.presentation.navigation.NavGraph

data class MenuItem (
    val title: String,
    val icon : ImageVector,
    val screen: NavGraph
)