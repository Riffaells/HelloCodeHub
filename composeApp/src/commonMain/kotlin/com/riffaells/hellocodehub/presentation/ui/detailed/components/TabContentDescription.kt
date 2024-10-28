package com.riffaells.hellocodehub.presentation.ui.detailed.components

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import com.riffaells.hellocodehub.domain.model.CodeColor
import com.riffaells.hellocodehub.domain.model.ProgrammingLanguage
import com.riffaells.hellocodehub.presentation.ui.component.TitleText
import hellocodehub.composeapp.generated.resources.*
import org.jetbrains.compose.resources.Font
import org.jetbrains.compose.resources.FontResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun TabContentDescription(lang: ProgrammingLanguage) {
    Text(
        modifier = Modifier.padding(4.dp),
        text = styleText(lang.description),

        )


    TitleText(
        title = "${stringResource(Res.string.hello_world)} ${lang.name}",

        ) {
        Column(
            modifier = Modifier.padding(4.dp)
        ) {
            TitleText(
                title = stringResource(Res.string.name_origin),
                style = MaterialTheme.typography.headlineSmall,

                ) {
                Text(
                    modifier = Modifier.padding(start = 4.dp),
                    text = lang.history.nameOrigin
                )
            }

            TitleText(
                title = stringResource(Res.string.development_start),
                style = MaterialTheme.typography.headlineSmall,
            ) {
                Text(
                    modifier = Modifier.padding(start = 4.dp),
                    text = lang.history.developmentStart
                )
            }

            TitleText(
                title = stringResource(Res.string.first_release),
                style = MaterialTheme.typography.headlineSmall,
            ) {
                Text(
                    modifier = Modifier.padding(start = 4.dp),
                    text = lang.history.firstRelease
                )
            }
        }
    }



    TitleText(
        title = "${stringResource(Res.string.hello_world)} ${lang.name}",

        ) {

        HighlightedCode(text = lang.code)
    }



    Icon(
        imageVector = Icons.Default.Build,
        contentDescription = "Год начала разработки"
    )


//    Icon(
//        imageVector = Icons.Default.Code,
//        contentDescription = "Год начала разработки"
//    )
//
//
//
//    Icon(
//        imageVector = Icons.Default.Construction,
//        contentDescription = "Год первой версии"
//    )
//
//
//    Icon(
//        imageVector = Icons.Default.Publish,
//        contentDescription = "Год первой версии"
//    )
//
//
//    Icon(
//        imageVector = Icons.Default.Update,
//        contentDescription = "Год первой версии"
//    )

}


@Composable
fun HighlightedCode(
    modifier: Modifier = Modifier,
    text: String,
    codeColor: CodeColor = CodeColor(),
    font: FontResource = Res.font.JetBrainsMono,
    fontItalic: FontResource = Res.font.JetBrainsMono_Italic
) {
    val code = text.trimIndent()

    val annotatedString = buildAnnotatedString {

        val tagPattern = Regex("<(\\w+)>(.*?)</\\1>")
        var lastIndex = 0
        withStyle(
            style = SpanStyle(
                color = codeColor.defaultColor
            ),
        ) {
            // Поиск и стилизация по тегам
            tagPattern.findAll(code).forEach { match ->
                // Добавляем текст перед тегом
                append(code.substring(lastIndex, match.range.first))

                // Определяем тип тега и применяем стиль
                val tag = match.groups[1]!!.value
                val content = match.groups[2]!!.value
                when (tag) {
                    "rkey" -> {
                        withStyle(
                            style = SpanStyle(
                                color = codeColor.keywords,
                                fontWeight = FontWeight.Bold,
                                fontStyle = FontStyle.Italic,
                                fontFamily = FontFamily(Font(fontItalic)),
                            )
                        ) {
                            append(content)
                        }
                    }

                    "rfunc" -> {
                        withStyle(style = SpanStyle(color = codeColor.function)) {
                            append(content)
                        }
                    }

                    "rstr" -> {
                        withStyle(style = SpanStyle(color = codeColor.strings)) {
                            append(content)
                        }
                    }

                    "rnum" -> {
                        withStyle(style = SpanStyle(color = codeColor.nums)) {
                            append(content)
                        }
                    }
                    "rstatic" -> {
                        withStyle(style = SpanStyle(color = codeColor.static)) {
                            append(content)
                        }
                    }

                    else -> {
                        append(content) // Если тег не распознан, просто добавляем текст
                    }
                }
                lastIndex = match.range.last + 1
            }

            // Добавляем остаток строки после последнего тега
            if (lastIndex < code.length) {
                append(code.substring(lastIndex))
            }
        }
    }

    // Отображение текста с раскраской


    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(
            containerColor = codeColor.background,
        )
    ) {
        SelectionContainer(
            modifier = Modifier
                .horizontalScroll(rememberScrollState()),
        ) {
            Text(
                modifier = Modifier
                    .padding(16.dp),
                text = annotatedString,
                style = MaterialTheme.typography.titleMedium,

                fontFamily = FontFamily(Font(font))

            )
        }
    }

}


fun styleText(
    text: String,
): AnnotatedString {
    val code = text.trimIndent()

    val annotatedString = buildAnnotatedString {

        val tagPattern = Regex("<(\\w+)>(.*?)</\\1>")
        var lastIndex = 0
        // Поиск и стилизация по тегам
        tagPattern.findAll(code).forEach { match ->
            // Добавляем текст перед тегом
            append(code.substring(lastIndex, match.range.first))

            // Определяем тип тега и применяем стиль
            val tag = match.groups[1]!!.value
            val content = match.groups[2]!!.value
            when (tag) {
                "bold" -> {
                    withStyle(
                        style = SpanStyle(

                            fontWeight = FontWeight.Bold
                        )
                    ) {
                        append(content)
                    }
                }

                "italic" -> {
                    withStyle(
                        style = SpanStyle(

                            fontStyle = FontStyle.Italic
                        )
                    ) {
                        append(content)
                    }
                }

                else -> {
                    append(content) // Если тег не распознан, просто добавляем текст
                }
            }
            lastIndex = match.range.last + 1
        }

        // Добавляем остаток строки после последнего тега
        if (lastIndex < code.length) {
            append(code.substring(lastIndex))
        }
    }

    return annotatedString
}


