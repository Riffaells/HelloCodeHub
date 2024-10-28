package com.riffaells.hellocodehub.presentation.ui.detailed.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.riffaells.hellocodehub.domain.model.ProgrammingLanguage
import com.riffaells.hellocodehub.presentation.theme.RIcons
import hellocodehub.composeapp.generated.resources.Res
import hellocodehub.composeapp.generated.resources.cons
import hellocodehub.composeapp.generated.resources.pros
import org.jetbrains.compose.resources.stringResource

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun TabContentProsCons(lang: ProgrammingLanguage) {
    FlowRow(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {

        val pros = lang.prosAndCons.pros
        val cons = lang.prosAndCons.cons

        CardNote(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .weight(1f),
            title = stringResource(Res.string.pros),
            vector = RIcons.ThumbUp,
            subImg = RIcons.Check,
            lst = pros,
            colors = listOf(
                "#04ff00",
                "#00ff48",
                "#00ff6e",
            )
        )
        CardNote(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .weight(1f),
            title = stringResource(Res.string.cons),
            vector = RIcons.ThumbDown,
            subImg = RIcons.Close,
            lst = cons,
            colors = listOf(
                "#ff0000",
                "#ff1e00",
                "#ff4800",
            )
        )
    }
}


@Composable
fun CardNote(
    modifier: Modifier = Modifier,
    title: String,
    vector: ImageVector,
    subImg: ImageVector,
    lst: List<String>,
    colors: List<String>

) {
    Box(
        modifier = modifier
    ) {

        Surface(
            modifier = Modifier
                .sizeIn(minWidth = 200.dp)
                .padding(4.dp),
            shape = MaterialTheme.shapes.medium,
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        Brush.linearGradient(
                            colors = colors.map { ProgrammingLanguage.hexToColor(it) },


                            ),
                        alpha = 0.3f
                    ),
//            horizontalAlignment = Alignment.CenterHorizontally

            ) {
                Row(
                    modifier = Modifier,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Icon(
                        modifier = Modifier.padding(4.dp),
                        imageVector = vector,
                        contentDescription = title,

                        )

                    Text(
                        text = title,
                        style = MaterialTheme.typography.headlineMedium,
                    )
                }

                for (item in lst) {
                    Row(
                        modifier = Modifier.padding(8.dp),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Icon(
                            modifier = Modifier.padding(2.dp),
                            imageVector = subImg,
                            contentDescription = title,
                            tint = MaterialTheme.colorScheme.primary,
                        )

                        Text(
                            text = item,
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Normal,
                        )
                    }
                }
            }

        }
    }
}