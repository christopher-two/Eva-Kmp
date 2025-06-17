package org.christophertwo.eva.ui.screens.eva

import org.override.eva.chat.textfield.AITextFieldState

sealed interface EvaAction {
    data class OnTyping(val state: AITextFieldState = AITextFieldState.TYPING) : EvaAction
}