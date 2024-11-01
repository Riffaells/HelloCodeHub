package com.riffaells.hellocodehub.presentation.ui.detailed.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.InlineTextContent
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.Placeholder
import androidx.compose.ui.text.PlaceholderVerticalAlign
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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

        val prosImg = mapOf(
            "prosId" to InlineTextContent(
                Placeholder(20.sp, 20.sp, PlaceholderVerticalAlign.TextCenter)
            ) {
                Icon(
                    imageVector = RIcons.Check,
                    modifier = Modifier.fillMaxSize(),
                    contentDescription = ""
                )
            }
        )
        val consImg = mapOf(
            "consId" to InlineTextContent(
                Placeholder(20.sp, 20.sp, PlaceholderVerticalAlign.TextCenter)
            ) {
                Icon(
                    imageVector = RIcons.Close,
                    modifier = Modifier.fillMaxSize(),
                    contentDescription = ""
                )
            }
        )

        CardNote(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .weight(1f),
            title = stringResource(Res.string.pros),
            vector = RIcons.ThumbUp,
            inlineContent = prosImg,
            lst = pros,
            colors = listOf(
                "#04ff00",
                "#00ff48",
                "#00ff6e",
            ),
            textImg="prosId"
        )
        CardNote(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .weight(1f),
            title = stringResource(Res.string.cons),
            vector = RIcons.ThumbDown,
            inlineContent = consImg,
            lst = cons,
            colors = listOf(
                "#ff0000",
                "#ff1e00",
                "#ff4800",
            ),
            textImg="consId"
        )
    }
}


@Composable
fun CardNote(
    modifier: Modifier = Modifier,
    title: String,
    vector: ImageVector,
    inlineContent:  Map<String, InlineTextContent>,
    lst: List<String>,
    colors: List<String>,
    textImg: String = "consId"

) {
    Box(
        modifier = modifier
    ) {

        Surface(
            modifier = Modifier
                .sizeIn(minWidth = 220.dp)
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

                val text = styleImg(lst.joinToString("\n<$textImg></$textImg>", "<$textImg></$textImg>"))

                Text(
                    modifier = Modifier.padding(4.dp),
                    text = text,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Normal,
                    inlineContent = inlineContent
                )

//
//                for (item in lst) {
//                    Row(
//                        modifier = Modifier.padding(8.dp),
//                        verticalAlignment = Alignment.CenterVertically,
//                    ) {
//                        Icon(
//                            modifier = Modifier.padding(2.dp),
//                            imageVector = subImg,
//                            contentDescription = title,
//                            tint = MaterialTheme.colorScheme.primary,
//                        )
//
//                        Text(
//                            text = item,
//                            style = MaterialTheme.typography.titleMedium,
//                            fontWeight = FontWeight.Normal,
//                        )
//                    }
//                }
            }

        }
    }
}