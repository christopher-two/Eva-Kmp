package org.christophertwo.eva

import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.ComposeViewport
import kotlinx.browser.document
import org.christophertwo.eva.di.ViewModelModules
import org.koin.core.context.startKoin

@OptIn(ExperimentalComposeUiApi::class)
fun main() {
    val body = document.body ?: return
    initKoin()
    ComposeViewport(body) {
        App()
    }
}

fun initKoin() {
    startKoin {
        printLogger()
        modules(ViewModelModules)
    }
}