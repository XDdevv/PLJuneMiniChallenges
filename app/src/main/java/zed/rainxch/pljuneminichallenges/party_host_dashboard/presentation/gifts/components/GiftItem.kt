package zed.rainxch.pljuneminichallenges.party_host_dashboard.presentation.gifts.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import zed.rainxch.pljuneminichallenges.core.presentation.designsystem.ui.theme.PartyHostDashboardColors
import zed.rainxch.pljuneminichallenges.core.presentation.designsystem.ui.theme.maliFont
import zed.rainxch.pljuneminichallenges.core.presentation.designsystem.ui.theme.nunitoFont
import zed.rainxch.pljuneminichallenges.core.presentation.designsystem.ui.theme.toColorX
import zed.rainxch.pljuneminichallenges.party_host_dashboard.presentation.model.Gift

@Composable
fun GiftItem(
    gift: Gift,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxHeight()
            .clip(RoundedCornerShape(12.dp))
            .background(PartyHostDashboardColors.SURFACE_HIGHER_VAR.toColorX())
            .padding(8.dp)
    ) {
        Text(
            text = gift.prize,
            fontSize = 20.sp,
            fontWeight = FontWeight.W700,
            fontFamily = nunitoFont,
            color = PartyHostDashboardColors.ON_SURFACE.toColorX()
        )
        Spacer(Modifier.height(4.dp))
        Text(
            text = gift.receiver,
            fontSize = 16.sp,
            fontWeight = FontWeight.W500,
            fontFamily = maliFont,
            color = PartyHostDashboardColors.ON_SURFACE.toColorX().copy(alpha = .8f)
        )
    }
}