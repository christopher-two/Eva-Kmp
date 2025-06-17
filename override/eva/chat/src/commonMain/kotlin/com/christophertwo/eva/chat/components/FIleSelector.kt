package com.christophertwo.eva.chat.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import io.github.vinceglb.filekit.dialogs.FileKitMode
import io.github.vinceglb.filekit.dialogs.FileKitType
import io.github.vinceglb.filekit.dialogs.compose.rememberFilePickerLauncher
import io.github.vinceglb.filekit.name


@Composable
fun FileMonoSelector(
    extensions: List<String> = listOf("jpg", "png", "pdf"),
    selectedFileText: (String) -> Unit,
) {
    var selectedFileText by remember { mutableStateOf("Ningún archivo seleccionado") }

    val filePickerLauncher = rememberFilePickerLauncher(
        type = FileKitType.File(
            extensions = extensions,
        ),
        onResult = { file ->
            file?.let {
                selectedFileText("Archivo seleccionado: ${file.name}")
            } ?: run {
                selectedFileText("El usuario canceló la selección")
            }
        }
    )

}

@Composable
fun FileMultiSelector(
    extensions: List<String> = listOf("jpg", "png", "pdf"),
    selectedFileText: (String) -> Unit,
) {
    val multipleFilesPickerLauncher = rememberFilePickerLauncher(
        mode = FileKitMode.Multiple(),
        type = FileKitType.File(extensions = extensions),
        onResult = { files ->
            files?.let {
                selectedFileText("Archivos seleccionados: ${files.joinToString(", ") { it.name }}")
            } ?: run {
                selectedFileText("El usuario canceló la selección")
            }
        }
    )
}