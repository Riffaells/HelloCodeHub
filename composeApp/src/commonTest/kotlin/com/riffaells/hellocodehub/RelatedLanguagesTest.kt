package com.riffaells.hellocodehub

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.runComposeUiTest
import androidx.compose.ui.unit.dp
import com.riffaells.hellocodehub.domain.model.ProgrammingLanguage
import com.riffaells.hellocodehub.presentation.ui.root.loadResourceText
import kotlinx.serialization.json.Json
import org.jetbrains.compose.resources.ExperimentalResourceApi
import kotlin.test.Test
import kotlin.test.assertTrue
@OptIn(ExperimentalResourceApi::class)
class RelatedLanguagesConsistencyTest {

    @OptIn(ExperimentalTestApi::class)
    @Test
    fun checkRelatedLanguagesConsistency() = runComposeUiTest {
        // Определяем missingLanguages вне setContent
        var missingLanguages by mutableStateOf(setOf<String>())

        setContent {
            // Используем missingLanguages внутри setContent
            val localMissingLanguages = missingLanguages

            LaunchedEffect(Unit) {
                // Загрузка JSON и десериализация
                val jsonContent = loadResourceText("files/lang-ru.json")
                val languages = Json.decodeFromString<List<ProgrammingLanguage>>(jsonContent)

                // Получение списка всех доступных языков
                val allLanguages = languages.map { it.name }.toSet()

                // Набор отсутствующих языков в relatedLanguages
                val missingLanguagesSet = mutableSetOf<String>()

                languages.forEach { language ->
                    language.relatedLanguages.forEach { relatedLang ->
                        // Добавление отсутствующих языков в набор
                        if (relatedLang !in allLanguages) {
                            missingLanguagesSet.add(relatedLang)
                        }
                    }
                }

                // Обновление состояния missingLanguages
                missingLanguages = missingLanguagesSet
            }

            // Отображение отсутствующих языков
            SelectionContainer {
                Text(
                    text = if (missingLanguages.isNotEmpty()) missingLanguages.joinToString(", ") else "",
                    modifier = Modifier.testTag("t_text")
                )
            }
        }

        // Ждем обновления состояния UI перед проверкой
        waitForIdle()

        // Теперь можно получить доступ к missingLanguages.value в тесте
        assertTrue(missingLanguages.isEmpty(), "Found missing languages: ${missingLanguages.joinToString(", ")}")
    }
}
