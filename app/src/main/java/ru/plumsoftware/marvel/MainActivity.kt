package ru.plumsoftware.marvel

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.core.view.WindowCompat
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import ru.plumsoftware.marvel.model.Hero
import ru.plumsoftware.marvel.model.constant.ApiConstants
import ru.plumsoftware.marvel.model.constant.CharacterIds
import ru.plumsoftware.marvel.model.dto.characters.Results
import ru.plumsoftware.marvel.repository.MarvelApi
import ru.plumsoftware.marvel.repository.MarvelRepository
import ru.plumsoftware.marvel.repository.MarvelRepositoryImpl
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
            val heroes = remember {
                mutableListOf(Hero())
            }
            val selectedHero = remember {
                mutableStateOf(heroes[0])
            }

            LaunchedEffect(key1 = Unit) {
                CoroutineScope(Dispatchers.IO).launch {
                    val moshi = Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build()
                    val api = Retrofit.Builder()
                        .baseUrl(ApiConstants.BASE_URL)
                        .addConverterFactory(MoshiConverterFactory.create(moshi))
                        .build()
                        .create(MarvelApi::class.java)

                    val rep: MarvelRepository = MarvelRepositoryImpl(marvelApi = api)

                    val allCharacters = rep.getAllCharacters()
                    val heroById = rep.getHeroById(CharacterIds.DEADPOOL_ID.toString())

                    if (heroById.code == 200)
                        with(heroById.data?.results?.get(0)) {
                            selectedHero.value = Hero(
                                heroId = this?.id,
                                heroNameResId = this?.name,
                                heroQuoteResId = this?.description,
                                heroImageResId = "${
                                    this?.thumbnail?.path?.replace(
                                        oldValue = "http",
                                        newValue = "https"
                                    )
                                }.${this?.thumbnail?.extension}"
                            )
                        }

                    if (allCharacters.code == 200) {
                        heroes.clear()
                        with(allCharacters.data!!.results) {
                            for (i in this.indices) {
                                val results: Results = this[i]
                                val hero = Hero(
                                    heroId = results.id,
                                    heroNameResId = results.name,
                                    heroQuoteResId = results.description,
                                    heroImageResId = "${
                                        results.thumbnail?.path?.replace(
                                            oldValue = "http",
                                            newValue = "https"
                                        )
                                    }.${results.thumbnail?.extension}"
                                )
                                heroes.add(hero)
                            }
                        }
                    }
                }
            }

            MarvelTheme(darkTheme = true) {
                ApplySystemColors()
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {


                    Content(navController, selectedHero, heroes)
                }
            }
        }
    }
}

@Composable
private fun Content(
    navController: NavHostController,
    selectedHero: MutableState<Hero>,
    heroes: MutableList<Hero>
) {
    NavHost(navController = navController, startDestination = Screens.MAIN_PAGE) {
        composable(route = Screens.MAIN_PAGE) {
            MainPage(
                heroes = heroes,
                onHeroClick = { hero ->
                    selectedHero.value = hero
                    navController.navigate(route = Screens.HERO_PAGE)
                }
            )
        }
        composable(route = Screens.HERO_PAGE) {
            HeroPage(hero = selectedHero.value, onBackCLick = {
                navController.popBackStack()
            })
        }
    }
}