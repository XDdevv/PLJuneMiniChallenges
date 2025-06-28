package zed.rainxch.pljuneminichallenges.party_host_dashboard.presentation.guest_list.components

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
import zed.rainxch.pljuneminichallenges.party_host_dashboard.presentation.model.Guest
import java.util.Locale

@Composable
fun GuestItem(
    guest: Guest,
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
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = guest.name,
            fontFamily = nunitoFont,
            fontWeight = FontWeight.W600,
            color = PartyHostDashboardColors.ON_SURFACE.toColorX(),
            fontSize = 20.sp
        )

        Text(
            text = guest.status.text,
            fontFamily = maliFont,
            fontWeight = FontWeight.W700,
            color = guest.status.textColor,
            fontSize = 16.sp,
        )
    }
}