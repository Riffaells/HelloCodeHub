package com.riffaells.hellocodehub.presentation.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.WindowScope
import com.riffaells.hellocodehub.App
import com.riffaells.hellocodehub.domain.components.root.RootComponent

@Composable
fun WindowScope.DesktopContent(
    modifier: Modifier = Modifier,
    component: RootComponent,
    onCloseRequest: () -> Unit,
    onMinimizeRequest: () -> Unit,
    onMaximizeRequest: () -> Unit
) {

    Box(
        modifier = modifier.fillMaxSize(),
    ) {

//        Column(
//            modifier = Modifier.background(Color.Transparent),
//        ) {

        App(
            modifier = Modifier,
            component = component,
        )
        TopBar(
            modifier = Modifier,
            onCloseRequest = onCloseRequest,
            onMinimizeRequest = onMinimizeRequest,
            onMaximizeRequest = onMaximizeRequest,
        )
    }
//        SpaceContent(
//            modifier = modifier.fillMaxSize(),
//            isSpace
//        )
//    }
}

