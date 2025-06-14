package org.christophertwo.eva.ui.screens.eva

import androidx.compose.runtime.Immutable
import androidx.compose.ui.text.input.TextFieldValue

@Immutable
data class EvaState(
    val isLoading: Boolean = false,
    val valueTextFiled: TextFieldValue = TextFieldValue(""),
)