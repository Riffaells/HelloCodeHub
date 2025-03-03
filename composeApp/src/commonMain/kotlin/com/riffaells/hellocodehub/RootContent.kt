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
import androidx.compose.animation.core.EaseInOutCubic

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
            AnimatedContent(
                targetState = Pair(isWideScreen, dialogSlot.child?.instance),
                transitionSpec = {
                    fadeIn(
                        animationSpec = tween(300, easing = EaseInOutCubic)
                    ).togetherWith(
                        fadeOut(
                            animationSpec = tween(300, easing = EaseInOutCubic)
                        )
                    ).using(
                        SizeTransform(clip = false) { _, _ ->
                            spring(
                                dampingRatio = Spring.DampingRatioMediumBouncy,
                                stiffness = Spring.StiffnessLow
                            )
                        }
                    )
                }
            ) { (isWide, detailed) ->
                if (isWide) {
                    Row(
                        modifier = Modifier.fillMaxSize()
                    ) {
                        MainContent(
                            stateRoot = stateRoot,
                            component = component.mainComponent,
                            componentRoot = component,
                            modifier = Modifier
                                .weight(0.5f)
                                .fillMaxSize()
                        )

                        AnimatedVisibility(
                            visible = detailed != null,
                            modifier = Modifier.weight(0.5f),
                            enter = fadeIn(tween(300)) +
                                slideInHorizontally(
                                    animationSpec = tween(300, easing = EaseInOutCubic),
                                    initialOffsetX = { it }
                                ),
                            exit = fadeOut(tween(300)) +
                                slideOutHorizontally(
                                    animationSpec = tween(300, easing = EaseInOutCubic),
                                    targetOffsetX = { it }
                                )
                        ) {
                            Row {
                                HorizontalDivider(
                                    modifier = Modifier
                                        .fillMaxHeight()
                                        .width(1.dp),
                                    color = MaterialTheme.colorScheme.outlineVariant
                                )

                                detailed?.let {
                                    DetailedContent(
                                        stateRoot = stateRoot,
                                        component = it,
                                        componentRoot = component,
                                        modifier = Modifier.fillMaxSize()
                                    )
                                }
                            }
                        }
                    }
                } else {
                    Box(modifier = Modifier.fillMaxSize()) {
                        AnimatedVisibility(
                            visible = detailed == null,
                            enter = fadeIn(tween(300)) +
                                slideInHorizontally(
                                    animationSpec = tween(300, easing = EaseInOutCubic),
                                    initialOffsetX = { -it }
                                ),
                            exit = fadeOut(tween(300)) +
                                slideOutHorizontally(
                                    animationSpec = tween(300, easing = EaseInOutCubic),
                                    targetOffsetX = { -it }
                                )
                        ) {
                            MainContent(
                                stateRoot = stateRoot,
                                component = component.mainComponent,
                                componentRoot = component,
                                modifier = Modifier.fillMaxSize()
                            )
                        }

                        AnimatedVisibility(
                            visible = detailed != null,
                            enter = fadeIn(tween(300)) +
                                slideInHorizontally(
                                    animationSpec = tween(300, easing = EaseInOutCubic),
                                    initialOffsetX = { it }
                                ),
                            exit = fadeOut(tween(300)) +
                                slideOutHorizontally(
                                    animationSpec = tween(300, easing = EaseInOutCubic),
                                    targetOffsetX = { it }
                                )
                        ) {
                            detailed?.let {
                                DetailedContent(
                                    stateRoot = stateRoot,
                                    component = it,
                                    componentRoot = component,
                                    modifier = Modifier.fillMaxSize()
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
