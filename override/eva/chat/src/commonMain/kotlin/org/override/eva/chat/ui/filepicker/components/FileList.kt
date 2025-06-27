package org.override.eva.chat.ui.filepicker.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.override.eva.chat.config.FilePickerConfig
import org.override.eva.chat.models.FileItem

@Composable
internal fun FileList(
    files: List<FileItem>,
    selectedFiles: List<FileItem>,
    config: FilePickerConfig,
    onFileClick: (FileItem) -> Unit
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        items(files) { file ->
            FileListItem(
                file = file,
                isSelected = selectedFiles.contains(file),
                onClick = { onFileClick(file) },
                multipleSelection = config.multipleSelection
            )
        }
    }
}