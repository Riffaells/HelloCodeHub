package com.riffaells.hellocodehub.presentation.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.unit.dp
import com.riffaells.hellocodehub.domain.model.ProgrammingLanguage
import com.riffaells.hellocodehub.presentation.ui.detailed.components.HighlightedCode
import org.jetbrains.compose.resources.painterResource


@Composable
fun LangCodeCard(
    modifier: Modifier = Modifier,
    lang: ProgrammingLanguage,
    onClick: () -> Unit
) {
    Surface(
        modifier = modifier
            .padding(8.dp)
            .sizeIn(maxWidth = 700.dp)
            .fillMaxWidth(),
        tonalElevation = 5.dp,
        shape = MaterialTheme.shapes.medium,
    ) {


        Row(
            modifier = Modifier
//                .clickable {
//                    onClick()
//                }
                .background(
                    Brush.linearGradient(
                        colors = lang.getColors(),
                        start = Offset(0f, Float.POSITIVE_INFINITY),  // Левый нижний угол
                        end = Offset(Float.POSITIVE_INFINITY, 0f)     // Правый верхний угол
                    ),
                    alpha = 0.1f
                ),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Image(
                modifier = Modifier
                    .sizeIn(maxHeight = 100.dp)
                    .padding(8.dp),
                painter = painterResource(lang.getLogo()),
                contentDescription = null,
                alignment = Alignment.CenterStart
            )
            Column {

                HighlightedCode(
                    text = lang.code,
                )

            }
        }

    }
}