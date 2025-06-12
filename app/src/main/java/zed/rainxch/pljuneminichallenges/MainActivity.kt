package zed.rainxch.pljuneminichallenges

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import zed.rainxch.pljuneminichallenges.birthday_invite_card.presentation.BirthdayInviteCard
import zed.rainxch.pljuneminichallenges.core.presentation.designsystem.ui.theme.PLJuneMiniChallengesTheme
import zed.rainxch.pljuneminichallenges.core.presentation.utils.Previews

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PLJuneMiniChallengesTheme {
                BirthdayInviteCard(
                    birthdayCard = Previews.birthdayCardPreview
                )
            }
        }
    }
}