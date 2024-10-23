package com.riffaells.hellocodehub.presentation.ui.detailed.components

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.riffaells.hellocodehub.domain.model.ProgrammingLanguage

@Composable
fun TabContentProsCons(lang: ProgrammingLanguage) {
    Column {
        Text(text = "Pros: ${lang.prosAndCons.pros.joinToString()}")
        Text(text = "Cons: ${lang.prosAndCons.cons.joinToString()}")
    }
}