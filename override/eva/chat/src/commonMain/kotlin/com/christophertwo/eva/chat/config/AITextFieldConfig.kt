package com.christophertwo.eva.chat.config

import androidx.compose.ui.graphics.Color
import com.christophertwo.eva.chat.textfield.ItemsDropdown
import org.christophertwo.eva.chat.generated.resources.Res
import org.christophertwo.eva.chat.generated.resources.send_24dp_E3E3E3_FILL0_wght400_GRAD0_opsz24

data class AITextFieldConfig(
    val maxLines: Int = 6,
    val minLines: Int = 1,
    val maxCharacters: Int = 4000,
    val placeholder: String = "Type a message...",
    val enableVoiceInput: Boolean = true,
    val enableAttachments: Boolean = true,
    val enableActions: Boolean = false,
    val enableAutoSend: Boolean = false,
    val showCharacterCounter: Boolean = true,
    val cornerRadius: Int = 24,
    val backgroundColor: Color = Color.Companion.Transparent,
    val borderColor: Color = Color.Companion.Gray,
    val focusedBorderColor: Color = Color.Companion.Blue,
    val textColor: Color = Color.Companion.Black,
    val hintColor: Color = Color.Companion.Gray,
    val sendButtonConfig: SendButtonConfig = SendButtonConfig(
        backgroundColor = Color.Companion.White,
        contentColor = Color.Companion.Black,
        icon = Res.drawable.send_24dp_E3E3E3_FILL0_wght400_GRAD0_opsz24
    ),
    val dropdownConfig: DropdownConfig = DropdownConfig(
        backgroundColor = Color.Companion.White,
        contentColor = Color.Companion.Black,
        cornerRadius = 24,
        borderColor = Color.Companion.Gray,
        focusedBorderColor = Color.Companion.Blue,
    ),
    val dropdownItems: List<ItemsDropdown> = emptyList()
)