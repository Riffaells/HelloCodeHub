package com.riffaells.hellocodehub

import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.layout.*
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import com.riffaells.hellocodehub.domain.components.root.RootComponent
import com.riffaells.hellocodehub.presentation.ui.detailed.DetailedContent
import com.riffaells.hellocodehub.presentation.ui.main.MainContent

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun RootContent(
    component: RootComponent,
    modifier: Modifier = Modifier,
) {
    val stateRoot by component.state.collectAsState()
    val dialogSlot by component.detailedSlot.subscribeAsState()

    BoxWithConstraints(
        modifier = modifier.fillMaxSize()
    ) {
        val isWideScreen = maxWidth > 800.dp

        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            if (isWideScreen) {
                Row(modifier = Modifier.fillMaxSize()) {
                    // Первая часть с MainContent
                    AnimatedContent(
                        targetState = dialogSlot.child != null,
                        modifier = Modifier
                            .weight(0.5f),
                    ) {
                        Box(
                            modifier = Modifier
                                .weight(0.5f)
                                .fillMaxHeight()

                        ) {
                            MainContent(
                                stateRoot = stateRoot,
                                component = component.mainComponent,
                                modifier = Modifier
                                    .animateContentSize(
                                        animationSpec = spring(
                                            dampingRatio = Spring.DampingRatioNoBouncy,
                                            stiffness = Spring.StiffnessMedium
                                        )
                                    )
                                    .fillMaxSize()
                            )
                        }
                    }

                    // Детальный контент (DetailedContent) с анимацией
                    AnimatedVisibility(
                        visible = dialogSlot.child != null,
                        modifier = Modifier.weight(0.5f),
                        enter = slideInHorizontally(
                            initialOffsetX = { it / 2 },
                            animationSpec = tween(
                                durationMillis = 400,
                                easing = FastOutSlowInEasing
                            )
                        ) + fadeIn(
                            animationSpec = tween(
                                durationMillis = 400,
                                easing = LinearEasing
                            )
                        ),
                        exit = slideOutHorizontally(
                            targetOffsetX = { it / 4 },
                            animationSpec = tween(
                                durationMillis = 400,
                                easing = FastOutSlowInEasing
                            )
                        ) + fadeOut(
                            animationSpec = tween(
                                durationMillis = 300,
                                easing = LinearEasing
                            )
                        )
                    ) {
                        Row {
                            // Разделитель с анимацией
                            HorizontalDivider(
                                modifier = Modifier
                                    .fillMaxHeight()
                                    .width(1.dp)
                                    .animateEnterExit(
                                        enter = expandHorizontally(
                                            animationSpec = tween(
                                                durationMillis = 300,
                                                easing = LinearOutSlowInEasing
                                            )
                                        ) + fadeIn(
                                            animationSpec = tween(300)
                                        ),
                                        exit = shrinkHorizontally(
                                            animationSpec = tween(
                                                durationMillis = 300,
                                                easing = FastOutLinearInEasing
                                            )
                                        ) + fadeOut(
                                            animationSpec = tween(200)
                                        )
                                    ),
                                color = MaterialTheme.colorScheme.outlineVariant
                            )

                            dialogSlot.child?.instance?.let { detailed ->
                                DetailedContent(
                                    stateRoot = stateRoot,
                                    component = detailed,
                                    modifier = Modifier.fillMaxSize()
                                )
                            }
                        }
                    }
                }
            } else {
                // Мобильная версия (без изменений)
                AnimatedContent(
                    targetState = dialogSlot.child != null,
                    transitionSpec = {
                        if (targetState) {
                            (slideInHorizontally(
                                initialOffsetX = { it },
                                animationSpec = tween(400, easing = FastOutSlowInEasing)
                            ) + fadeIn(
                                animationSpec = tween(350)
                            )).togetherWith(
                                slideOutHorizontally(
                                    targetOffsetX = { -it },
                                    animationSpec = tween(400, easing = FastOutSlowInEasing)
                                ) + fadeOut(
                                    animationSpec = tween(350)
                                )
                            )
                        } else {
                            (slideInHorizontally(
                                initialOffsetX = { -it },
                                animationSpec = tween(400, easing = FastOutSlowInEasing)
                            ) + fadeIn(
                                animationSpec = tween(350)
                            )).togetherWith(
                                slideOutHorizontally(
                                    targetOffsetX = { it },
                                    animationSpec = tween(400, easing = FastOutSlowInEasing)
                                ) + fadeOut(
                                    animationSpec = tween(350)
                                )
                            )
                        }.using(
                            SizeTransform(clip = false)
                        )
                    }
                ) { isDetailed ->
                    if (!isDetailed) {
                        MainContent(
                            stateRoot = stateRoot,
                            component = component.mainComponent,
                            modifier = Modifier.fillMaxSize()
                        )
                    } else {
                        dialogSlot.child?.instance?.let { detailed ->
                            DetailedContent(
                                stateRoot = stateRoot,
                                component = detailed,
                                modifier = Modifier.fillMaxSize()
                            )
                        }
                    }
                }
            }
        }
    }
}
