package zed.rainxch.pljuneminichallenges.party_host_dashboard.presentation.components

import androidx.compose.material.icons.Icons
import androidx.compose.ui.graphics.vector.ImageVector
import zed.rainxch.pljuneminichallenges.core.presentation.designsystem.ui.theme.guests
import zed.rainxch.pljuneminichallenges.party_host_dashboard.presentation.navigation.NavGraph

data class Destination (
    val title: String,
    val icon : ImageVector,
    val screen: NavGraph
)