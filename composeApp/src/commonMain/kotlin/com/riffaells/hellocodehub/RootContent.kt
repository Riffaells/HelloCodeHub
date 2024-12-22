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

    BoxWithConstraints(
        modifier = modifier.fillMaxSize()
    ) {
        val isWideScreen = maxWidth > 800.dp

        println("Screen width: $maxWidth, isWideScreen: $isWideScreen") // Отладочный вывод

        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            if (isWideScreen) {
                // Широкий экран - два компонента рядом
                Row(modifier = Modifier.fillMaxSize()) {
                    // MainContent всегда виден
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

                    // DetailedContent с анимацией
                    dialogSlot.child?.instance?.let { detailed ->
                        HorizontalDivider(
                            modifier = Modifier
                                .fillMaxHeight()
                                .width(1.dp),
                            color = MaterialTheme.colorScheme.outlineVariant
                        )

                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .fillMaxHeight()
                        ) {
                            DetailedContent(
                                stateRoot = stateRoot,
                                component = detailed,
                                modifier = Modifier.fillMaxSize()
                            )
                        }
                    }
                }
            } else {
                // Узкий экран - один компонент
                Box(modifier = Modifier.fillMaxSize()) {
                    if (dialogSlot.child == null) {
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
