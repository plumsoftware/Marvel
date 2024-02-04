package ru.plumsoftware.marvel.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.googlefonts.Font
import androidx.compose.ui.text.googlefonts.GoogleFont
import androidx.compose.ui.unit.sp
import ru.plumsoftware.marvel.R

private val provider = GoogleFont.Provider(
    providerAuthority = "com.google.android.gms.fonts",
    providerPackage = "com.google.android.gms",
    certificates = R.array.com_google_android_gms_fonts_certs
)

private val primaryFont = GoogleFont("Roboto")

val Typography = Typography(
    titleLarge = TextStyle(
        fontFamily = FontFamily(
            Font(
                googleFont = primaryFont,
                fontProvider = provider,
                weight = FontWeight.Bold,
                style = FontStyle.Normal
            )
        ),
        fontSize = 26.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.5.sp
    ),
    titleMedium = TextStyle(
        fontFamily = FontFamily(
            Font(
                googleFont = primaryFont,
                fontProvider = provider,
                weight = FontWeight.Bold,
                style = FontStyle.Normal
            )
        ),
        fontSize = 22.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.sp
    ),
    labelLarge = TextStyle(
        fontWeight = FontWeight.Normal,
        fontFamily = FontFamily(
            Font(
                googleFont = primaryFont,
                fontProvider = provider,
                weight = FontWeight.Normal,
                style = FontStyle.Normal
            )
        ),
        fontSize = 18.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.sp
    )
)