package ru.plumsoftware.marvel.ui.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import ru.plumsoftware.marvel.R
import ru.plumsoftware.marvel.model.uimodel.Hero
import ru.plumsoftware.marvel.ui.component.Header
import ru.plumsoftware.marvel.ui.component.SnapList
import ru.plumsoftware.marvel.ui.theme.MarvelTheme
import ru.plumsoftware.marvel.ui.theme.Spaces

@Composable
fun MainPage(heroes: MutableList<Hero>, onHeroClick: (Hero) -> Unit = {}) {
    val heroBackColor = remember {
        mutableStateOf(heroes[0].heroColor)
    }

    Box(modifier = Modifier.fillMaxSize()) {
        Icon(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.5f)
                .align(Alignment.BottomEnd),
            tint = heroBackColor.value,
            painter = painterResource(id = R.drawable.hero_back),
            contentDescription = null
        )

        Column(
            verticalArrangement = Arrangement.spacedBy(
                space = Spaces.Items.contentSpace,
                alignment = Alignment.CenterVertically
            ),
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
        ) {
            Header()
            SnapList(
                list = heroes,
                onHeroClick = onHeroClick,
                onScroll = { color ->
                    heroBackColor.value = color
                }
            )
        }
    }
}

@Composable
@Preview(showBackground = true, showSystemUi = true)
private fun MainPage_() {
    MarvelTheme {
        Surface {
            MainPage(heroes = mutableListOf())
        }
    }
}