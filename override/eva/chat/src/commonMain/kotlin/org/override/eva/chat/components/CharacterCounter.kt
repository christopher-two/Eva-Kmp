package org.override.eva.chat.components

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

/**
 * Contador de caracteres
 */
@Composable
internal fun CharacterCounter(
    currentCount: Int,
    maxCount: Int,
    modifier: Modifier = Modifier
) {
    val color = when {
        currentCount >= maxCount * 0.9 -> colorScheme.error
        currentCount >= maxCount * 0.7 -> colorScheme.error.copy(alpha = 0.8f)
        else -> colorScheme.onSurface.copy(alpha = 0.6f)
    }

    Text(
        text = "$currentCount/$maxCount",
        style = MaterialTheme.typography.bodySmall,
        color = color,
        modifier = modifier
    )
}
