package com.math.noteapp.android

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.Typography
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun MyApplicationTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val Navy = Color(0xFF464152)
    val Blue = Color(0xFF769BA3)
    val LightBlue = Color(0xFFD7EFFE)
    val Chartreuse = Color(0xFFEFF7CF)

    val colors = if (darkTheme) {
        darkColorScheme(
            primary = Navy,
            secondary = Color(0xFF03DAC5),
            tertiary = Color(0xFF3700B3),
            surface = Blue,
            onPrimary = Chartreuse,
            onSurface = Navy
        )
    } else {
        lightColorScheme(
            primary = LightBlue,
            secondary = Color(0xFF37978E),
            tertiary = Color(0xFF3700B3),
            surface = Blue,
            onPrimary = Navy,
            onSurface = Color.White
        )
    }
    val typography = Typography(
        bodyMedium = TextStyle(
            fontFamily = FontFamily.Default,
            fontWeight = FontWeight.Normal,
            fontSize = 16.sp
        )
    )
    val shapes = Shapes(
        small = RoundedCornerShape(4.dp),
        medium = RoundedCornerShape(4.dp),
        large = RoundedCornerShape(0.dp)
    )

    MaterialTheme(
        colorScheme = colors,
        typography = typography,
        shapes = shapes,
        content = content
    )
}
