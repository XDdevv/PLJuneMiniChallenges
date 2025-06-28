package zed.rainxch.pljuneminichallenges.party_host_dashboard.presentation.model

import androidx.compose.ui.graphics.Color
import zed.rainxch.pljuneminichallenges.core.presentation.designsystem.ui.theme.PartyHostDashboardColors
import zed.rainxch.pljuneminichallenges.core.presentation.designsystem.ui.theme.toColorX

enum class GuestStatus(
    val textColor: Color,
    val text: String
) {
    ATTENDING(
        PartyHostDashboardColors.FOREST_GREEN.toColorX(),
        "Attending"
    ),
    NOT_COMING(
        PartyHostDashboardColors.BOULDER_GREY.toColorX(),
        "Not coming"
    ),
    MAYBE(
        PartyHostDashboardColors.ELECTRIC_BLUE.toColorX(),
        "Maybe"
    )
}