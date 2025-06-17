package org.override.eva.chat.textfield

import org.jetbrains.compose.resources.DrawableResource

data class ItemsDropdown(
    val title: String,
    val icon: DrawableResource,
    val contentDescription: String,
    val onClick: () -> Unit
)