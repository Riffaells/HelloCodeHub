package com.riffaells.hellocodehub

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.arkivanov.decompose.extensions.compose.stack.Children
import com.arkivanov.decompose.extensions.compose.stack.animation.fade
import com.arkivanov.decompose.extensions.compose.stack.animation.plus
import com.arkivanov.decompose.extensions.compose.stack.animation.scale
import com.arkivanov.decompose.extensions.compose.stack.animation.stackAnimation
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import com.arkivanov.decompose.router.slot.SlotNavigation
import com.arkivanov.decompose.router.slot.child
import com.riffaells.hellocodehub.domain.components.root.RootComponent
import com.riffaells.hellocodehub.presentation.ui.detailed.DetailedContent
import com.riffaells.hellocodehub.presentation.ui.main.MainContent
import androidx.compose.animation.*
import androidx.compose.animation.core.*

@Composable
fun RootContent(
    component: RootComponent,
    modifier: Modifier = Modifier,
) {
    val stateRoot by component.state.collectAsState()
    val dialogSlot by component.detailedSlot.subscribeAsState()

    Surface(
        modifier = modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Row(
            modifier = Modifier.fillMaxSize()
        ) {
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
            ) {
                MainContent(
                    stateRoot = stateRoot,
                    component = component.mainComponent,
                    modifier = Modifier.fillMaxSize()
                )
            }

            AnimatedVisibility(
                visible = dialogSlot.child != null,
                enter = slideInHorizontally(
                    initialOffsetX = { it / 2 },
                    animationSpec = tween(300, easing = EaseOutCubic)
                ) + fadeIn(
                    animationSpec = tween(300)
                ),
                exit = slideOutHorizontally(
                    targetOffsetX = { it / 2 },
                    animationSpec = tween(300, easing = EaseInCubic)
                ) + fadeOut(
                    animationSpec = tween(300)
                )
            ) {
                Row(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight()
                ) {
                    HorizontalDivider(
                        modifier = Modifier
                            .fillMaxHeight()
                            .width(1.dp)
                            .animateEnterExit(
                                enter = fadeIn() + expandHorizontally(),
                                exit = fadeOut() + shrinkHorizontally()
                            ),
                        color = MaterialTheme.colorScheme.outlineVariant
                    )

                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxHeight()
                    ) {
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
