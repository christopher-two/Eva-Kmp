package org.override.eva.chat.ui.filepicker

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.EaseOutBack
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Popup
import androidx.compose.ui.window.PopupProperties
import org.override.eva.chat.config.FilePickerConfig
import org.override.eva.chat.models.FileItem
import org.override.eva.chat.ui.filepicker.components.FileList
import org.override.eva.chat.ui.filepicker.components.FilePickerFooter
import org.override.eva.chat.ui.filepicker.components.FilePickerHeader
import org.override.eva.chat.ui.filepicker.components.FilePickerSearchBar
import org.override.eva.chat.utils.getMockFiles

/**
 * Composable principal del FilePicker
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun FilePicker(
    isVisible: Boolean,
    config: FilePickerConfig = FilePickerConfig(),
    onFileSelected: (List<FileItem>) -> Unit,
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier,
    getCurrentDirectory: () -> List<FileItem> = { getMockFiles() }
) {
    var selectedFiles by remember { mutableStateOf<List<FileItem>>(emptyList()) }
    var searchQuery by remember { mutableStateOf("") }
    var currentFiles by remember { mutableStateOf(getCurrentDirectory()) }
    var isLoading by remember { mutableStateOf(false) }

    // Animación de entrada/salida
    AnimatedVisibility(
        visible = isVisible,
        enter = fadeIn(animationSpec = tween(300)) + scaleIn(
            initialScale = 0.8f,
            animationSpec = tween(300, easing = EaseOutBack)
        ),
        exit = fadeOut(animationSpec = tween(200)) + scaleOut(
            targetScale = 0.8f,
            animationSpec = tween(200)
        )
    ) {
        Popup(
            alignment = Alignment.Center,
            properties = PopupProperties(
                dismissOnBackPress = true,
                dismissOnClickOutside = true,
                focusable = true
            ),
            onDismissRequest = onDismiss
        ) {
            Card(
                modifier = modifier
                    .size(width = config.maxWidth, height = config.maxHeight)
                    .shadow(
                        elevation = config.elevation,
                        shape = RoundedCornerShape(config.cornerRadius),
                        ambientColor = Color.Black.copy(alpha = 0.1f),
                        spotColor = Color.Black.copy(alpha = 0.25f)
                    ),
                shape = RoundedCornerShape(config.cornerRadius),
                colors = CardDefaults.cardColors(
                    containerColor = if (config.backgroundColor != Color.Unspecified)
                        config.backgroundColor
                    else
                        MaterialTheme.colorScheme.surface
                ),
                elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
            ) {
                Column(
                    modifier = Modifier.fillMaxSize()
                ) {
                    // Header
                    FilePickerHeader(
                        title = config.title,
                        onClose = onDismiss,
                        cornerRadius = config.cornerRadius
                    )

                    // Search bar (si está habilitado)
                    if (config.enableSearch) {
                        FilePickerSearchBar(
                            query = searchQuery,
                            onQueryChange = { searchQuery = it },
                            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
                        )
                    }

                    // File list
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxWidth()
                    ) {
                        if (isLoading) {
                            CircularProgressIndicator(
                                modifier = Modifier.align(Alignment.Center)
                            )
                        } else {
                            FileList(
                                files = currentFiles.filter { file ->
                                    if (searchQuery.isBlank()) true
                                    else file.name.contains(searchQuery, ignoreCase = true)
                                },
                                selectedFiles = selectedFiles,
                                config = config,
                                onFileClick = { file ->
                                    if (file.isDirectory) {
                                        // Navegar al directorio (implementación específica de plataforma)
                                        // currentFiles = navigateToDirectory(file.path)
                                    } else {
                                        selectedFiles = if (config.multipleSelection) {
                                            if (selectedFiles.contains(file)) {
                                                selectedFiles - file
                                            } else {
                                                selectedFiles + file
                                            }
                                        } else {
                                            listOf(file)
                                        }
                                    }
                                }
                            )
                        }
                    }

                    // Footer con botones
                    FilePickerFooter(
                        selectedCount = selectedFiles.size,
                        confirmText = config.confirmButtonText,
                        cancelText = config.cancelButtonText,
                        onConfirm = {
                            if (selectedFiles.isNotEmpty()) {
                                onFileSelected(selectedFiles)
                            }
                        },
                        onCancel = onDismiss,
                        isConfirmEnabled = selectedFiles.isNotEmpty()
                    )
                }
            }
        }
    }
}