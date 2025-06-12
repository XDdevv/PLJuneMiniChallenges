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
    val isTablet = isTablet(context)
    val cardHeight = if (isTablet) Modifier.height(600.dp)
    else Modifier.height(400.dp)
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
                    .then(cardHeight)
                    .width(640.dp)
                    .padding(horizontal = 40.dp)
            ) {

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Spacer(Modifier.weight(1f))
                    Text(
                        text = birthdayCard.title,
                        color = BirthdayInviteColors.SECONDARY.toColorX(),
                        fontSize = 32.sp,
                        fontFamily = maliFont,
                        fontWeight = FontWeight.SemiBold,
                        textAlign = TextAlign.Center
                    )
                    Text(
                        text = birthdayCard.description,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Normal,
                        fontFamily = maliFont,
                        color = BirthdayInviteColors.SECONDARY.toColorX(),
                        textAlign = TextAlign.Center
                    )

                    Spacer(Modifier.height(24.dp))

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
                        fontSize = 18.sp,
                        fontFamily = nunitoFont,
                        textAlign = TextAlign.Center
                    )

                    Text(
                        text = formattedTimeText,
                        fontSize = 18.sp,
                        fontFamily = nunitoFont,
                        textAlign = TextAlign.Center
                    )

                    Text(
                        text = location,
                        fontSize = 18.sp,
                        fontFamily = nunitoFont,
                        textAlign = TextAlign.Center
                    )

                    Spacer(Modifier.weight(1f))

                    Text(
                        text = "RSVP by ${birthdayCard.rsvp}",
                        fontFamily = nunitoFont,
                        color = BirthdayInviteColors.SECONDARY.toColorX().copy(alpha = .8f),
                        fontSize = 16.sp
                    )
                }

                Image(
                    painter = painterResource(R.drawable.decor),
                    contentDescription = null,
                    contentScale = ContentScale.FillBounds
                )
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