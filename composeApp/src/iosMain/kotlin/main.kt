import androidx.compose.ui.window.ComposeUIViewController
import com.riffaells.hellocodehub.RootContent
import platform.UIKit.UIViewController

fun MainViewController(): UIViewController = ComposeUIViewController { RootContent() }
