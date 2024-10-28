package com.riffaells.hellocodehub

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.arkivanov.decompose.DefaultComponentContext
import com.arkivanov.decompose.ExperimentalDecomposeApi
import com.riffaells.hellocodehub.domain.components.root.DefaultRootComponent
import com.riffaells.hellocodehub.presentation.ui.root.App
import org.kodein.di.DI
import org.kodein.di.DIAware
import org.kodein.di.android.closestDI
import org.kodein.di.compose.localDI
import org.kodein.di.compose.withDI

class MainActivity : ComponentActivity(), DIAware {

    override val di: DI by closestDI()

    @OptIn(ExperimentalDecomposeApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // Turn off the decor fitting system windows, which allows us to handle insets,
        // including IME animations
//        WindowCompat.setDecorFitsSystemWindows(window, false)


        setContent {
            withDI(di) {

                val root = DefaultRootComponent(DefaultComponentContext(lifecycle), di = localDI())


                App(component = root) { c ->
                    RootContent(
                        component = c,
                    )
                }
            }
        }

    }
}
