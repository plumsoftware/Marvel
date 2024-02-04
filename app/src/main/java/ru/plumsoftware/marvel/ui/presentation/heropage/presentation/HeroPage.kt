package ru.plumsoftware.marvel.ui.presentation.heropage.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImage
import ru.plumsoftware.marvel.R
import ru.plumsoftware.data.model.uimodel.Hero
import ru.plumsoftware.marvel.ui.presentation.heropage.viewmodel.HeroViewModel
import ru.plumsoftware.marvel.ui.theme.MarvelTheme
import ru.plumsoftware.marvel.ui.theme.Spaces

@Composable
fun HeroPage(heroViewModel: HeroViewModel) {

    val state = heroViewModel.state.collectAsState().value

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {

        AsyncImage(
            model = state.hero?.heroImageResId,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop,
            contentDescription = stringResource(id = R.string.hero_image),
        )

        Column(
            horizontalAlignment = Alignment.Start,
            modifier = Modifier
                .wrapContentSize()
                .align(Alignment.TopStart)
        ) {
            Spacer(modifier = Modifier.height(Spaces.Items.heroPageSpacer))
            IconButton(
                onClick = {
                    heroViewModel.onOutput(HeroViewModel.Output.OnBackClicked)
                },
                colors = IconButtonDefaults.iconButtonColors(
                    contentColor = MaterialTheme.colorScheme.onBackground
                )
            ) {
                Icon(
                    imageVector = Icons.Rounded.ArrowBack, contentDescription = stringResource(
                        id = R.string.back_button
                    )
                )
            }
        }

        Column(
            verticalArrangement = Arrangement.spacedBy(
                space = Spaces.Items.heroPageSpace,
                alignment = Alignment.Bottom
            ),
            horizontalAlignment = Alignment.Start,
            modifier = Modifier
                .fillMaxWidth()
                .padding(all = Spaces.Screens.allPaddingScreen)
                .align(Alignment.BottomStart)
        ) {

            Text(
                text = state.hero?.heroQuoteResId.toString(),
                style = MaterialTheme.typography.titleMedium.copy(color = MaterialTheme.colorScheme.onBackground),
                textAlign = TextAlign.Start
            )
            Text(
                text = state.hero?.heroQuoteResId.toString(),
                style = MaterialTheme.typography.labelLarge.copy(color = MaterialTheme.colorScheme.onBackground),
                textAlign = TextAlign.Start
            )
            Spacer(modifier = Modifier.height(Spaces.Items.heroPageSpacer))

        }
    }
}

@Composable
@Preview(showBackground = true)
private fun HeroPage_() {

    val viewModel = HeroViewModel(hero = Hero(), output = {

    })

    MarvelTheme(darkTheme = true) {
        Surface {
            HeroPage(viewModel)
        }
    }
}
