package zed.rainxch.pljuneminichallenges.party_host_dashboard.presentation.party_timeline.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.toColor
import zed.rainxch.pljuneminichallenges.core.presentation.designsystem.ui.theme.PartyHostDashboardColors
import zed.rainxch.pljuneminichallenges.core.presentation.designsystem.ui.theme.maliFont
import zed.rainxch.pljuneminichallenges.core.presentation.designsystem.ui.theme.nunitoFont
import zed.rainxch.pljuneminichallenges.core.presentation.designsystem.ui.theme.toColorX
import zed.rainxch.pljuneminichallenges.party_host_dashboard.presentation.model.Event
import zed.rainxch.pljuneminichallenges.party_host_dashboard.presentation.model.Guest
import java.util.Locale

@Composable
fun EventItem(
    event: Event,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp, horizontal = 4.dp)
            .background(PartyHostDashboardColors.SURFACE_HIGHER_VAR.toColorX())
            .padding(horizontal = 8.dp, vertical = 4.dp)
            .clickable(onClick = onClick),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = event.time,
            fontFamily = maliFont,
            fontWeight = FontWeight.W600,
            color = PartyHostDashboardColors.BACKGROUND.toColorX(),
            fontSize = 18.sp,
        )


        Text(
            text = event.title,
            fontFamily = nunitoFont,
            fontWeight = FontWeight.W600,
            color = PartyHostDashboardColors.ON_SURFACE.toColorX(),
            fontSize = 20.sp
        )
    }
}