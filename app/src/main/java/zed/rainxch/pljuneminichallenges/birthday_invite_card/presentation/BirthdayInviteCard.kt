package zed.rainxch.pljuneminichallenges.birthday_invite_card.presentation

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewScreenSizes
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import zed.rainxch.pljuneminichallenges.R
import zed.rainxch.pljuneminichallenges.birthday_invite_card.presentation.model.BirthdayCard
import zed.rainxch.pljuneminichallenges.core.presentation.designsystem.ui.theme.BirthdayInviteColors
import zed.rainxch.pljuneminichallenges.core.presentation.designsystem.ui.theme.maliFont
import zed.rainxch.pljuneminichallenges.core.presentation.designsystem.ui.theme.nunitoFont
import zed.rainxch.pljuneminichallenges.core.presentation.designsystem.ui.theme.toColorX
import zed.rainxch.pljuneminichallenges.core.presentation.utils.Previews
import zed.rainxch.pljuneminichallenges.core.presentation.utils.toFormattedDate

@Composable
fun BirthdayInviteCard(
    birthdayCard: BirthdayCard,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val isTablet = remember { isTablet(context) }
    val cardWidth = if (isTablet) 640.dp else 380.dp
    val cardHeight = if (isTablet) 800.dp else 480.dp
    val titleSize = if (isTablet) 60.sp else 36.sp
    val descriptionSize = if (isTablet) 34.sp else 21.sp
    val contentSize = if (isTablet) 34.sp else 21.sp
    val rsvpSize = if (isTablet) 26.sp else 16.sp
    val spacerSize = if (isTablet) 56.dp else 32.dp
    val contentPadding = if (isTablet) 56.dp else 0.dp
    Scaffold(
        modifier = modifier
            .fillMaxSize()
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(BirthdayInviteColors.BACKGROUND.toColorX())
                .padding(innerPadding),
            contentAlignment = Alignment.Center
        ) {
            Surface(
                shape = RoundedCornerShape(28.dp),
                color = BirthdayInviteColors.SURFACE.toColorX(),
                modifier = Modifier
                    .width(cardWidth)
                    .height(cardHeight)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(vertical = 16.dp, horizontal = contentPadding),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Spacer(Modifier.weight(1f))
                    Text(
                        text = birthdayCard.title,
                        color = BirthdayInviteColors.SECONDARY.toColorX(),
                        fontSize = titleSize,
                        fontFamily = maliFont,
                        fontWeight = FontWeight.SemiBold,
                        textAlign = TextAlign.Center
                    )
                    Text(
                        text = birthdayCard.description,
                        fontSize = descriptionSize,
                        fontWeight = FontWeight.Normal,
                        fontFamily = maliFont,
                        color = BirthdayInviteColors.SECONDARY.toColorX(),
                        textAlign = TextAlign.Center
                    )

                    Spacer(Modifier.height(spacerSize))

                    val formattedDateText = remember {
                        createPairAnnotatedText(
                            "Date: ",
                            birthdayCard.date.toFormattedDate()
                        )
                    }
                    val formattedTimeText = remember {
                        createPairAnnotatedText(
                            "Time: ",
                            birthdayCard.time.toFormattedDate()
                        )
                    }
                    val location = remember {
                        createPairAnnotatedText(
                            "Location: ",
                            birthdayCard.location
                        )
                    }

                    Text(
                        text = formattedDateText,
                        fontSize = contentSize,
                        fontFamily = nunitoFont,
                        textAlign = TextAlign.Center
                    )

                    Text(
                        text = formattedTimeText,
                        fontSize = contentSize,
                        fontFamily = nunitoFont,
                        textAlign = TextAlign.Center
                    )

                    Text(
                        text = location,
                        fontSize = contentSize,
                        fontFamily = nunitoFont,
                        textAlign = TextAlign.Center,
                        lineHeight = contentSize * 1.2f
                    )

                    Spacer(Modifier.weight(1f))

                    Text(
                        text = "RSVP by ${birthdayCard.rsvp}",
                        fontFamily = nunitoFont,
                        color = BirthdayInviteColors.SECONDARY.toColorX().copy(alpha = .8f),
                        fontSize = rsvpSize
                    )
                }

                Image(
                    painter = painterResource(R.drawable.decor),
                    contentDescription = null,
                    contentScale = ContentScale.FillBounds
                )

                Spacer(Modifier.height(24.dp))
            }
        }
    }
}

fun createPairAnnotatedText(
    first: String,
    second: String
): AnnotatedString {
    return buildAnnotatedString {
        withStyle(
            SpanStyle(
                color = BirthdayInviteColors.SECONDARY.toColorX(),
                fontWeight = FontWeight.SemiBold,
            )
        ) {
            append(first)
        }
        withStyle(
            SpanStyle(
                color = BirthdayInviteColors.SECONDARY.toColorX()
                    .copy(alpha = .8f),
                fontWeight = FontWeight.Normal,
            )
        ) {
            append(second)
        }
    }
}

fun isTablet(context: Context): Boolean {
    return (context.resources.configuration.smallestScreenWidthDp >= 600)
}

@Preview(
    name = "Tablet - Portrait",
    widthDp = 800,
    heightDp = 1280
)
@Composable
private fun BirthdayInviteCardPreview() {
    BirthdayInviteCard(
        birthdayCard = Previews.birthdayCardPreview
    )
}