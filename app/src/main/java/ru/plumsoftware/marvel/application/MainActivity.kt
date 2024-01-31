package ru.plumsoftware.marvel.application

import android.os.Bundle
import android.util.Log
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
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import ru.plumsoftware.data.model.dao.Character
import ru.plumsoftware.data.model.dao.CharacterDatabase
import ru.plumsoftware.data.storage.MarvelStorage
import ru.plumsoftware.marvel.model.constants.Screens
import ru.plumsoftware.marvel.model.uimodel.Hero
import ru.plumsoftware.marvel.ui.presentation.heropage.presentation.HeroPage
import ru.plumsoftware.marvel.ui.presentation.mainpage.presentation.MainPage
import ru.plumsoftware.marvel.ui.presentation.mainpage.store.MainStore
import ru.plumsoftware.marvel.ui.presentation.mainpage.viewmodel.MainViewModel
import ru.plumsoftware.marvel.ui.theme.ApplySystemColors
import ru.plumsoftware.marvel.ui.theme.MarvelTheme

class MainActivity : ComponentActivity(), KoinComponent {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {

            val navController = rememberNavController()
            val heroes = remember {
                mutableListOf<Hero>()
            }
            val selectedHero = remember {
                mutableStateOf(Hero())
            }

            val isError = remember { mutableStateOf(false) }

//            LaunchedEffect(key1 = Unit) {
//                CoroutineScope(Dispatchers.IO).launch {
//                    try {
//                    val moshi = Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build()
//                    val api = Retrofit.Builder()
//                        .baseUrl(ApiConstants.BASE_URL)
//                        .addConverterFactory(MoshiConverterFactory.create(moshi))
//                        .build()
//                        .create(MarvelApi::class.java)
//
//                    val storage = MarvelStorage(
//                        getAllCharactersUseCaseUse = GetAllCharactersUseCase(
//                            MarvelRepositoryImpl(marvelApi = api)
//                        )
//                    )

//                        val rep: ru.plumsoftware.domain.repository.MarvelRepository = MarvelRepositoryImpl(marvelApi = api)

//                        val get = storage.get()
//                    val get = storage.get()
//                    Log.i("MARVEL", get.toString())


//                        val heroById = rep.getHeroById(CharacterIds.DEADPOOL_ID.toString())

//                        if (heroById.code == 200)
//                            with(heroById.data?.results?.get(0)) {
//                                selectedHero.value = Hero(
//                                    heroId = this?.id,
//                                    heroNameResId = this?.name,
//                                    heroQuoteResId = this?.description,
//                                    heroImageResId = "${
//                                        this?.thumbnail?.path?.replace(
//                                            oldValue = "http",
//                                            newValue = "https"
//                                        )
//                                    }.${this?.thumbnail?.extension}"
//                                )
//                            }

//                    if (get.code == 200) {
//                        db.dao.deleteAllData()
//                        heroes.clear()
//                        with(get.data!!.results) {
//                            for (i in this.indices) {
//                                val results = this[i]
//                                val hero = Hero(
//                                    heroId = results.id,
//                                    heroNameResId = results.name,
//                                    heroQuoteResId = results.description,
//                                    heroImageResId = "${
//                                        results.thumbnail?.path?.replace(
//                                            oldValue = "http",
//                                            newValue = "https"
//                                        )
//                                    }.${results.thumbnail?.extension}"
//                                )
//                                heroes.add(hero)
//                                db.dao.upsertData(
//                                    characters = Character(
//                                        heroId = hero.heroId!!,
//                                        name = hero.heroNameResId!!,
//                                        description = hero.heroQuoteResId!!
//                                    )
//                                )
//                            }
//                        }
//                    } else {
//                        heroes.clear()
//                        val allCharacters1: List<Character> = db.dao.getAllCharacters()
//
//                        for (i in allCharacters1.indices) {
//                            val character = allCharacters1[i]
//                            val hero = Hero(
//                                heroId = character.heroId,
//                                heroNameResId = character.name,
//                                heroQuoteResId = character.description
//                            )
//                            heroes.add(hero)
//                        }
//                    }
//                    } catch (e: Exception) {
//                    isError.value = true
//                    heroes.clear()
//                    val allCharacters1: List<Character> = db.dao.getAllCharacters()
//
//                    for (i in allCharacters1.indices) {
//                        val character = allCharacters1[i]
//                        val hero = Hero(
//                            heroId = character.heroId,
//                            heroNameResId = character.name,
//                            heroQuoteResId = character.description
//                        )
//                        heroes.add(hero)
//                    }
//                }
//            }
//            }

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
private fun Content(
    navController: NavHostController,
    selectedHero: MutableState<Hero>,
) {
    NavHost(navController = navController, startDestination = Screens.MAIN_PAGE) {
        composable(route = Screens.MAIN_PAGE) {
            val viewModel = MainViewModel(
                action = {
                    MainStore.Action.DoMarvelRequest
                },
                output = { output ->
                    when (output) {
                        MainViewModel.Output.NavigateTo -> {
                            navController.navigate(route = Screens.HERO_PAGE)
                        }
                    }
                }
            )
            MainPage(mainViewModel = viewModel, onIntent = viewModel::onIntent)
        }
        composable(route = Screens.HERO_PAGE) {
            HeroPage(hero = selectedHero.value, onBackCLick = {
                navController.popBackStack()
            })
        }
    }
}