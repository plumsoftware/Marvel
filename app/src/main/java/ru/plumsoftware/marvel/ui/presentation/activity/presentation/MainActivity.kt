package ru.plumsoftware.marvel.ui.presentation.activity.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.core.view.WindowCompat
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import org.koin.core.component.KoinComponent
import ru.plumsoftware.data.model.uimodel.Hero
import ru.plumsoftware.marvel.ui.presentation.activity.store.MainActivityStore
import ru.plumsoftware.marvel.ui.presentation.activity.viewmodel.MainActivityViewModel
import ru.plumsoftware.marvel.model.constants.Screens
import ru.plumsoftware.marvel.ui.presentation.heropage.presentation.HeroPage
import ru.plumsoftware.marvel.ui.presentation.heropage.viewmodel.HeroViewModel
import ru.plumsoftware.marvel.ui.presentation.mainpage.presentation.MainPage
import ru.plumsoftware.marvel.ui.presentation.mainpage.viewmodel.MainViewModel
import ru.plumsoftware.marvel.ui.theme.ApplySystemColors
import ru.plumsoftware.marvel.ui.theme.MarvelTheme

class MainActivity : ComponentActivity(), KoinComponent {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        WindowCompat.setDecorFitsSystemWindows(window, false)

        val mainActivityViewModel = MainActivityViewModel(
            heroId = intent.getIntExtra("hero_id", -1),
        )

        setContent {
            MarvelTheme(darkTheme = true) {
                ApplySystemColors()
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Content(mainActivityViewModel)
                }
            }
        }
    }
}


@Composable
private fun Content(
    mainActivityViewModel: MainActivityViewModel,
) {

    val navController = rememberNavController()

    val state = mainActivityViewModel.state.collectAsState().value

    if (state.messageId > 0 && state.selectedHero != Hero()) {
        navController.navigate(route = Screens.MAIN_PAGE)
    }

    NavHost(navController = navController, startDestination = Screens.MAIN_PAGE) {
        composable(route = Screens.MAIN_PAGE) {
            val viewModel = MainViewModel(
                output = { output ->
                    when (output) {
                        MainViewModel.Output.NavigateTo -> {
                            navController.navigate(route = Screens.HERO_PAGE)
                        }

                        is MainViewModel.Output.ChangeSelectedHero -> {
                            mainActivityViewModel.onIntent(
                                MainActivityStore.Intent.ChangeSelectedHero(
                                    selectedHero = output.selectedHero
                                )
                            )
                        }
                    }
                }
            )
            MainPage(mainViewModel = viewModel, onIntent = viewModel::onIntent)
        }
        composable(route = Screens.HERO_PAGE) {
            val viewModel = HeroViewModel(
                hero = state.selectedHero,
                output = { output ->
                    when (output) {
                        HeroViewModel.Output.OnBackClicked -> {
                            navController.popBackStack()
                        }
                    }
                }
            )
            HeroPage(heroViewModel = viewModel)
        }
    }
}