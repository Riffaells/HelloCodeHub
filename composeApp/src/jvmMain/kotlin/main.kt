import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.*
import com.arkivanov.decompose.DefaultComponentContext
import com.arkivanov.decompose.ExperimentalDecomposeApi
import com.arkivanov.decompose.extensions.compose.lifecycle.LifecycleController
import com.arkivanov.essenty.lifecycle.LifecycleRegistry
import com.arkivanov.mvikotlin.core.utils.setMainThreadId
import com.riffaells.hellocodehub.domain.components.root.DefaultRootComponent
import com.riffaells.hellocodehub.di.kodein
import com.riffaells.hellocodehub.presentation.ui.DesktopContent
import com.riffaells.hellocodehub.presentation.ui.root.AppContent
import hellocodehub.composeapp.generated.resources.Res
import hellocodehub.composeapp.generated.resources.app
import org.jetbrains.compose.resources.stringResource
import org.kodein.di.compose.localDI
import org.kodein.di.compose.withDI
import javax.swing.SwingUtilities

@OptIn(ExperimentalDecomposeApi::class, ExperimentalComposeUiApi::class)
fun main() {
    val lifecycle = LifecycleRegistry()

    SwingUtilities.invokeAndWait {
        setMainThreadId(Thread.currentThread().id)



    }
    application {
        withDI(
            kodein
        ) {

            val di = localDI()
            val root = DefaultRootComponent(DefaultComponentContext(lifecycle), di = di)
            val windowState = rememberWindowState()
            LifecycleController(lifecycle, windowState)






            Window(
                onCloseRequest = ::exitApplication,
                state = windowState,
                title = stringResource(Res.string.app),
                decoration = WindowDecoration.Undecorated(),
            ) {
                AppContent(
                    modifier = Modifier,
                    component = root,
                ) { component ->
                    DesktopContent(
                        modifier = Modifier,
                        component = component,
//                        isSpace = isSpace,
                        onCloseRequest = ::exitApplication,
                        onMinimizeRequest = { windowState.isMinimized = true },
                        onMaximizeRequest = {

                            if (windowState.placement == WindowPlacement.Floating)
                                windowState.placement = WindowPlacement.Maximized
                            else
                                windowState.placement = WindowPlacement.Floating
                        },
                    )
                }
            }
        }
    }
}

fun <T> invokeOnAwtSync(block: () -> T): T {
    var result: T? = null
    SwingUtilities.invokeAndWait { result = block() }

    @Suppress("UNCHECKED_CAST")
    return result as T
}



//@Preview
//@Composable
//fun AppPreview() { App() }