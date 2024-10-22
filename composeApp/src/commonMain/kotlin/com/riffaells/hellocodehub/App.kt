package com.riffaells.hellocodehub

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.arkivanov.decompose.extensions.compose.stack.Children
import com.arkivanov.decompose.extensions.compose.stack.animation.fade
import com.arkivanov.decompose.extensions.compose.stack.animation.stackAnimation
import com.riffaells.hellocodehub.domain.components.root.RootComponent
import com.riffaells.hellocodehub.presentation.ui.detailed.DetailedContent
import com.riffaells.hellocodehub.presentation.ui.main.MainContent

@Composable
fun App(
    component: RootComponent,
    modifier: Modifier = Modifier,
) {
    val stateRoot by component.state.collectAsState()

    Surface(modifier = modifier.background(Color.Transparent).fillMaxSize()) {

        Children(
            stack = component.childStack,
            modifier = Modifier.fillMaxSize(),
            animation = stackAnimation(fade())
        ) {
            when (val instance = it.instance) {
                is RootComponent.Child.MainChild -> MainContent(stateRoot = stateRoot, component = instance.component)
                is RootComponent.Child.DetailedChild -> DetailedContent(component = instance.component)

            }
        }
    }


}