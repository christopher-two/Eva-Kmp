package org.christophertwo.eva.ui.screens.eva

import io.github.vinceglb.filekit.PlatformFile
import org.override.eva.chat.enums.AITextFieldState

sealed interface EvaAction {
    data class OnTyping(val state: AITextFieldState = AITextFieldState.TYPING) : EvaAction
    data class OnAttachmentText(val text: String) : EvaAction
    data class OnAttachmentFiles(val files: List<PlatformFile>) : EvaAction
}