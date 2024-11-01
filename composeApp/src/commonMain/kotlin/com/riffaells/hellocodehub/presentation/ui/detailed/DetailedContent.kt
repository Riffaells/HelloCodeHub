package com.riffaells.hellocodehub.presentation.ui.detailed

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.riffaells.hellocodehub.domain.components.detailed.DetailedComponent
import com.riffaells.hellocodehub.domain.components.detailed.DetailedStore
import com.riffaells.hellocodehub.domain.components.root.RootStore
import com.riffaells.hellocodehub.presentation.theme.RIcons
import com.riffaells.hellocodehub.presentation.ui.detailed.components.DetailedLogo
import com.riffaells.hellocodehub.presentation.ui.detailed.components.TabContentDescription
import com.riffaells.hellocodehub.presentation.ui.detailed.components.TabContentFeatures
import com.riffaells.hellocodehub.presentation.ui.detailed.components.TabContentProsCons
import hellocodehub.composeapp.generated.resources.Res
import hellocodehub.composeapp.generated.resources.detailed_tabs
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.imageResource
import org.jetbrains.compose.resources.stringArrayResource


@Composable
fun DetailedContent(
    stateRoot: RootStore.State,
    component: DetailedComponent,
) {
    val state by component.state.collectAsState()
    val lang = component.lang


    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }


    val tabs = stringArrayResource(Res.array.detailed_tabs)
    val tabsIcons = listOf(RIcons.Description, RIcons.Features, RIcons.ProsCons)
    val pagerState = rememberPagerState {
        tabs.size
    }


    LaunchedEffect(key1 = state.tabIndex) {
        pagerState.animateScrollToPage(state.tabIndex)
    }

    LaunchedEffect(key1 = pagerState.currentPage, pagerState.isScrollInProgress) {
        if (!pagerState.isScrollInProgress)
            component.onEvent(DetailedStore.Intent.UpdateIndex(pagerState.currentPage))
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {


        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.linearGradient(
                        colors = lang.getColors(),


                        ),
                    alpha = 0.2f
                )
                .padding(top = 36.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            DetailedLogo(
                lang = lang,
                onBack = {
                    component.onBackClicked()
                },
                isRowLayout = false,
            )

            Surface(
                modifier = Modifier
                    .sizeIn(maxWidth = 1000.dp)
                    .fillMaxSize()
                    .zIndex(0f),
                shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp),
                color = MaterialTheme.colorScheme.surface
            ) {
                Column(
                    modifier = Modifier
                ) {


                    TabRow(
                        selectedTabIndex = state.tabIndex,
                        modifier = Modifier,
                    ) {

                        tabs.forEachIndexed { index, title ->
                            Tab(
                                modifier = Modifier,
                                selected = state.tabIndex == index,
                                onClick = {
                                    component.onEvent(DetailedStore.Intent.UpdateIndex(index))
                                },
                                text = {
                                    Row(
                                        modifier = Modifier.fillMaxWidth(),
                                        verticalAlignment = Alignment.CenterVertically,
                                        horizontalArrangement = Arrangement.Center
                                    ) {
                                        Icon(
                                            modifier = Modifier
                                                .padding(4.dp),
                                            imageVector = tabsIcons[index],
                                            contentDescription = title
                                        )
                                        Text(
                                            modifier = Modifier,
                                            text = title,
                                            maxLines = 1,
                                        )
                                    }
                                },
                            )
                        }
                    }
                    HorizontalPager(

                        state = pagerState,
                        modifier = Modifier
                            .fillMaxWidth()
                    ) { index ->

                        val mod =

                            LazyColumn(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(10.dp)
                            ) {
                                item {
                                    when (index) {
                                        0 -> TabContentDescription(lang)
                                        1 ->
                                            TabContentFeatures(
                                                lang = lang,
                                                onLang = { lang ->
                                                    val l = stateRoot.languages.firstOrNull { it.name == lang }
                                                    l?.let {
                                                        component.onLangDetailedClicked(it)
                                                    } ?: scope.launch {
                                                        val result = snackbarHostState.showSnackbar(
                                                            "Такого языка еще нету",
                                                            duration = SnackbarDuration.Short
                                                        )
                                                        when (result) {
                                                            SnackbarResult.ActionPerformed -> {}
                                                            SnackbarResult.Dismissed -> {
                                                                snackbarHostState.showSnackbar("Действие отменено")
                                                            }
                                                        }
                                                    }
                                                }
                                            )


                                        2 -> TabContentProsCons(lang)
                                    }
                                }
                            }
                    }
                }


            }
        }

        SnackbarHost(
            modifier = Modifier
                .align(Alignment.BottomCenter),
            hostState = snackbarHostState,
            snackbar = { snackbar ->
                Surface(
                    modifier = Modifier
                        .padding(4.dp),
                    color = MaterialTheme.colorScheme.surface,
                    shape = MaterialTheme.shapes.large,
                ) {

                    Row(
                        verticalAlignment = Alignment.CenterVertically,

                        ) {
                        Text(
                            text = snackbar.visuals.message
                        )
                    }

                }
            }
        )
    }
}
