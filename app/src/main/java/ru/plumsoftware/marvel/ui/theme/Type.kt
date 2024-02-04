package ru.plumsoftware.marvel.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import ru.plumsoftware.marvel.R

val Typography = Typography(
    titleLarge = TextStyle(
        fontWeight = FontWeight.Bold,
        fontFamily = FontFamily(Font(resId = R.font.roboto_regular)),
        fontSize = 26.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.5.sp
    ),
    titleMedium = TextStyle(
        fontWeight = FontWeight.Bold,
        fontFamily = FontFamily(Font(resId = R.font.roboto_regular)),
        fontSize = 22.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.sp
    ),
    labelLarge = TextStyle(
        fontWeight = FontWeight.Normal,
        fontFamily = FontFamily(Font(resId = R.font.roboto_regular)),
        fontSize = 18.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.sp
    )
)