package org.christophertwo.eva.ui.screens.eva

import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.christophertwo.eva.chat.textfield.AITextFieldEvent
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn

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
            else -> TODO("Handle actions")
        }
    }

    fun onEvent(event: AITextFieldEvent) {
        when (event) {
            is AITextFieldEvent.OnTextChanged -> {
                _state.value = _state.value.copy(valueTextFiled = TextFieldValue(event.text))
            }
            else -> {

            }
        }
    }
}