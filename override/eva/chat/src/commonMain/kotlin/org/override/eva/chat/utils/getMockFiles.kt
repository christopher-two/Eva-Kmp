package org.override.eva.chat.utils

import org.override.eva.chat.models.FileItem

fun getMockFiles(): List<FileItem> = listOf(
    FileItem("Documentos", "/documents", isDirectory = true),
    FileItem("Imágenes", "/images", isDirectory = true),
    FileItem("archivo.pdf", "/documents/archivo.pdf", 1024000, extension = "pdf"),
    FileItem("imagen.jpg", "/images/imagen.jpg", 2048000, extension = "jpg"),
    FileItem("documento.docx", "/documents/documento.docx", 512000, extension = "docx"),
    FileItem("video.mp4", "/videos/video.mp4", 10485760, extension = "mp4"),
    FileItem("audio.mp3", "/audio/audio.mp3", 3145728, extension = "mp3")
)