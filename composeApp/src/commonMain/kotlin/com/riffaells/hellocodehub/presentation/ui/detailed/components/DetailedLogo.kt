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
import androidx.compose.ui.draw.alpha
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
    // Базовые размеры
    val maxHeight = 200.dp
    val minHeight = 70.dp

    // Простые анимации для каждого параметра
    val height by animateDpAsState(
        targetValue = lerp(maxHeight, minHeight, collapseFraction)
    )

    val logoSize by animateDpAsState(
        targetValue = lerp(120.dp, 40.dp, collapseFraction)
    )

    val titleSize by animateFloatAsState(
        targetValue = lerp(28f, 18f, collapseFraction)
    )

    val subtitleSize by animateFloatAsState(
        targetValue = lerp(20f, 14f, collapseFraction)
    )

    Box(
        modifier = modifier
            .height(height)
            .fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Кнопка назад
            IconButton(
                onClick = onBack,
                modifier = Modifier.padding(end = 8.dp)
            ) {
                Icon(
                    imageVector = RIcons.ArrowBack,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary
                )
            }

            // Логотип языка
            Image(
                painter = painterResource(lang.getLogo()),
                contentDescription = lang.name,
                modifier = Modifier.size(logoSize)
            )

            // Информация о языке
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 16.dp)
            ) {
                Text(
                    text = lang.name,
                    fontSize = titleSize.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary,
                    maxLines = 1
                )

                Text(
                    text = lang.paradigms.getStringParadigms(
                        stringArrayResource(Res.array.paradigms)
                    )[0],
                    fontSize = subtitleSize.sp,
                    maxLines = 3
                )
            }

            // Количество лет
            val years = Clock.System.now()
                .toLocalDateTime(TimeZone.currentSystemDefault())
                .year - lang.developmentStartYear

            YearsContent(
                years = years,
                text = pluralStringResource(Res.plurals.years, years)
            )
        }
    }
}

@Composable
private fun YearsContent(
    years: Int,
    text: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(
                        MaterialTheme.colorScheme.onPrimaryContainer,
                        MaterialTheme.colorScheme.onPrimaryContainer
                    )
                ),
                shape = CircleShape,
                alpha = 0.6f
            )
            .padding(horizontal = 16.dp, vertical = 8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = years.toString(),
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onPrimary
        )

        Text(
            text = text,
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.onPrimary
        )
    }
}
