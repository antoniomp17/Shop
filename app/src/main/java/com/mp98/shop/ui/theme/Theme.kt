package com.mp98.shop.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val DarkColorScheme = darkColorScheme(
    primary = primaryColorDark,
    onPrimary = onPrimaryColorDark,
    primaryContainer = primaryContainerColorDark,
    onPrimaryContainer = onPrimaryContainerColorDark,
    inversePrimary = inversePrimaryColorDark,
    secondary = secondaryColorDark,
    onSecondary = onSecondaryColorDark,
    secondaryContainer = secondaryContainerColorDark,
    onSecondaryContainer = onSecondaryContainerColorDark,
    tertiary = tertiaryColorDark,
    onTertiary = onTertiaryColorDark,
    tertiaryContainer = tertiaryContainerColorDark,
    onTertiaryContainer = onTertiaryContainerColorDark,
    background = backgroundColorDark,
    onBackground = onBackgroundColorDark,
    surface = surfaceColorDark,
    onSurface = onSurfaceColorDark,
    surfaceVariant = surfaceVariantColorDark,
    onSurfaceVariant = onSurfaceVariantColorDark,
    surfaceTint = surfaceTintColorDark,
    inverseSurface = inverseSurfaceColorDark,
    inverseOnSurface = inverseOnSurfaceColorDark,
    error = errorColorDark,
    onError = onErrorColorDark,
    errorContainer = errorContainerColorDark,
    onErrorContainer = onErrorContainerColorDark,
    outline = outlineColorDark,
    outlineVariant = outlineVariantColorDark,
    scrim = scrimColorDark
)

private val LightColorScheme = lightColorScheme(
    primary = primaryColor,
    onPrimary = onPrimaryColor,
    primaryContainer = primaryContainerColor,
    onPrimaryContainer = onPrimaryContainerColor,
    inversePrimary = inversePrimaryColor,
    secondary = secondaryColor,
    onSecondary = onSecondaryColor,
    secondaryContainer = secondaryContainerColor,
    onSecondaryContainer = onSecondaryContainerColor,
    tertiary = tertiaryColor,
    onTertiary = onTertiaryColor,
    tertiaryContainer = tertiaryContainerColor,
    onTertiaryContainer = onTertiaryContainerColor,
    background = backgroundColor,
    onBackground = onBackgroundColor,
    surface = surfaceColor,
    onSurface = onSurfaceColor,
    surfaceVariant = surfaceVariantColor,
    onSurfaceVariant = onSurfaceVariantColor,
    surfaceTint = surfaceTintColor,
    inverseSurface = inverseSurfaceColor,
    inverseOnSurface = inverseOnSurfaceColor,
    error = errorColor,
    onError = onErrorColor,
    errorContainer = errorContainerColor,
    onErrorContainer = onErrorContainerColor,
    outline = outlineColor,
    outlineVariant = outlineVariantColor,
    scrim = scrimColor
)

@Composable
fun ShopTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            val insetsController = WindowCompat.getInsetsController(window, view)

            // Cambia el color de la barra de estado
            window.statusBarColor = colorScheme.surface.toArgb() // Este sigue siendo usado para definir el color

            // Configura la apariencia de la barra de estado
            insetsController.isAppearanceLightStatusBars = !darkTheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content,
    )
}