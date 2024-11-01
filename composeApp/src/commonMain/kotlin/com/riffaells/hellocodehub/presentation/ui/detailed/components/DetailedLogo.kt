package com.riffaells.hellocodehub.presentation.ui.detailed.components

import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.Matrix
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.layout.layout
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.riffaells.hellocodehub.domain.model.ProgrammingLanguage
import com.riffaells.hellocodehub.presentation.theme.RIcons
import hellocodehub.composeapp.generated.resources.Res
import hellocodehub.composeapp.generated.resources.paradigms
import hellocodehub.composeapp.generated.resources.years
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.pluralStringResource
import org.jetbrains.compose.resources.stringArrayResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun DetailedLogo(
    modifier: Modifier = Modifier,
    lang: ProgrammingLanguage,
    onBack: () -> Unit,
    isRowLayout: Boolean

) {

    Box {
        IconButton(
            modifier = Modifier
                .padding(start = 4.dp),
            onClick = { onBack() },
            colors = IconButtonDefaults.iconButtonColors(

            )
        ) {
            Image(
                RIcons.ArrowBack,
                contentDescription = null,
                colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primary)
            )
        }


        val years =
            Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).year - lang.developmentStartYear
        YearsContent(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(bottom = 16.dp, end = 16.dp),
            years = years,
            text = pluralStringResource(Res.plurals.years, years)
        )


        if (isRowLayout) {
            Row(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(12.dp, 30.dp)
                    .zIndex(1f)
                    .align(Alignment.Center),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Image(
                    modifier = Modifier
                        .sizeIn(maxHeight = 60.dp, minHeight = 60.dp), // Уменьшение размера логотипа в Row
                    painter = painterResource(lang.getLogo()),
                    contentDescription = lang.name
                )
                Spacer(modifier = Modifier.width(8.dp))
                Column {
                    Text(
                        text = lang.name,
                        style = MaterialTheme.typography.displayMedium,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary
                    )
                    Text(
                        text = stringArrayResource(Res.array.paradigms)[0],
                        style = MaterialTheme.typography.headlineSmall,
                    )
                }
            }
        } else {
            Column(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(12.dp, 30.dp)
                    .zIndex(1f)
                    .align(Alignment.Center),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Image(
                    modifier = Modifier
                        .sizeIn(maxHeight = 120.dp, minHeight = 120.dp), // Размер логотипа для Column
                    painter = painterResource(lang.getLogo()),
                    contentDescription = lang.name
                )
                Text(
                    text = lang.name,
                    style = MaterialTheme.typography.displayMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary
                )
                Text(
                    text = lang.paradigms.getStringParadigms(stringArrayResource(Res.array.paradigms))[0],
                    style = MaterialTheme.typography.headlineSmall,
                )
            }
        }

    }
}


@Composable
fun YearsContent(
    modifier: Modifier = Modifier,
    years: Int,
    text: String,
) {


    val color = MaterialTheme.colorScheme.onPrimaryContainer
    Column(
        modifier = modifier
            .background(
                Brush.linearGradient(
                    listOf(color, color)
                ), CircleShape, alpha = 0.8f
            )
            .circleLayout(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            modifier = Modifier
//                .align(Alignment.Center)
                .padding(top = 8.dp),
            text = years.toString(),
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onPrimary
        )

        Text(
            modifier = Modifier
//                .align(Alignment.BottomCenter)
                .padding(bottom = 8.dp),
            text = text,
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.onPrimary
        )
    }
}

//}
fun Modifier.circleLayout() =
    layout { measurable, constraints ->
        // Измерить композицию
        val placeable = measurable.measure(constraints)

        //получить текущий максимальный размер, чтобы назначить width=height
        val currentHeight = placeable.height
        val currentWidth = placeable.width
        val newDiameter = maxOf(currentHeight, currentWidth)

        //назначить размер и положение центра
        layout(newDiameter, newDiameter) {
            // Где размещается составной элемент
            placeable.placeRelative((newDiameter - currentWidth) / 2, (newDiameter - currentHeight) / 2)
        }
    }