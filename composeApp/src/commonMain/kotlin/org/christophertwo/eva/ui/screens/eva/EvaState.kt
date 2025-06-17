package org.christophertwo.eva.ui.screens.eva

import androidx.compose.runtime.Immutable
import androidx.compose.ui.text.input.TextFieldValue
import org.override.eva.chat.textfield.AITextFieldState

@Immutable
data class EvaState(
    val isLoading: Boolean = false,
    val valueTextFiled: TextFieldValue = TextFieldValue(""),
    val state: AITextFieldState = AITextFieldState.IDLE,
)