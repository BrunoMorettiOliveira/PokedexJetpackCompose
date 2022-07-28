package com.plcoding.jetpackcomposepokedex.pokemonListTestInstrumented

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.navigation.compose.rememberNavController
import com.plcoding.jetpackcomposepokedex.MainActivity
import com.plcoding.jetpackcomposepokedex.pokemonList.PokemonListScreen
import com.plcoding.jetpackcomposepokedex.pokemonList.PokemonListViewModel
import com.plcoding.jetpackcomposepokedex.pokemonListTestInstrumented.di.FakeErrorDataRepoImpl
import com.plcoding.jetpackcomposepokedex.pokemonListTestInstrumented.di.FakeSucessDataRepoImpl
import com.plcoding.jetpackcomposepokedex.ui.theme.JetpackComposePokedexTheme
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
class PokemonListScreenKtTest {

    @get:Rule(order = 1)
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @get:Rule(order = 0)
    var hiltRule = HiltAndroidRule(this)

    private lateinit var viewModelSucess: PokemonListViewModel
    private lateinit var viewModelError: PokemonListViewModel


    @Before
    fun init() {
        hiltRule.inject()
        viewModelSucess = PokemonListViewModel(FakeSucessDataRepoImpl())
        viewModelError = PokemonListViewModel(FakeErrorDataRepoImpl())
    }


    @Test
    fun TestFirstPokemon() {
        composeTestRule.setContent {
            JetpackComposePokedexTheme {
                val navController = rememberNavController()
                PokemonListScreen(navController = navController, viewModelSucess)
            }
        }
        composeTestRule.onNode(hasTestTag("BoxClick1"), true)
            .assertIsEnabled()
            .assertIsDisplayed()
    }

    @Test
    fun TestSearchPokemon() {
        composeTestRule.setContent {
            JetpackComposePokedexTheme {
                val navController = rememberNavController()
                PokemonListScreen(navController = navController, viewModelSucess)
            }
        }
        composeTestRule.onNode(hasTestTag("SearchBar"), true).performClick()
        composeTestRule.onNode(hasTestTag("SearchBar"), true).performTextInput("bulbasaur")
        composeTestRule.onNode(hasTestTag("BoxClick1"), true)
            .assertIsEnabled()
            .assertIsDisplayed()
    }

    @Test
    fun TestSearchBar() {
        composeTestRule.setContent {
            JetpackComposePokedexTheme {
                val navController = rememberNavController()
                PokemonListScreen(navController = navController, viewModelSucess)
            }
        }
        composeTestRule.onNode(hasTestTag("SearchBar"), true)
            .assertHasClickAction()
            .assertIsDisplayed()
            .assertIsEnabled()
    }

    @Test
    fun TestSearchNotFoundPokemon() {
        composeTestRule.setContent {
            JetpackComposePokedexTheme {
                val navController = rememberNavController()
                PokemonListScreen(navController = navController, viewModelSucess)
            }
        }
        composeTestRule.onNode(hasTestTag("SearchBar"), true).performClick()
        composeTestRule.onNode(hasTestTag("SearchBar"), true).performTextInput("dragonite")
        composeTestRule.onNode(hasTestTag("BoxClick1"), true)
            .assertDoesNotExist()
    }

    @Test
    fun LogoApear() {
        composeTestRule.setContent {
            JetpackComposePokedexTheme {
                val navController = rememberNavController()
                PokemonListScreen(navController = navController, viewModelSucess)
            }
        }
        composeTestRule.onNode(hasTestTag("LogoListScreen"), true)
            .assertIsEnabled()
            .assertIsDisplayed()
    }

    @Test
    fun AreClickable() {
        composeTestRule.setContent {
            JetpackComposePokedexTheme {
                val navController = rememberNavController()
                PokemonListScreen(navController = navController, viewModelSucess)
            }
        }
        composeTestRule.onNode(hasTestTag("BoxClick1"), true).assertHasClickAction()
    }

    @Test
    fun ErrorScreenTest() {
        composeTestRule.setContent {
            JetpackComposePokedexTheme {
                val navController = rememberNavController()
                PokemonListScreen(navController = navController, viewModelError)
            }
        }
        composeTestRule.onNode(hasTestTag("BoxError"), true)
            .assertIsEnabled()
            .assertIsDisplayed()
    }

    @Test
    fun ErrorButtonTest() {
        composeTestRule.setContent {
            JetpackComposePokedexTheme {
                val navController = rememberNavController()
                PokemonListScreen(navController = navController, viewModelError)
            }
        }
        composeTestRule.onNode(hasTestTag("ButtonError"), true)
            .assertIsEnabled()
            .assertIsDisplayed()
            .assertHasClickAction()
    }
}

