package zed.rainxch.pljuneminichallenges.cake_lighting_controller.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import zed.rainxch.pljuneminichallenges.R
import zed.rainxch.pljuneminichallenges.cake_lighting_controller.presentation.model.Candle

@Composable
fun Candle(
    candle: Candle,
    isFired: Boolean,
    onClick: (isFired: Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .height(248.dp)
            .width(46.dp)
            .clickable(onClick = {
                onClick(isFired)
            }),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (isFired) {
            Image(
                painter = painterResource(R.drawable.ic_candle_on),
                contentDescription = "Candle's on"
            )
        } else {
            Image(
                painter = painterResource(R.drawable.ic_candle_off),
                contentDescription = "Candle's off"
            )
        }

        Image(
            painter = painterResource(candle.imageRes),
            contentDescription = "Candle body"
        )
    }

}

@Preview
@Composable
private fun CandlePreview() {
}