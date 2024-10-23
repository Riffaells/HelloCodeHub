package com.riffaells.hellocodehub.presentation.ui.detailed.components

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.riffaells.hellocodehub.domain.model.ProgrammingLanguage

@Composable
fun TabContentFeatures(lang: ProgrammingLanguage) {
    Column {
        Text(text = "Paradigms: ${lang.paradigms.getStringParadigms().joinToString()}")
        Text(text = "Typing: ${lang.typing}")
        Text(text = "Features: ${lang.features.joinToString()}")

        Text(text = "Popular frameworks: ${lang.popularFrameworks.joinToString()}")


        Text(text = "Use cases: ${lang.useCases.joinToString()}")
    }

}

