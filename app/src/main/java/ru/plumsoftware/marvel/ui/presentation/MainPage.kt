package ru.plumsoftware.marvel.ui.presentation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import ru.plumsoftware.marvel.R
import ru.plumsoftware.marvel.model.uimodel.Hero
import ru.plumsoftware.marvel.ui.component.Header
import ru.plumsoftware.marvel.ui.component.SnapList
import ru.plumsoftware.marvel.ui.theme.MarvelTheme
import ru.plumsoftware.marvel.ui.theme.Spaces

@Composable
fun MainPage(
    heroes: MutableList<Hero>,
    onHeroClick: (Hero) -> Unit = {},
    isError: MutableState<Boolean>
) {
    val heroBackColor = remember {
        mutableStateOf(if (heroes.isNotEmpty()) heroes[0].heroColor else Hero().heroColor)
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

        AnimatedVisibility(
            visible = isError.value,
            enter = fadeIn(),
            exit = fadeOut()
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.errorContainer),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = stringResource(id = R.string.error),
                    style = MaterialTheme.typography.labelMedium.copy(color = MaterialTheme.colorScheme.onErrorContainer)
                )
            }
        }

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
            MainPage(heroes = remember {
                mutableListOf()
            }, isError = remember {
                mutableStateOf(false)
            })
        }
    }
}