package org.override.eva.chat.config

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import org.override.eva.chat.enums.FileSortType

/**
 * Data class para configurar el comportamiento del FilePicker
 */
data class FilePickerConfig(
    val allowedExtensions: List<String> = emptyList(),
    val maxFileSize: Long = Long.MAX_VALUE,
    val multipleSelection: Boolean = false,
    val showHiddenFiles: Boolean = false,
    val title: String = "Seleccionar archivo",
    val confirmButtonText: String = "Seleccionar",
    val cancelButtonText: String = "Cancelar",
    val maxHeight: androidx.compose.ui.unit.Dp = 400.dp,
    val maxWidth: androidx.compose.ui.unit.Dp = 350.dp,
    val cornerRadius: androidx.compose.ui.unit.Dp = 16.dp,
    val elevation: androidx.compose.ui.unit.Dp = 8.dp,
    val backgroundColor: Color = Color.Unspecified,
    val enableSearch: Boolean = true,
    val sortBy: FileSortType = FileSortType.NAME
)