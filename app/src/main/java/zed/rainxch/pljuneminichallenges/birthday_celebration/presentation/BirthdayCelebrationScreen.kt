package zed.rainxch.pljuneminichallenges.birthday_celebration.presentation

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import zed.rainxch.pljuneminichallenges.R
import zed.rainxch.pljuneminichallenges.birthday_celebration.presentation.components.BirthdayCelebrationButton
import zed.rainxch.pljuneminichallenges.birthday_celebration.presentation.vm.BirthdayCelebrationActions
import zed.rainxch.pljuneminichallenges.birthday_celebration.presentation.vm.BirthdayCelebrationEvents
import zed.rainxch.pljuneminichallenges.birthday_celebration.presentation.vm.BirthdayCelebrationScreenViewModel
import zed.rainxch.pljuneminichallenges.birthday_celebration.presentation.vm.BirthdayCelebrationUiState
import zed.rainxch.pljuneminichallenges.core.presentation.designsystem.ui.theme.BirthdayCelebrationColors
import zed.rainxch.pljuneminichallenges.core.presentation.designsystem.ui.theme.toColorX
import zed.rainxch.pljuneminichallenges.core.presentation.utils.ObserveAsEvents

@Composable
fun BirthdayCelebrationScreenRoot() {
    val context = LocalContext.current

    val viewModel = viewModel<BirthdayCelebrationScreenViewModel>()
    val state by viewModel.state.collectAsState()

    ObserveAsEvents(viewModel.events) { event ->
        when (event) {
            is BirthdayCelebrationEvents.OnSentMessage -> {
                Toast.makeText(context, event.message, Toast.LENGTH_SHORT).show()
            }
        }
    }
    BirthdayCelebrationScreen(
        state = state,
        onAction = viewModel::onAction
    )
}

@Composable
fun BirthdayCelebrationScreen(
    state: BirthdayCelebrationUiState,
    onAction: (BirthdayCelebrationActions) -> Unit,
    modifier: Modifier = Modifier,
) {
    val screenHeight = LocalConfiguration.current.screenHeightDp.dp
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(BirthdayCelebrationColors.BACKGROUND.toColorX())
    ) {
        Image(
            painter = painterResource(R.drawable.ic_celebration_decor),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        Scaffold(
            modifier = Modifier.fillMaxSize(),
            containerColor = Color.Transparent
        ) { _ ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                if (!state.countingDown) {
                    BirthdayCelebrationButton(
                        text = "Count to Cake!",
                        onClick = {
                            onAction(BirthdayCelebrationActions.StartCountdown)
                        },
                        modifier = Modifier
                            .offset(y = screenHeight * .2f)
                            .align(Alignment.TopCenter),
                        containerColor = BirthdayCelebrationColors.SURFACE.toColorX(),
                        textColor = BirthdayCelebrationColors.ON_SURFACE.toColorX(),
                    )
                }

                Image(
                    painter = painterResource(R.drawable.ic_cake),
                    contentDescription = "Cake",
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .offset(y = 100.dp),
                    contentScale = ContentScale.Crop
                )


                if (state.countingDown) {
                    BirthdayCelebrationButton(
                        text = "Cancel",
                        onClick = {
                            onAction(BirthdayCelebrationActions.StopCountdown)
                        },
                        modifier = Modifier
                            .align(Alignment.BottomCenter)
                            .navigationBarsPadding(),
                        containerColor = BirthdayCelebrationColors.SURFACE.toColorX(),
                        textColor = BirthdayCelebrationColors.ERROR.toColorX(),
                    )
                }

                Column(
                    modifier = Modifier
                        .align(Alignment.Center),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    if (state.countingDown) {
                        Image(
                            painter = painterResource(R.drawable.ic_candle),
                            contentDescription = null,
                        )

                        Image(
                            painter = painterResource(state.count.iconRes),
                            contentDescription = null,
                            modifier = Modifier
                                .height(252.dp)
                                .width(200.dp)
                                .offset(y = -(252 / 2).dp),
                            contentScale = ContentScale.Fit
                        )
                    }
                }

            }
        }
    }
}

@Preview
@Composable
private fun BirthdayCelebrationScreenPreview() {
    BirthdayCelebrationScreen(
        state = BirthdayCelebrationUiState(),
        onAction = {

        }
    )
}