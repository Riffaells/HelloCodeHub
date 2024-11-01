package com.riffaells.hellocodehub.presentation.ui.detailed.components

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.appendInlineContent
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.text.*
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.riffaells.hellocodehub.domain.model.CodeColor
import com.riffaells.hellocodehub.domain.model.ProgrammingLanguage
import com.riffaells.hellocodehub.presentation.theme.RIcons
import com.riffaells.hellocodehub.presentation.ui.component.TitleText
import hellocodehub.composeapp.generated.resources.*
import org.jetbrains.compose.resources.Font
import org.jetbrains.compose.resources.FontResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.resources.vectorResource

@Composable
fun TabContentDescription(lang: ProgrammingLanguage) {
    Text(
        modifier = Modifier.padding(4.dp),
        text = styleText(lang.description),

        )

    TitleText(
        title = stringResource(Res.string.name_origin),
        style = MaterialTheme.typography.headlineSmall,

        ) {
        Text(
            modifier = Modifier.padding(4.dp),
            text = lang.history.nameOrigin
        )
    }

    TitleText(
        title = stringResource(Res.string.development_start),
        style = MaterialTheme.typography.headlineSmall,
    ) {
        Text(
            modifier = Modifier.padding(4.dp),
            text = lang.history.developmentStart
        )
    }

    TitleText(
        title = stringResource(Res.string.first_release),
        style = MaterialTheme.typography.headlineSmall,
    ) {
        Text(
            modifier = Modifier.padding(4.dp),
            text = lang.history.firstRelease
        )
    }

    TitleText(
        title = "${stringResource(Res.string.hello_world)} ${lang.name}",

        ) {

        HighlightedCode(text = lang.code)
    }


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
        Box {
            IconButton(
                modifier = Modifier.padding(4.dp).align(Alignment.TopEnd).alpha(0.7f),
                onClick = {}
            ) {
                Icon(
                    imageVector = RIcons.Copy,
                    contentDescription = null
                )
            }
            SelectionContainer(
                modifier = Modifier
                    .padding(16.dp, 20.dp)
                    .horizontalScroll(rememberScrollState()),
            ) {
                Text(
                    modifier = Modifier,
                    text = annotatedString,
                    style = MaterialTheme.typography.titleMedium,

                    fontFamily = FontFamily(Font(font))

                )
            }
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


@Composable
fun styleImg(
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
                "prosId" -> {
                    appendInlineContent("prosId", "[pros]")
                }

                "consId" -> {
                    appendInlineContent("consId", "[cons]")
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


