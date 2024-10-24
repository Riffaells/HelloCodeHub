package com.riffaells.hellocodehub.presentation.ui.detailed.components

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
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
import hellocodehub.composeapp.generated.resources.JetBrainsMono
import hellocodehub.composeapp.generated.resources.JetBrainsMono_Italic
import hellocodehub.composeapp.generated.resources.Res
import org.jetbrains.compose.resources.Font
import org.jetbrains.compose.resources.FontResource

@Composable
fun TabContentDescription(lang: ProgrammingLanguage) {
    Text(
        modifier = Modifier.padding(8.dp),
        text = styleText(lang.description),

        )


    HighlightedCode(text = lang.code)

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
        modifier = Modifier,
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


