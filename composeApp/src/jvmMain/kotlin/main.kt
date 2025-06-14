import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import org.christophertwo.eva.App
import org.christophertwo.eva.di.ViewModelModules
import org.koin.core.context.startKoin
import java.awt.Dimension

fun main() = application {
    initKoin()
    Window(
        title = "Eva",
        state = rememberWindowState(width = 800.dp, height = 600.dp),
        onCloseRequest = ::exitApplication,
    ) {
        window.minimumSize = Dimension(350, 600)
        App()
    }
}

fun initKoin() {
    startKoin {
        printLogger()
        modules(ViewModelModules)
    }
}