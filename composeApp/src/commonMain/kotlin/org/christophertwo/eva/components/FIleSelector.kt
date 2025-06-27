package org.christophertwo.eva.components

import androidx.compose.runtime.Composable
import io.github.vinceglb.filekit.PlatformFile
import io.github.vinceglb.filekit.dialogs.FileKitMode
import io.github.vinceglb.filekit.dialogs.FileKitType
import io.github.vinceglb.filekit.dialogs.compose.rememberFilePickerLauncher
import io.github.vinceglb.filekit.name

@Composable
fun FileMonoSelector(
    extensions: List<String> = listOf("jpg", "png", "pdf"),
    selectedFileText: (String) -> Unit,
    selectedFile: (PlatformFile?) -> Unit,
) {
    val filePickerLauncher = rememberFilePickerLauncher(
        type = FileKitType.File(
            extensions = extensions,
        ),
        onResult = { file ->
            file?.let {
                selectedFileText("Archivo seleccionado: ${file.name}")
                selectedFile(file)
            } ?: run {
                selectedFileText("El usuario canceló la selección")
                selectedFile(null)
            }
        }
    )
    filePickerLauncher.launch()
}

@Composable
fun FileMultiSelector(
    extensions: List<String> = listOf("jpg", "png", "pdf"),
    onClosed: () -> Unit,
    selectedFileText: (String) -> Unit,
    selectedFiles: (List<PlatformFile>) -> Unit,
) {
    val multipleFilesPickerLauncher = rememberFilePickerLauncher(
        mode = FileKitMode.Multiple(),
        type = FileKitType.File(extensions = extensions),
        onResult = { files ->
            files?.let {
                selectedFileText("Archivos seleccionados: ${files.joinToString(", ") { it.name }}")
                selectedFiles(files)
            } ?: run {
                selectedFileText("El usuario canceló la selección")
                selectedFiles(emptyList())
            }
            onClosed()
        }
    )
    multipleFilesPickerLauncher.launch()
}