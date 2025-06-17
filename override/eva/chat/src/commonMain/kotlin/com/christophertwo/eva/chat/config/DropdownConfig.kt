package com.christophertwo.eva.chat.config

import androidx.compose.ui.graphics.Color

data class DropdownConfig(
    val backgroundColor: Color = Color.White,
    val contentColor: Color = Color.Black,
    val cornerRadius: Int = 24,
    val borderColor: Color = Color.Gray,
    val focusedBorderColor: Color = Color.Blue,
)
