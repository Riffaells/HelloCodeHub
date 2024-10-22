package com.riffaells.hellocodehub.presentation.ui.detailed

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.key.Key.Companion.R
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.riffaells.hellocodehub.domain.components.detailed.DetailedComponent
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.painterResource


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailedContent(
    component: DetailedComponent
) {
    val state by component.state.collectAsState()
    val lang = component.lang

    val scope = rememberCoroutineScope()
    val scaffoldState = rememberBottomSheetScaffoldState()

    Scaffold(
        topBar = {

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.onBackground)
                    .padding(24.dp),
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

            }
        }

    ) { padding ->



        Column(
            modifier = Modifier
                .padding(padding)
        ) {

            Text(component.lang.name, style = MaterialTheme.typography.headlineSmall)

        }


    }

}