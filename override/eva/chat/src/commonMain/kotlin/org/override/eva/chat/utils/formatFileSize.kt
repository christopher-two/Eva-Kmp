package org.override.eva.chat.utils

// Función auxiliar para formatear el tamaño de archivo
fun formatFileSize(bytes: Long): String {
    val kb = bytes / 1024.0
    val mb = kb / 1024.0
    val gb = mb / 1024.0

    return when {
        gb >= 1 -> "${gb.toString().substringBefore(".")}.${
            gb.toString().substringAfter(".").take(1)
        } GB"

        mb >= 1 -> "${mb.toString().substringBefore(".")}.${
            mb.toString().substringAfter(".").take(1)
        } MB"

        kb >= 1 -> "${kb.toString().substringBefore(".")}.${
            kb.toString().substringAfter(".").take(1)
        } KB"

        else -> "$bytes B"
    }
}