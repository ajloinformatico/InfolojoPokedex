package es.infolojo.infolojopokedex.utils.ThemeHelper

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import es.infolojo.infolojopokedex.ui.theme.Purple40
import es.infolojo.infolojopokedex.ui.theme.Purple80
import es.infolojo.infolojopokedex.ui.theme.PurpleGrey40
import es.infolojo.infolojopokedex.ui.theme.PurpleGrey80

@Composable
fun getColorPrimary(): Color = if (isSystemInDarkTheme()) {
    Purple80
} else {
    Purple40
}

@Composable
fun getColorSecondary(): Color = if (isSystemInDarkTheme()) {
    PurpleGrey40
} else {
    PurpleGrey80
}

/**
 * Check if the [color] is dark
 */
@Composable
fun findCorrectTextColor(color: Color): Color = if (isDarkColor(color)) {
    Color.White
} else {
    Color.Black
}


/**
 * Check if the [color] is dark
 */
fun isDarkColor(color: Color): Boolean {
    // These values are the definition of luminance for digital formats: https://en.wikipedia.org/wiki/Luma_%28video%29
    val darkness =
        1 - (0.299 * color.red + 0.587 * color.green + 0.114 * color.blue)
    return darkness >= 0.4
}