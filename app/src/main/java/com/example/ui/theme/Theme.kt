package com.example.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

private val LightColorScheme = lightColorScheme(
    primary = ForestGreenPrimary,
    onPrimary = Color.White,
    primaryContainer = MintContainerLight,
    onPrimaryContainer = MintContainerText,
    secondary = OceanBlueSecondary,
    onSecondary = Color.White,
    secondaryContainer = BlueContainerLight,
    onSecondaryContainer = BlueContainerText,
    tertiary = GoldenMangoTertiary,
    onTertiary = Color.White,
    tertiaryContainer = GoldenMangoContainer,
    onTertiaryContainer = GoldenMangoText,
    background = FreshBackgroundLight,
    onBackground = FreshOnSurfaceLight,
    surface = FreshSurfaceLight,
    onSurface = FreshOnSurfaceLight,
    surfaceVariant = FreshSurfaceVariantLight,
    onSurfaceVariant = Color(0xFF3B483F),
    outline = FreshOutlineLight,
)

private val DarkColorScheme = darkColorScheme(
    primary = ForestGreenDarkTheme,
    onPrimary = Color(0xFF003919),
    primaryContainer = Color(0xFF085328),
    onPrimaryContainer = MintContainerLight,
    secondary = OceanBlueDarkTheme,
    onSecondary = Color(0xFF003061),
    secondaryContainer = Color(0xFF034484),
    onSecondaryContainer = BlueContainerLight,
    tertiary = GoldenMangoDarkTheme,
    onTertiary = Color(0xFF452100),
    tertiaryContainer = Color(0xFF6B3600),
    onTertiaryContainer = GoldenMangoContainer,
    background = BackgroundDarkTheme,
    onBackground = OnSurfaceDarkTheme,
    surface = SurfaceDarkTheme,
    onSurface = OnSurfaceDarkTheme,
    surfaceVariant = SurfaceVariantDarkTheme,
    onSurfaceVariant = Color(0xFFBCCBC1),
    outline = OutlineDarkTheme,
)

@Composable
fun VgaExportTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = false, // Use intentional branded agriculture palette
    content: @Composable () -> Unit,
) {
  val colorScheme = when {
    dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
      val context = LocalContext.current
      if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
    }
    darkTheme -> DarkColorScheme
    else -> LightColorScheme
  }

  MaterialTheme(
      colorScheme = colorScheme,
      typography = Typography,
      content = content
  )
}

// Backward compatibility alias
@Composable
fun MyApplicationTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) = VgaExportTheme(darkTheme = darkTheme, dynamicColor = dynamicColor, content = content)
