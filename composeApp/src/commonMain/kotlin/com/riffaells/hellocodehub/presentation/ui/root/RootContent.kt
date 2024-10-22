package com.riffaells.hellocodehub.presentation.ui.root

import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.riffaells.hellocodehub.domain.components.root.DefaultRootComponent
import com.riffaells.hellocodehub.domain.components.root.RootStore
import com.riffaells.hellocodehub.domain.model.ProgrammingLanguage
import com.riffaells.hellocodehub.presentation.theme.AppTheme
import hello_code_hub.composeapp.generated.resources.Res
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import org.jetbrains.compose.resources.ExperimentalResourceApi

@Composable
fun RootContent(
    modifier: Modifier = Modifier,
    component: DefaultRootComponent,
    content: @Composable (DefaultRootComponent) -> Unit
) {


    LaunchedEffect(Unit) {
        val jsonContent = loadResourceText("files/lang-ru.json")
        val l = Json.decodeFromString<List<ProgrammingLanguage>>(jsonContent)
        component.onEvent(RootStore.Intent.UpdateLang(l)) // Десериализация JSON

    }

    AppTheme(
//        theme = theme
    ) {
        content(component)
    }

}



@OptIn(ExperimentalResourceApi::class)
suspend fun loadResourceText(resourcePath: String): String {
    return withContext(Dispatchers.Unconfined) {
        // Чтение байтов из ресурса
        val resourceBytes = Res.readBytes(resourcePath)
        // Преобразование байтов в строку
        resourceBytes.decodeToString()
    }
}