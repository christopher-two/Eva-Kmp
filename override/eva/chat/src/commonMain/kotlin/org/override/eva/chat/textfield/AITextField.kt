package org.override.eva.chat.textfield

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.override.eva.chat.components.ActionButton
import org.override.eva.chat.components.CharacterCounter
import org.override.eva.chat.components.SendButton
import org.override.eva.chat.config.AITextFieldConfig
import org.override.eva.chat.config.DropdownConfig
import org.jetbrains.compose.resources.painterResource
import org.override.eva.generated.resources.Res
import org.override.eva.generated.resources.add_24dp_E3E3E3_FILL0_wght400_GRAD0_opsz24
import org.override.eva.generated.resources.attach_file_24dp_E3E3E3_FILL0_wght400_GRAD0_opsz24
import org.override.eva.generated.resources.mic_24dp_E3E3E3_FILL0_wght400_GRAD0_opsz24

/**
 * TextField avanzado y rediseñado para un chat de IA.
 * La distribución del contenido se ha mejorado para ser más flexible y robusta,
 * utilizando `Modifier.weight(1f)` para que el campo de texto se expanda correctamente.
 */
@Composable
fun AITextField(
    value: TextFieldValue,
    onEvent: (AITextFieldEvent) -> Unit,
    state: AITextFieldState = AITextFieldState.IDLE,
    config: AITextFieldConfig = AITextFieldConfig(),
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
) {
    // --- Core State and Animations ---
    var isFocused by remember { mutableStateOf(false) }
    val focusRequester = remember { FocusRequester() }
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current

    val borderColor by animateColorAsState(
        targetValue = when {
            state == AITextFieldState.ERROR -> Color.Red
            isFocused -> config.focusedBorderColor
            else -> config.borderColor
        },
        animationSpec = tween(200),
        label = "borderColorAnimation"
    )

    val containerColor by animateColorAsState(
        targetValue = if (state == AITextFieldState.ERROR) Color.Red.copy(alpha = 0.05f) else config.backgroundColor,
        animationSpec = tween(200),
        label = "containerColorAnimation"
    )

    // --- UI Layout ---
    Column(
        modifier = modifier
            .fillMaxSize()
            .border(
                width = 3.dp,
                color = borderColor,
                shape = RoundedCornerShape(config.cornerRadius.dp)
            )
            .background(
                color = containerColor,
                shape = RoundedCornerShape(config.cornerRadius.dp)
            )
            .padding(vertical = 8.dp)
    ) {
        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .padding(12.dp)
        ) {
            BasicTextField(
                value = value.text,
                onValueChange = { newText ->
                    if (newText.length <= config.maxCharacters) {
                        onEvent(AITextFieldEvent.OnTextChanged(newText))
                    }
                },
                modifier = Modifier
                    .fillMaxSize()
                    .focusRequester(focusRequester)
                    .onFocusChanged { focusState ->
                        isFocused = focusState.isFocused
                        onEvent(AITextFieldEvent.OnFocusChanged(focusState.isFocused))
                    },
                enabled = enabled && state !in listOf(
                    AITextFieldState.SENDING,
                    AITextFieldState.RECEIVING
                ),
                textStyle = TextStyle(
                    color = config.textColor,
                    fontSize = 16.sp,
                    lineHeight = 20.sp
                ),
                keyboardOptions = KeyboardOptions(
                    capitalization = KeyboardCapitalization.Sentences,
                    keyboardType = KeyboardType.Text,
                    imeAction = if (config.enableAutoSend) ImeAction.Send else ImeAction.Default
                ),
                keyboardActions = KeyboardActions(
                    onSend = {
                        if (value.text.isNotBlank()) {
                            onEvent(AITextFieldEvent.OnSendMessage(value.text.trim()))
                            keyboardController?.hide()
                            focusManager.clearFocus()
                        }
                    }
                ),
                maxLines = config.maxLines,
                minLines = config.minLines,
                cursorBrush = SolidColor(config.focusedBorderColor),
                decorationBox = { innerTextField ->
                    if (value.text.isEmpty()) {
                        Text(
                            text = config.placeholder,
                            style = TextStyle(color = config.hintColor, fontSize = 16.sp)
                        )
                    }
                    innerTextField()
                }
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // --- Left-side Actions ---
            ActionButton(
                icon = Res.drawable.add_24dp_E3E3E3_FILL0_wght400_GRAD0_opsz24,
                onClick = { onEvent(AITextFieldEvent.OnActionsRequested) },
                enabled = true,
                contentDescription = "Acción",
            )
            if (config.enableAttachments && state != AITextFieldState.SENDING) {
                ActionButton(
                    icon = Res.drawable.attach_file_24dp_E3E3E3_FILL0_wght400_GRAD0_opsz24,
                    onClick = { onEvent(AITextFieldEvent.OnAttachmentRequested) },
                    enabled = enabled && state == AITextFieldState.IDLE,
                    contentDescription = "Adjuntar archivo",
                )
            }

            StateIndicator(state = state)

            Spacer(modifier = Modifier.weight(1f)) // Empuja los elementos a los extremos

            // --- Right-side Actions & Indicators ---
            if (config.showCharacterCounter && value.text.isNotEmpty()) {
                CharacterCounter(
                    currentCount = value.text.length,
                    maxCount = config.maxCharacters
                )
                Spacer(modifier = Modifier.width(8.dp))
            }

            if (config.enableVoiceInput && value.text.isEmpty() && state == AITextFieldState.IDLE) {
                ActionButton(
                    icon = Res.drawable.mic_24dp_E3E3E3_FILL0_wght400_GRAD0_opsz24,
                    onClick = { onEvent(AITextFieldEvent.OnVoiceInputRequested) },
                    enabled = enabled,
                    contentDescription = "Entrada de voz"
                )
            }

            if (value.text.isNotBlank() || state in listOf(
                    AITextFieldState.SENDING,
                    AITextFieldState.RECEIVING
                )
            ) {
                SendButton(
                    state = state,
                    enabled = enabled && value.text.isNotBlank(),
                    config = config.sendButtonConfig,
                    onSend = { onEvent(AITextFieldEvent.OnSendMessage(value.text.trim())) },
                    onStop = { onEvent(AITextFieldEvent.OnStopGeneration) }
                )
            }
        }
    }
    DropdownMenuComponent(
        isExpanded = config.enableActions,
        onDismissRequest = { onEvent(AITextFieldEvent.OnActionsRequested) },
        dropdownItems = config.dropdownItems,
        config = config.dropdownConfig
    )
}

@Composable
private fun DropdownMenuComponent(
    isExpanded: Boolean,
    onDismissRequest: () -> Unit,
    config: DropdownConfig,
    dropdownItems: List<ItemsDropdown>,
    modifier: Modifier = Modifier
) {
    DropdownMenu(
        expanded = isExpanded,
        onDismissRequest = onDismissRequest,
        modifier = modifier.fillMaxWidth(0.4f),
        shape = RoundedCornerShape(config.cornerRadius.dp),
        containerColor = config.backgroundColor,
        content = {
            dropdownItems.forEach {
                DropdownMenuItem(
                    text = {
                        Text(
                            text = it.title,
                            color = config.contentColor,
                            fontSize = 13.sp
                        )
                    },
                    modifier = modifier,
                    onClick = { it.onClick() },
                    leadingIcon = {
                        Icon(
                            painter = painterResource(it.icon),
                            contentDescription = it.contentDescription,
                            tint = config.contentColor
                        )
                    }
                )
            }
        }
    )
}