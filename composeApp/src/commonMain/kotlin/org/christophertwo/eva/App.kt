package org.christophertwo.eva

import androidx.compose.runtime.Composable
import org.christophertwo.eva.theme.AppTheme
import org.christophertwo.eva.ui.screens.eva.EvaRoot
import org.jetbrains.compose.ui.tooling.preview.Preview

@Preview
@Composable
internal fun App() = AppTheme {
    EvaRoot()
}
