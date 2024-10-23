package com.riffaells.hellocodehub.presentation.ui.main

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.riffaells.hellocodehub.domain.components.main.MainComponent
import com.riffaells.hellocodehub.domain.components.root.RootStore
import com.riffaells.hellocodehub.presentation.ui.component.LangCard

@Composable
fun MainContent(
    modifier: Modifier = Modifier,
    stateRoot: RootStore.State,
    component: MainComponent

) {


    Scaffold(
        modifier = modifier,
    ) {

        if (stateRoot.languages.isNotEmpty()) {
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


    }

}

