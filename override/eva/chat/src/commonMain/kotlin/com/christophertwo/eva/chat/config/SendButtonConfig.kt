package com.christophertwo.eva.chat.config

import androidx.compose.ui.graphics.Color
import org.jetbrains.compose.resources.DrawableResource

data class SendButtonConfig(
    val backgroundColor: Color = Color.White,
    val contentColor: Color = Color.Black,
    val icon: DrawableResource,
)