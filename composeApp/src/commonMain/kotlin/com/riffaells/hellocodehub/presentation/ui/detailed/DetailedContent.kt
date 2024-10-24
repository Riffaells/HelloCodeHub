package com.riffaells.hellocodehub.presentation.ui.detailed

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
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
import com.riffaells.hellocodehub.presentation.theme.RIcons
import com.riffaells.hellocodehub.presentation.ui.detailed.components.DetailedLogo
import com.riffaells.hellocodehub.presentation.ui.detailed.components.TabContentDescription
import com.riffaells.hellocodehub.presentation.ui.detailed.components.TabContentFeatures
import com.riffaells.hellocodehub.presentation.ui.detailed.components.TabContentProsCons
import hellocodehub.composeapp.generated.resources.Res
import hellocodehub.composeapp.generated.resources.detailed_tabs
import org.jetbrains.compose.resources.stringArrayResource


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailedContent(
    component: DetailedComponent
) {
    val state by component.state.collectAsState()
    val lang = component.lang



    val scope = rememberCoroutineScope()


    var tabIndex by remember { mutableStateOf(0) }
    val tabs = stringArrayResource(Res.array.detailed_tabs)
    val tabsIcons = listOf(RIcons.Description, RIcons.Features, RIcons.ProsCons)
    val pagerState = rememberPagerState {
        tabs.size
    }

    val scrollState = rememberScrollState()

    LaunchedEffect(key1 = tabIndex) {
        pagerState.animateScrollToPage(tabIndex)
    }

    LaunchedEffect(key1 = pagerState.currentPage, pagerState.isScrollInProgress) {
        if (!pagerState.isScrollInProgress)
            tabIndex = pagerState.currentPage
    }




    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.linearGradient(
                    colors = lang.getColors(),


                ),
                alpha = 0.2f
            )
    ) {
        DetailedLogo(
            lang = lang,
            onBack = {
                component.onBackClicked()
            }
        )

        Surface(
            modifier = Modifier
                .fillMaxSize()
                .zIndex(0f),
            shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp),
            color = MaterialTheme.colorScheme.surface
        ) {
            Column(
                modifier = Modifier
            ) {


                TabRow(
                    selectedTabIndex = tabIndex,
                    modifier = Modifier,
                ) {

                    tabs.forEachIndexed { index, title ->
                        Tab(
                            modifier = Modifier,
                            selected = tabIndex == index,
                            onClick = { tabIndex = index },
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

                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(10.dp)
                            .verticalScroll(scrollState)
                    ) {
                        when (index) {
                            0 -> TabContentDescription(lang)
                            1 -> TabContentFeatures(lang)
                            2 -> TabContentProsCons(lang)
                        }


                    }
                }


            }
        }
    }


}

