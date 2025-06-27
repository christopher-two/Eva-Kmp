package org.christophertwo.eva.ui.screens.eva

import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.github.vinceglb.filekit.extension
import io.github.vinceglb.filekit.nameWithoutExtension
import io.github.vinceglb.filekit.size
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import org.override.eva.chat.enums.AITextFieldState
import org.override.eva.chat.event.AITextFieldEvent
import org.override.eva.chat.models.FileItem

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

            is EvaAction.OnAttachmentText -> {
                _state.value = _state.value.copy(attachmentsText = action.text)
            }

            is EvaAction.OnAttachmentFiles -> {
                val filesItem: MutableList<FileItem> = emptyList<FileItem>().toMutableList()
                action.files.forEach {
                    filesItem.add(
                        FileItem(
                            name = it.nameWithoutExtension,
                            path = "",
                            size = it.size(),
                            isDirectory = true,
                            extension = it.extension,
                            lastModified = 10000L,
                        )
                    )
                }
                _state.value = _state.value.copy(
                    attachments = filesItem
                )
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

            is AITextFieldEvent.OnAttachmentRequested -> {
                _state.value =
                    _state.value.copy(attachmentAction = _state.value.attachmentAction.not())
            }

            is AITextFieldEvent.OnFilePickerDismiss -> {
                _state.value = _state.value.copy(
                    attachmentAction = _state.value.attachmentAction.not(),
                    attachments = emptyList()
                )
            }

            else -> {}
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