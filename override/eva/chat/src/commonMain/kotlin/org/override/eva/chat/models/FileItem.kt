package org.override.eva.chat.models

import org.jetbrains.compose.resources.DrawableResource
import org.override.eva.generated.resources.Res
import org.override.eva.generated.resources.description_24dp_E3E3E3_FILL0_wght400_GRAD0_opsz24
import org.override.eva.generated.resources.draft_24dp_E3E3E3_FILL0_wght400_GRAD0_opsz24
import org.override.eva.generated.resources.folder_24dp_E3E3E3_FILL0_wght400_GRAD0_opsz24
import org.override.eva.generated.resources.image_24dp_E3E3E3_FILL0_wght400_GRAD0_opsz24
import org.override.eva.generated.resources.movie_24dp_E3E3E3_FILL0_wght400_GRAD0_opsz24
import org.override.eva.generated.resources.music_note_24dp_E3E3E3_FILL0_wght400_GRAD0_opsz24
import org.override.eva.generated.resources.picture_as_pdf_24dp_E3E3E3_FILL0_wght400_GRAD0_opsz24

/**
 * Data class que representa un archivo
 */
data class FileItem(
    val name: String,
    val path: String,
    val size: Long = 0L,
    val isDirectory: Boolean = false,
    val extension: String = "",
    val lastModified: Long = 0L
) {
    val icon: DrawableResource
        get() = when {
            isDirectory -> Res.drawable.folder_24dp_E3E3E3_FILL0_wght400_GRAD0_opsz24
            extension.lowercase() in listOf(
                "jpg",
                "jpeg",
                "png",
                "gif",
                "bmp"
            ) -> Res.drawable.image_24dp_E3E3E3_FILL0_wght400_GRAD0_opsz24

            extension.lowercase() in listOf(
                "mp4",
                "avi",
                "mkv",
                "mov"
            ) -> Res.drawable.movie_24dp_E3E3E3_FILL0_wght400_GRAD0_opsz24

            extension.lowercase() in listOf(
                "mp3",
                "wav",
                "flac",
                "aac"
            ) -> Res.drawable.music_note_24dp_E3E3E3_FILL0_wght400_GRAD0_opsz24

            extension.lowercase() in listOf("pdf") -> Res.drawable.picture_as_pdf_24dp_E3E3E3_FILL0_wght400_GRAD0_opsz24
            extension.lowercase() in listOf(
                "doc",
                "docx",
                "txt"
            ) -> Res.drawable.description_24dp_E3E3E3_FILL0_wght400_GRAD0_opsz24

            else -> Res.drawable.draft_24dp_E3E3E3_FILL0_wght400_GRAD0_opsz24
        }
}
