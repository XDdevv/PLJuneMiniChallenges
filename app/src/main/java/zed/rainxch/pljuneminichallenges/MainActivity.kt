package zed.rainxch.pljuneminichallenges

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import zed.rainxch.pljuneminichallenges.birthday_celebration.presentation.BirthdayCelebrationScreenRoot
import zed.rainxch.pljuneminichallenges.core.presentation.designsystem.ui.theme.PLJuneMiniChallengesTheme
import zed.rainxch.pljuneminichallenges.gift_memory_match.presentation.GiftMemoryMatchScreenRoot
import zed.rainxch.pljuneminichallenges.party_host_dashboard.presentation.PartyHostApp
import zed.rainxch.pljuneminichallenges.party_host_dashboard.presentation.PartyHostDashboardRoot

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PLJuneMiniChallengesTheme {
                BirthdayCelebrationScreenRoot()
            }
        }
    }
}