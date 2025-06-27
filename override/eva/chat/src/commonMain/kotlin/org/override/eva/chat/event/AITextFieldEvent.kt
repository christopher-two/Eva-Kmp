package org.override.eva.chat.event

import org.override.eva.chat.models.FileItem

/**
 * Eventos del TextField de IA
 */
sealed class AITextFieldEvent {
    data class OnTextChanged(val text: String) : AITextFieldEvent()
    data class OnSendMessage(val message: String) : AITextFieldEvent()
    object OnStopGeneration : AITextFieldEvent()
    object OnVoiceInputRequested : AITextFieldEvent()
    object OnAttachmentRequested : AITextFieldEvent()
    object OnActionsRequested : AITextFieldEvent()
    data class OnFocusChanged(val isFocused: Boolean) : AITextFieldEvent()
    data class OnFilesSelected(val files: List<FileItem>) : AITextFieldEvent()
    object OnFilePickerDismiss : AITextFieldEvent()
}