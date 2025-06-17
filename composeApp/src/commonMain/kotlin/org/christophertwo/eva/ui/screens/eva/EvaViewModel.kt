package org.christophertwo.eva.ui.screens.eva

import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import org.override.eva.chat.textfield.AITextFieldEvent
import org.override.eva.chat.textfield.AITextFieldState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class EvaViewModel : ViewModel() {

    private val _state = MutableStateFlow(EvaState())
    val state = _state
        .onStart { }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000L),
            initialValue = EvaState()
        )

    fun onAction(action: EvaAction) {
        when (action) {
            is EvaAction.OnTyping -> {
                _state.value = _state.value.copy(state = action.state)
            }
        }
    }

    fun onEvent(event: AITextFieldEvent) {
        when (event) {
            is AITextFieldEvent.OnTextChanged -> {
                _state.value = _state.value.copy(valueTextFiled = TextFieldValue(event.text))
            }

            is AITextFieldEvent.OnSendMessage -> {
                _state.value = _state.value.copy(valueTextFiled = TextFieldValue(""))
                onSendMessage()
            }

            else -> {

            }
        }
    }

    private fun onSendMessage() {
        viewModelScope.launch {
            _state.value = _state.value.copy(
                state = AITextFieldState.SENDING
            )
            delay(1000)
            _state.value = _state.value.copy(
                state = AITextFieldState.IDLE
            )
        }
    }

}