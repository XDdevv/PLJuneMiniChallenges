package zed.rainxch.pljuneminichallenges.party_host_dashboard.presentation.party_timeline.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import zed.rainxch.pljuneminichallenges.core.presentation.designsystem.ui.theme.PartyHostDashboardColors
import zed.rainxch.pljuneminichallenges.core.presentation.designsystem.ui.theme.maliFont
import zed.rainxch.pljuneminichallenges.core.presentation.designsystem.ui.theme.nunitoFont
import zed.rainxch.pljuneminichallenges.core.presentation.designsystem.ui.theme.toColorX
import zed.rainxch.pljuneminichallenges.core.presentation.utils.Previews.guestPreview
import zed.rainxch.pljuneminichallenges.party_host_dashboard.presentation.model.Event
import zed.rainxch.pljuneminichallenges.party_host_dashboard.presentation.model.Guest

@Composable
fun EventTabletDetailsScreen(
    event: Event,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxHeight()
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth(),
        ) {
            Text(
                text = event.time,
                fontFamily = maliFont,
                fontWeight = FontWeight.W700,
                color = PartyHostDashboardColors.BACKGROUND.toColorX(),
                fontSize = 16.sp
            )

            Text(
                text = event.title,
                fontFamily = nunitoFont,
                fontWeight = FontWeight.W600,
                color = PartyHostDashboardColors.ON_SURFACE.toColorX(),
                fontSize = 24.sp
            )

            Text(
                text = event.description,
                fontFamily = nunitoFont,
                fontWeight = FontWeight.W400,
                fontSize = 20.sp,
                color = PartyHostDashboardColors.ON_SURFACE.toColorX(),
            )
        }

        Spacer(Modifier.height(16.dp))

    }
}