package com.kubzos.launcher.ui.theme

import androidx.compose.material.*
import androidx.compose.runtime.Composable

private val LightColors = lightColors(
    primary = androidx.compose.ui.graphics.Color(0xFF1E88E5),
    primaryVariant = androidx.compose.ui.graphics.Color(0xFF1565C0),
    secondary = androidx.compose.ui.graphics.Color(0xFFBB86FC)
)

private val DarkColors = darkColors(
    primary = androidx.compose.ui.graphics.Color(0xFF90CAF9),
    secondary = androidx.compose.ui.graphics.Color(0xFFBB86FC)
)

@Composable
fun KubzOSTheme(darkTheme: Boolean = false, content: @Composable () -> Unit) {
    MaterialTheme(colors = if (darkTheme) DarkColors else LightColors, content = content)
}
