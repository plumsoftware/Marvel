package ru.plumsoftware.marvel

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.core.view.WindowCompat
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import ru.plumsoftware.marvel.mock.getMockHeroes
import ru.plumsoftware.marvel.model.Hero
import ru.plumsoftware.marvel.ui.Screens
import ru.plumsoftware.marvel.ui.presentation.HeroPage
import ru.plumsoftware.marvel.ui.presentation.MainPage
import ru.plumsoftware.marvel.ui.theme.ApplySystemColors
import ru.plumsoftware.marvel.ui.theme.MarvelTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {

            val navController = rememberNavController()
            val selectedHero = remember {
                mutableStateOf(getMockHeroes()[0])
            }

            MarvelTheme(darkTheme = true) {
                ApplySystemColors()
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {


                    Content(navController, selectedHero)
                }
            }
        }
    }
}

@Composable
private fun Content(navController: NavHostController, selectedHero: MutableState<Hero>) {
    NavHost(navController = navController, startDestination = Screens.MAIN_PAGE) {
        composable(route = Screens.MAIN_PAGE) {
            MainPage(onHeroClick = { hero ->
                selectedHero.value = hero
                navController.navigate(route = Screens.HERO_PAGE)
            })
        }
        composable(route = Screens.HERO_PAGE) {
            HeroPage(hero = selectedHero.value, onBackCLick = {
                navController.popBackStack()
            })
        }
    }
}