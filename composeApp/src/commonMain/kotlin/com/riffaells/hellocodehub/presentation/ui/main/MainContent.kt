package com.riffaells.hellocodehub.presentation.ui.main

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.riffaells.hellocodehub.domain.components.main.MainComponent
import com.riffaells.hellocodehub.domain.components.root.RootComponent
import com.riffaells.hellocodehub.domain.components.root.RootStore
import com.riffaells.hellocodehub.domain.model.ProgrammingLanguage
import com.riffaells.hellocodehub.presentation.ui.component.LangCard
import hello_code_hub.composeapp.generated.resources.Res
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.InternalResourceApi

@OptIn(ExperimentalResourceApi::class, InternalResourceApi::class)
@Composable
fun MainContent(
    modifier: Modifier = Modifier,
    stateRoot: RootStore.State,
    component: MainComponent

) {



    Scaffold(
        modifier = modifier,
    ) {
        if (stateRoot.languages.isNotEmpty()){
            LazyColumn(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                items(items = stateRoot.languages) { lang ->
                    LangCard(
                        lang = lang,
                        onClick = {
                            component.onLangDetailedClicked(lang)
                        }
                    )
                }
            }
        } else {
            Text("Загрузка")
        }

        Button(
            onClick = {
                println(stateRoot.languages.size)
            }
        ){

        }
    }

}

