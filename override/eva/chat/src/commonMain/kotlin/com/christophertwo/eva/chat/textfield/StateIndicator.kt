package com.christophertwo.eva.chat.textfield

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp


/**
 * Indicador de estado
 */
@Composable
internal fun StateIndicator(
    state: AITextFieldState,
    color: Color = colorScheme.primary,
    modifier: Modifier = Modifier
) {
    val text = when (state) {
        AITextFieldState.IDLE -> ""
        AITextFieldState.TYPING -> "Escribiendo..."
        AITextFieldState.SENDING -> "Enviando..."
        AITextFieldState.RECEIVING -> "IA respondiendo..."
        AITextFieldState.ERROR -> "Error al enviar mensaje"
    }

    val color = when (state) {
        AITextFieldState.ERROR -> colorScheme.error
        else -> color
    }

    if (text.isNotEmpty()) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = modifier
        ) {
            if (state == AITextFieldState.RECEIVING) {
                CircularProgressIndicator(
                    modifier = Modifier.size(12.dp),
                    strokeWidth = 1.5.dp,
                    color = color
                )
                Spacer(modifier = Modifier.width(8.dp))
            }

            Text(
                text = text,
                style = MaterialTheme.typography.bodySmall,
                color = color
            )
        }
    }
}
