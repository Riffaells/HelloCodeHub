import androidx.compose.ui.window.ComposeUIViewController
import com.riffaells.hellocodehub.App
import platform.UIKit.UIViewController

fun MainViewController(): UIViewController = ComposeUIViewController { App() }
