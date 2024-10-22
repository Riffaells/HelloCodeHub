package com.riffaells.hellocodehub.presentation.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.riffaells.hellocodehub.domain.model.ProgrammingLanguage
import org.jetbrains.compose.resources.painterResource


@Composable
fun LangCard(
    modifier: Modifier = Modifier,
    lang: ProgrammingLanguage,
    onClick: () -> Unit
) {
    Card(
        modifier = modifier
            .padding(4.dp)
            .sizeIn(maxWidth = 700.dp)
            .fillMaxWidth()
            .clickable {
                onClick()
            }
    ) {
        Box(
            modifier = Modifier.padding(8.dp)
        ) {


            Row(modifier = Modifier) {
                Image(
                    modifier = Modifier
                        .sizeIn(maxHeight = 70.dp)
                            ,
                    painter = painterResource(lang.getLogo()),
                    contentDescription = null,
                    alignment = Alignment.CenterStart
                )
                Column {
                    Text(

                        lang.name,
                        style = MaterialTheme.typography.headlineMedium,
                    )


                    Text(

                        lang.description,
                        style = MaterialTheme.typography.titleSmall,
                    )

                }
            }
        }
    }
}