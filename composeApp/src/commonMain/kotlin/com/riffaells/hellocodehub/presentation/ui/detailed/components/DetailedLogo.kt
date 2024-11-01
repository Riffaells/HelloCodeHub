package com.riffaells.hellocodehub.presentation.ui.detailed.components

import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.*
import androidx.compose.ui.layout.layout
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.lerp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.util.lerp
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

@OptIn(ExperimentalAnimationApi::class, ExperimentalAnimationApi::class)
@Composable
fun DetailedLogo(
    modifier: Modifier = Modifier,
    lang: ProgrammingLanguage,
    onBack: () -> Unit,
    collapseFraction: Float
) {
    // Задаем максимальную и минимальную высоту области
    val maxHeight = 200.dp
    val minHeight = 70.dp // Минимальная высота в свернутом состоянии
    val animatedHeight by animateDpAsState(targetValue = lerp(maxHeight, minHeight, collapseFraction))

    // Анимированные размеры
    val logoSize by animateDpAsState(targetValue = lerp(120.dp, 40.dp, collapseFraction))
    val nameFontSize by animateSpAsState(targetValue = lerp(28.sp, 18.sp, collapseFraction))
    val paradigmFontSize by animateSpAsState(targetValue = lerp(20.sp, 14.sp, collapseFraction))
    val backButtonSize by animateDpAsState(targetValue = lerp(48.dp, 40.dp, collapseFraction))
    val yearsFontSize by animateSpAsState(targetValue = lerp(16.sp, 12.sp, collapseFraction))

    // Отступы для анимации позиций
    val contentPaddingHorizontal by animateDpAsState(targetValue = lerp(16.dp, 8.dp, collapseFraction))

    Box(
        modifier = modifier
            .sizeIn(maxWidth = 1000.dp, minHeight = minHeight)
            .height(animatedHeight)
            .fillMaxWidth()
    ) {
        // Основной контент
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = contentPaddingHorizontal),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Кнопка "Назад"
            IconButton(

                onClick = { onBack() },
                modifier = Modifier
                    .size(backButtonSize)
                    .align(Alignment.Top)
                    .padding(10.dp)
            ) {
                Icon(
                    imageVector = RIcons.ArrowBack,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary
                )
            }

            // Логотип
            Image(
                painter = painterResource(lang.getLogo()),
                contentDescription = lang.name,
                modifier = Modifier
                    .size(logoSize)
            )

            // Название и парадигма
            Column(
                modifier = Modifier
                    .padding(start = 8.dp),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = lang.name,
//                    style = MaterialTheme.typography.displayMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary,
                    fontSize = nameFontSize,
                    maxLines = 1
                )
                Text(
                    text = lang.paradigms.getStringParadigms(stringArrayResource(Res.array.paradigms))[0],
//                    style = MaterialTheme.typography.headlineSmall,
                    fontSize = paradigmFontSize,
                    maxLines = 1
                )
            }

            Spacer(modifier = Modifier.weight(1f))

            val years =
                Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).year - lang.developmentStartYear
            YearsContent(
                modifier = Modifier,
                years = years,
                text = pluralStringResource(Res.plurals.years, years),
                fontSize = yearsFontSize
            )
        }
    }
}


@Composable
fun animateSpAsState(
    targetValue: TextUnit,
    animationSpec: AnimationSpec<Float> = tween()
): State<TextUnit> {
    val animatedValue = animateFloatAsState(targetValue.value, animationSpec)
    return derivedStateOf { animatedValue.value.sp }
}


@Composable
fun YearsContent(
    modifier: Modifier = Modifier,
    years: Int,
    text: String,
    fontSize: TextUnit
) {


    val color = MaterialTheme.colorScheme.onPrimaryContainer
    Column(
        modifier = modifier
            .background(
                Brush.linearGradient(
                    listOf(color, color)
                ), CircleShape, alpha = 0.6f
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
            color = MaterialTheme.colorScheme.onPrimary,
//            fontSize = fontSize
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