package org.christophertwo.eva.ui.screens.eva

import androidx.compose.animation.animateColor
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.override.eva.chat.config.AITextFieldConfig
import org.override.eva.chat.config.SendButtonConfig
import org.override.eva.chat.textfield.AITextField
import org.override.eva.chat.textfield.AITextFieldEvent
import org.override.eva.chat.textfield.AITextFieldState
import org.override.eva.generated.resources.Res
import org.koin.compose.viewmodel.koinViewModel
import org.override.eva.generated.resources.pause_circle_24dp_E3E3E3_FILL0_wght400_GRAD0_opsz24
import org.override.eva.generated.resources.send_24dp_E3E3E3_FILL0_wght400_GRAD0_opsz24

@Composable
fun EvaRoot(
    viewModel: EvaViewModel = koinViewModel(),
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    EvaScreen(
        state = state,
        onAction = viewModel::onAction,
        onEvent = viewModel::onEvent
    )
}

@Composable
fun EvaScreen(
    state: EvaState,
    onEvent: (AITextFieldEvent) -> Unit,
    onAction: (EvaAction) -> Unit,
) {
    LaunchedEffect(state.valueTextFiled) {
        if (state.valueTextFiled.text.isNotBlank() && state.state == AITextFieldState.IDLE) {
            onAction(EvaAction.OnTyping(AITextFieldState.TYPING))
        } else if (state.valueTextFiled.text.isBlank() && state.state == AITextFieldState.TYPING) {
            onAction(EvaAction.OnTyping(AITextFieldState.IDLE))
        }
    }

    // Animación infinita para el gradiente
    val infiniteTransition = rememberInfiniteTransition(label = "colorAnimation")
    val color1 by infiniteTransition.animateColor(
        initialValue = colorScheme.primary,
        targetValue = colorScheme.secondary,
        animationSpec = infiniteRepeatable(
            animation = tween(3000, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        )
    )
    val color2 by infiniteTransition.animateColor(
        initialValue = colorScheme.secondary,
        targetValue = colorScheme.primary,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 4000,
                easing = LinearEasing,
                delayMillis = 1000
            ),
            repeatMode = RepeatMode.Reverse
        )
    )
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(color = colorScheme.background),
        content = {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                content = {
                    // Texto con gradiente animado
                    Text(
                        text = "Hola soy Eva, tu asistente personal",
                        modifier = Modifier.padding(16.dp),
                        style = MaterialTheme.typography.titleLarge.copy(
                            brush = Brush.linearGradient(
                                colors = listOf(
                                    color1,
                                    color2
                                ),
                            )
                        ),
                        maxLines = 2,
                        textAlign = TextAlign.Center,
                        fontSize = 30.sp
                    )
                    TextField(
                        state = state,
                        onEvent = onEvent
                    )
                }
            )
        }
    )
}

@Composable
private fun TextField(
    state: EvaState,
    onEvent: (AITextFieldEvent) -> Unit,
) {
    AITextField(
        value = state.valueTextFiled,
        state = state.state,
        modifier = Modifier
            .widthIn(
                min = 500.dp,
                max = 800.dp
            )
            .height(150.dp)
            .padding(16.dp),
        onEvent = {
            when (it) {
                is AITextFieldEvent.OnTextChanged -> {
                    onEvent(it)
                }

                is AITextFieldEvent.OnSendMessage -> {
                    onEvent(it)
                }

                else -> {}
            }
        },
        config = AITextFieldConfig(
            backgroundColor = colorScheme.surfaceContainerLowest,
            borderColor = colorScheme.outline,
            textColor = colorScheme.onSurface,
            focusedBorderColor = colorScheme.secondary,
            hintColor = colorScheme.onSurfaceVariant,
            cornerRadius = 10,
            sendButtonConfig = SendButtonConfig(
                backgroundColor = colorScheme.primary,
                contentColor = colorScheme.onPrimary,
                sendIcon = Res.drawable.send_24dp_E3E3E3_FILL0_wght400_GRAD0_opsz24,
                pauseIcon = Res.drawable.pause_circle_24dp_E3E3E3_FILL0_wght400_GRAD0_opsz24
            )
        )
    )
}