package org.override.eva.chat.config

import androidx.compose.ui.graphics.Color
import org.jetbrains.compose.resources.DrawableResource
import org.override.eva.generated.resources.Res
import org.override.eva.generated.resources.pause_circle_24dp_E3E3E3_FILL0_wght400_GRAD0_opsz24
import org.override.eva.generated.resources.send_24dp_E3E3E3_FILL0_wght400_GRAD0_opsz24

data class SendButtonConfig(
    val backgroundColor: Color = Color.White,
    val backgroundColorActive: Color = Color.White,
    val contentColor: Color = Color.Black,
    val contentActiveColor: Color = Color.Black,
    val sendIcon: DrawableResource = Res.drawable.send_24dp_E3E3E3_FILL0_wght400_GRAD0_opsz24,
    val pauseIcon: DrawableResource = Res.drawable.pause_circle_24dp_E3E3E3_FILL0_wght400_GRAD0_opsz24,
)