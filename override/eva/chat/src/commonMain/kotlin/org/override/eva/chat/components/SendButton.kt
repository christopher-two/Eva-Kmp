package org.override.eva.chat.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import org.override.eva.chat.config.SendButtonConfig
import org.override.eva.chat.textfield.AITextFieldState
import org.jetbrains.compose.resources.painterResource
import org.override.eva.generated.resources.Res
import org.override.eva.generated.resources.pause_circle_24dp_E3E3E3_FILL0_wght400_GRAD0_opsz24
import org.override.eva.generated.resources.send_24dp_E3E3E3_FILL0_wght400_GRAD0_opsz24

/**
 * Botón de enviar con estados
 */
@Composable
internal fun SendButton(
    state: AITextFieldState,
    config: SendButtonConfig,
    enabled: Boolean,
    onSend: () -> Unit,
    onStop: () -> Unit,
    modifier: Modifier = Modifier
) {
    val backgroundColor by animateColorAsState(
        targetValue = when (state) {
            AITextFieldState.SENDING, AITextFieldState.RECEIVING -> MaterialTheme.colorScheme.error
            else -> config.backgroundColor
        },
        animationSpec = tween(200)
    )

    val icon = when (state) {
        AITextFieldState.SENDING, AITextFieldState.RECEIVING -> Res.drawable.pause_circle_24dp_E3E3E3_FILL0_wght400_GRAD0_opsz24
        else -> Res.drawable.send_24dp_E3E3E3_FILL0_wght400_GRAD0_opsz24
    }

    val onClick = when (state) {
        AITextFieldState.SENDING, AITextFieldState.RECEIVING -> onStop
        else -> onSend
    }

    Box(
        modifier = modifier
            .size(40.dp)
            .clip(CircleShape)
            .background(backgroundColor)
            .clickable(
                enabled = enabled || state in listOf(
                    AITextFieldState.SENDING,
                    AITextFieldState.RECEIVING
                ),
                indication = null,
                interactionSource = remember { MutableInteractionSource() }
            ) { onClick() },
        contentAlignment = Alignment.Center
    ) {
        if (state == AITextFieldState.SENDING) {
            CircularProgressIndicator(
                modifier = Modifier.size(20.dp),
                strokeWidth = 2.dp,
                color = config.contentColor
            )
        } else {
            Icon(
                painter = painterResource(icon),
                contentDescription = when (state) {
                    AITextFieldState.RECEIVING -> "Detener generación"
                    else -> "Enviar mensaje"
                },
                modifier = Modifier.size(20.dp),
                tint = config.contentColor
            )
        }
    }
}
