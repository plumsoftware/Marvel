package ru.plumsoftware.marvel.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import ru.plumsoftware.marvel.R
import ru.plumsoftware.marvel.ui.theme.MarvelTheme
import ru.plumsoftware.marvel.ui.theme.Sizes
import ru.plumsoftware.marvel.ui.theme.Spaces

@Composable
fun Header() {
    Column(
        verticalArrangement = Arrangement.spacedBy(
            space = Spaces.Items.headerItemSpace,
            alignment = Alignment.CenterVertically
        ),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(horizontal = Spaces.Items.contentSpace)
    ) {
        Image(
            modifier = Modifier
                .width(width = Sizes.MarvelLogo.width)
                .height(height = Sizes.MarvelLogo.height),
            painter = painterResource(id = R.drawable.marvel_logo),
            contentDescription = stringResource(
                id = R.string.marvel_image
            )
        )
        Text(
            text = stringResource(id = R.string.header),
            style = MaterialTheme.typography.titleLarge,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
@Preview(showBackground = true)
private fun Header_() {
    MarvelTheme(darkTheme = true) {
        Surface {
            Header()
        }
    }
}