import androidx.compose.ui.window.ComposeUIViewController
import org.christophertwo.eva.App
import platform.UIKit.UIViewController

fun MainViewController(): UIViewController = ComposeUIViewController { App() }
