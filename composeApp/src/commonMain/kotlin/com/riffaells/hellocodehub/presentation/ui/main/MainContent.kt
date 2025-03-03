package com.riffaells.hellocodehub.presentation.ui.main

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.unit.dp
import com.riffaells.hellocodehub.domain.components.main.MainComponent
import com.riffaells.hellocodehub.domain.components.root.RootComponent
import com.riffaells.hellocodehub.domain.components.root.RootStore
import com.riffaells.hellocodehub.presentation.ui.component.LangCard

@Composable
fun MainContent(
    modifier: Modifier = Modifier,
    componentRoot: RootComponent,
    stateRoot: RootStore.State,
    component: MainComponent

) {

    var filterState by remember { mutableStateOf(true)}

    var allM = modifier

    stateRoot.currentLanguage?.let {
        allM = modifier.background(
        Brush.linearGradient(
            colors = it.getColors().reversed(),


            ),
        alpha = 0.2f
    )
    }

    Column(
        modifier = allM
            .padding(top = 36.dp, start = 8.dp, end = 8.dp)

    ) {


        Row {
            SuggestionChip(
                modifier = Modifier.padding(start = 4.dp).align(Alignment.CenterVertically),
                onClick = { filterState = !filterState },
                label = { Text(text = "Filter") },
                shape = MaterialTheme.shapes.small,
            )

        }

        AnimatedVisibility(
            filterState
        ) {


        }


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
                            componentRoot.onEvent(RootStore.Intent.CurrentLang(lang))
                        }
                    )
//
//                    LangCodeCard(
//                        lang = lang,
//                        onClick = {
//                            component.onLangDetailedClicked(lang)
//                        }
//                    )
                }
            }
        } else {
            Text("Загрузка")
        }

    }


}
