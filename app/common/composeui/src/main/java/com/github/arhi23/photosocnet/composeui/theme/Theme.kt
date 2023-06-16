package com.github.arhi23.photosocnet.composeui.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val psnDarkColorScheme = darkColorScheme(
  primary = psnDarkPrimary,
  onPrimary = psnDarkOnPrimary,
  primaryContainer = psnDarkPrimaryContainer,
  onPrimaryContainer = psnDarkOnPrimaryContainer,
  inversePrimary = psnDarkPrimaryInverse,
  secondary = psnDarkSecondary,
  onSecondary = psnDarkOnSecondary,
  secondaryContainer = psnDarkSecondaryContainer,
  onSecondaryContainer = psnDarkOnSecondaryContainer,
  tertiary = psnDarkTertiary,
  onTertiary = psnDarkOnTertiary,
  tertiaryContainer = psnDarkTertiaryContainer,
  onTertiaryContainer = psnDarkOnTertiaryContainer,
  error = psnDarkError,
  onError = psnDarkOnError,
  errorContainer = psnDarkErrorContainer,
  onErrorContainer = psnDarkOnErrorContainer,
  background = psnDarkBackground,
  onBackground = psnDarkOnBackground,
  surface = psnDarkSurface,
  onSurface = psnDarkOnSurface,
  inverseSurface = psnDarkInverseSurface,
  inverseOnSurface = psnDarkInverseOnSurface,
  surfaceVariant = psnDarkSurfaceVariant,
  onSurfaceVariant = psnDarkOnSurfaceVariant,
  outline = psnDarkOutline
)

private val psnLightColorScheme = lightColorScheme(
  primary = psnLightPrimary,
  onPrimary = psnLightOnPrimary,
  primaryContainer = psnLightPrimaryContainer,
  onPrimaryContainer = psnLightOnPrimaryContainer,
  inversePrimary = psnLightPrimaryInverse,
  secondary = psnLightSecondary,
  onSecondary = psnLightOnSecondary,
  secondaryContainer = psnLightSecondaryContainer,
  onSecondaryContainer = psnLightOnSecondaryContainer,
  tertiary = psnLightTertiary,
  onTertiary = psnLightOnTertiary,
  tertiaryContainer = psnLightTertiaryContainer,
  onTertiaryContainer = psnLightOnTertiaryContainer,
  error = psnLightError,
  onError = psnLightOnError,
  errorContainer = psnLightErrorContainer,
  onErrorContainer = psnLightOnErrorContainer,
  background = psnLightBackground,
  onBackground = psnLightOnBackground,
  surface = psnLightSurface,
  onSurface = psnLightOnSurface,
  inverseSurface = psnLightInverseSurface,
  inverseOnSurface = psnLightInverseOnSurface,
  surfaceVariant = psnLightSurfaceVariant,
  onSurfaceVariant = psnLightOnSurfaceVariant,
  outline = psnLightOutline
)
private val LightColors = lightColorScheme(
  primary = md_theme_light_primary,
  onPrimary = md_theme_light_onPrimary,
  primaryContainer = md_theme_light_primaryContainer,
)

private val DarkColors = darkColorScheme(
  primary = md_theme_dark_primary,
  onPrimary = md_theme_dark_onPrimary,
  primaryContainer = md_theme_dark_primaryContainer,
)

@Composable
fun PsnTheme(
  darkTheme: Boolean = isSystemInDarkTheme(),
  dynamicColor: Boolean = true,
  content: @Composable () -> Unit
) {
  val psnColorScheme = when {
//    dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
//      val context = LocalContext.current
//      if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
//    }
    darkTheme -> psnDarkColorScheme
    else -> psnLightColorScheme
  }
  val view = LocalView.current
  if (!view.isInEditMode) {
    SideEffect {
      val window = (view.context as Activity).window
      window.statusBarColor = psnColorScheme.primary.toArgb()
      WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = darkTheme
    }
  }

  MaterialTheme(
    colorScheme = psnColorScheme,
    typography = psnTypography,
    shapes = shapes,
    content = content
  )
}