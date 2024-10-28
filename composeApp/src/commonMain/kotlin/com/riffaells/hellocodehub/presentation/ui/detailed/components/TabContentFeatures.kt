package com.riffaells.hellocodehub.presentation.ui.detailed.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.riffaells.hellocodehub.domain.model.ProgrammingLanguage
import com.riffaells.hellocodehub.presentation.ui.component.TitleText
import hellocodehub.composeapp.generated.resources.*
import hellocodehub.composeapp.generated.resources.Res
import hellocodehub.composeapp.generated.resources.community
import hellocodehub.composeapp.generated.resources.future
import hellocodehub.composeapp.generated.resources.paradigms
import org.jetbrains.compose.resources.pluralStringResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun TabContentFeatures(lang: ProgrammingLanguage) {

    TitleChips(
        text = stringResource(Res.string.features, lang.features.size,),
        lst = lang.features,
    )

    TitleText(
        title = stringResource(Res.string.designStyle),
    ) {
        Column(
            modifier = Modifier.padding(8.dp),
        ) {
            Text(
                modifier = Modifier.padding(8.dp),
                text = lang.designStyle,
            )

            TitleChips(
                text = pluralStringResource(Res.plurals.creators, lang.creators.size,),
                lst = lang.creators,
            )

            TitleChips(
                text = stringResource(Res.string.compatibility),
                lst = lang.compatibility,
            )
            TitleChips(
                text = stringResource(Res.string.popularFrameworks),
                lst = lang.popularFrameworks,
            )

            TitleChips(
                text = pluralStringResource(Res.plurals.paradigms, lang.paradigms.paradigms.size),
                lst = lang.paradigms.getStringParadigms().map { stringResource(it) },
            )


            TitleChips(
                text = stringResource(Res.string.ideSupport),
                lst = lang.ideSupport,
            )
            TitleChips(
                text = stringResource(Res.string.relatedLanguages),
                lst = lang.relatedLanguages,
            )
        }
    }
    TitleMap(
        text = stringResource(Res.string.fileExtensions),
        map = lang.fileExtensions,

        )


    TitleMap(
        text = stringResource(Res.string.community),
        map = lang.community,
    )






    TitleText(
        title = stringResource(Res.string.future),
    ) {
        Column {
            Text(
                modifier = Modifier.padding(8.dp),
                text = lang.future
            )
        }
    }


}


@Composable
fun TitleMap(
    modifier: Modifier = Modifier,
    map: Map<String, String>,
    text: String,
) {

    TitleText(
        title = text
    ) {
        Column(
            modifier = modifier
                .padding(8.dp),
        ) {

            for ((key, value) in map) {

                Text(
                    modifier = Modifier,
                    text = styleText("<bold>${key}</bold> - $value"),
                )

            }
        }

    }

}


@OptIn(ExperimentalLayoutApi::class)
@Composable
fun TitleChips(
    modifier: Modifier = Modifier,
    text: String,
    lst: List<String>,
) {


    FlowRow(
        modifier = modifier,
    ) {
        Text(
            modifier = Modifier.padding(start = 4.dp).align(Alignment.CenterVertically),
            text = text,
            fontWeight = FontWeight.Bold
        )

        for (l in lst) {
            SuggestionChip(
                modifier = Modifier.padding(start = 4.dp),
                onClick = {

                },
                label = {
                    Text(
                        text = l,
                    )
                },
                shape = MaterialTheme.shapes.medium,
            )

        }

    }


}
