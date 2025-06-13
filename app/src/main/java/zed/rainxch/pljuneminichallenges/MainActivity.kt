package zed.rainxch.pljuneminichallenges

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import zed.rainxch.pljuneminichallenges.cake_lighting_controller.presentation.CakeLightingController
import zed.rainxch.pljuneminichallenges.cake_lighting_controller.presentation.CakeLightingControllerRoot
import zed.rainxch.pljuneminichallenges.core.presentation.designsystem.ui.theme.PLJuneMiniChallengesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val viewModel by viewModels<TestViewmodel>()
//            viewModel.start()
            PLJuneMiniChallengesTheme {
                CakeLightingControllerRoot()
            }
        }
    }
}