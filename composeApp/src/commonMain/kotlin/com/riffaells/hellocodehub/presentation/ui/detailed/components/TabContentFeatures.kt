package com.riffaells.hellocodehub.presentation.ui.detailed.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.riffaells.hellocodehub.domain.model.ProgrammingLanguage
import com.riffaells.hellocodehub.presentation.ui.component.TitleText
import hellocodehub.composeapp.generated.resources.Res
import hellocodehub.composeapp.generated.resources.paradigms
import org.jetbrains.compose.resources.stringResource

@Composable
fun TabContentFeatures(lang: ProgrammingLanguage) {

        TitleText(
            title = stringResource(Res.string.paradigms),

        ) {
            val paradigms = lang.paradigms.getStringParadigms().map { stringResource(it) }.joinToString("\n ∘ ", " ∘ ")
            Text(text = paradigms)
        }


}

