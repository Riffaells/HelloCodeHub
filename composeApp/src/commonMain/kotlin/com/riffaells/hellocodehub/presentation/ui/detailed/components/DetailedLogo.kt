package com.riffaells.hellocodehub.presentation.ui.detailed.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.riffaells.hellocodehub.domain.model.ProgrammingLanguage
import com.riffaells.hellocodehub.presentation.theme.RIcons
import org.jetbrains.compose.resources.imageResource
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun DetailedLogo(
    modifier: Modifier = Modifier,
    lang: ProgrammingLanguage,
    onBack: () -> Unit,

    ) {

    Box {
        IconButton(
            modifier = Modifier.padding(4.dp),
            onClick = { onBack() },
            colors = IconButtonDefaults.iconButtonColors(

            )
        ) {
            Image(RIcons.ArrowBack, contentDescription = null, colorFilter =  ColorFilter.tint(MaterialTheme.colorScheme.primary))
        }

        Column(
            modifier = modifier
                .fillMaxWidth()

                .padding(12.dp, 30.dp)
                .zIndex(1f)
                .align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {


            Image(
                modifier = Modifier
                    .sizeIn(minWidth = 48.dp, minHeight = 48.dp),
                painter = painterResource(lang.getLogo()),
                contentDescription = lang.name
            )

            Text(
                text = lang.name,
                style = MaterialTheme.typography.displayMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            )
            Text(
                text = stringResource(lang.paradigms.getStringParadigms()[0]),
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold,
            )

        }
    }
}