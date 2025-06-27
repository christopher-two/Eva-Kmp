package org.override.eva.chat.config

import androidx.compose.ui.graphics.Color
import org.override.eva.chat.models.ItemsDropdown
import org.override.eva.generated.resources.Res
import org.override.eva.generated.resources.pause_circle_24dp_E3E3E3_FILL0_wght400_GRAD0_opsz24
import org.override.eva.generated.resources.send_24dp_E3E3E3_FILL0_wght400_GRAD0_opsz24

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
    val dropdownItems: List<ItemsDropdown> = emptyList(),
    val sendButtonConfig: SendButtonConfig = SendButtonConfig(),
    val dropdownConfig: DropdownConfig = DropdownConfig(),
    val filePickerConfig: FilePickerConfig = FilePickerConfig()
)