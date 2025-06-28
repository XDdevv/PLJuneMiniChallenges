package zed.rainxch.pljuneminichallenges.party_host_dashboard.presentation.guest_list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import zed.rainxch.pljuneminichallenges.core.presentation.designsystem.ui.theme.PartyHostDashboardColors
import zed.rainxch.pljuneminichallenges.core.presentation.designsystem.ui.theme.back
import zed.rainxch.pljuneminichallenges.core.presentation.designsystem.ui.theme.maliFont
import zed.rainxch.pljuneminichallenges.core.presentation.designsystem.ui.theme.nunitoFont
import zed.rainxch.pljuneminichallenges.core.presentation.designsystem.ui.theme.toColorX
import zed.rainxch.pljuneminichallenges.core.presentation.utils.Previews
import zed.rainxch.pljuneminichallenges.party_host_dashboard.presentation.model.Guest

@Composable
fun GuestListDetailsScreen(
    guest: Guest,
    onNavigateBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                onClick = onNavigateBack
            ) {
                Icon(
                    imageVector = Icons.Default.back,
                    contentDescription = "Back icon",
                    tint = PartyHostDashboardColors.ON_SURFACE.toColorX()
                )
            }

            Text(
                text = "Guest",
                color = PartyHostDashboardColors.ON_SURFACE.toColorX(),
                fontSize = 24.sp,
                fontFamily = nunitoFont,
                fontWeight = FontWeight.W700
            )
        }

        Spacer(Modifier.height(8.dp))

        Surface(
            shape = RoundedCornerShape(12.dp),
            shadowElevation = 4.dp,
            tonalElevation = 4.dp,
            modifier = Modifier
                .fillMaxWidth(),
            color = PartyHostDashboardColors.SURFACE_HIGHER_VAR.toColorX()
        ) {
            Column(
                modifier = modifier
                    .padding(horizontal = 16.dp, vertical = 24.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = guest.name,
                        fontFamily = nunitoFont,
                        fontWeight = FontWeight.W600,
                        color = PartyHostDashboardColors.ON_SURFACE.toColorX(),
                        fontSize = 24.sp
                    )

                    Text(
                        text = guest.status.text,
                        fontFamily = maliFont,
                        fontWeight = FontWeight.W700,
                        fontSize = 16.sp,
                        color = guest.status.textColor,
                    )
                }

                Spacer(Modifier.height(16.dp))

                Text(
                    text = "GIFT",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.W600,
                    color = PartyHostDashboardColors.ON_SURFACE.toColorX().copy(alpha = .8f),
                    fontFamily = maliFont
                )
                Text(
                    text = guest.gift,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.W600,
                    color = PartyHostDashboardColors.ON_SURFACE.toColorX(),
                    fontFamily = nunitoFont
                )
            }
        }
    }
}

@Preview
@Composable
private fun GuestListDetailsScreenPreview() {
    GuestListDetailsScreen(
        guest = Previews.guestPreview,
        onNavigateBack = {

        }
    )
}