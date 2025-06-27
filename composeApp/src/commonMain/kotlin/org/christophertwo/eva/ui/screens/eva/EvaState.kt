package org.christophertwo.eva.ui.screens.eva

import androidx.compose.runtime.Immutable
import androidx.compose.ui.text.input.TextFieldValue
import io.github.vinceglb.filekit.PlatformFile
import org.override.eva.chat.enums.AITextFieldState
import org.override.eva.chat.models.FileItem

@Immutable
data class EvaState(
    val isLoading: Boolean = false,
    val valueTextFiled: TextFieldValue = TextFieldValue(""),
    val state: AITextFieldState = AITextFieldState.IDLE,
    val attachmentAction: Boolean = false,
    val attachmentsText: String = "",
    val attachments: List<FileItem> = emptyList(),
)