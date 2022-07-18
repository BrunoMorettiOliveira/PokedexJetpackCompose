package com.plcoding.jetpackcomposepokedex.pokemonDetail

import androidx.compose.material.MaterialTheme.colors
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.test.assertHasClickAction
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.plcoding.jetpackcomposepokedex.MainActivity
import com.plcoding.jetpackcomposepokedex.pokemonDetail.di.FakeErrorDataRepoImpl
import com.plcoding.jetpackcomposepokedex.pokemonDetail.di.FakeSucessDataRepoImpl
import com.plcoding.jetpackcomposepokedex.ui.theme.JetpackComposePokedexTheme
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
class PokemonDetailScreenKtTest {

    @get:Rule(order = 1)
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @get:Rule(order = 0)
    var hiltRule = HiltAndroidRule(this)

    private lateinit var viewModelSucess: PokemonDetailViewModel
    private lateinit var viewModelError: PokemonDetailViewModel


    @Before
    fun init() {
        hiltRule.inject()
        viewModelSucess = PokemonDetailViewModel(FakeSucessDataRepoImpl())
        viewModelError = PokemonDetailViewModel(FakeErrorDataRepoImpl())
    }

    @Test
    fun ArrowBackTest() {
        composeTestRule.setContent {
            JetpackComposePokedexTheme {
                val navController = rememberNavController()
                PokemonDetailScreen(
                    navController = navController,
                    viewModel = viewModelSucess,
                    dominantColor = Color.Green,
                    topPadding = 20.dp,
                    pokemonImageSize = 200.dp,
                    pokemonName = "bulbasaur"
                )
            }
        }
        composeTestRule.onNode(hasTestTag("ArrowBack"),true)
            .assertIsEnabled()
            .assertHasClickAction()
            .assertIsDisplayed()
    }

    @Test
    fun ImagePokemonTest() {
        composeTestRule.setContent {
            JetpackComposePokedexTheme {
                val navController = rememberNavController()
                PokemonDetailScreen(
                    navController = navController,
                    viewModel = viewModelSucess,
                    dominantColor = Color.Green,
                    topPadding = 20.dp,
                    pokemonImageSize = 200.dp,
                    pokemonName = "bulbasaur"
                )
            }
        }
        composeTestRule.onNode(hasTestTag("ImagePokemon"),true)
            .assertIsEnabled()
            .assertIsDisplayed()
    }

    @Test
    fun PokemonNameTest() {
        composeTestRule.setContent {
            JetpackComposePokedexTheme {
                val navController = rememberNavController()
                PokemonDetailScreen(
                    navController = navController,
                    viewModel = viewModelSucess,
                    dominantColor = Color.Green,
                    topPadding = 20.dp,
                    pokemonImageSize = 200.dp,
                    pokemonName = "bulbasaur"
                )
            }
        }
        composeTestRule.onNode(hasTestTag("PokemonName"),true)
            .assertIsEnabled()
            .assertIsDisplayed()
    }

    @Test
    fun DetailBoxTest() {
        composeTestRule.setContent {
            JetpackComposePokedexTheme {
                val navController = rememberNavController()
                PokemonDetailScreen(
                    navController = navController,
                    viewModel = viewModelSucess,
                    dominantColor = Color.Green,
                    topPadding = 20.dp,
                    pokemonImageSize = 200.dp,
                    pokemonName = "bulbasaur"
                )
            }
        }
        composeTestRule.onNode(hasTestTag("DetailBox"),true)
            .assertIsEnabled()
            .assertIsDisplayed()
    }

    @Test
    fun TypesTest() {
        composeTestRule.setContent {
            JetpackComposePokedexTheme {
                val navController = rememberNavController()
                PokemonDetailScreen(
                    navController = navController,
                    viewModel = viewModelSucess,
                    dominantColor = Color.Green,
                    topPadding = 20.dp,
                    pokemonImageSize = 200.dp,
                    pokemonName = "bulbasaur"
                )
            }
        }
        composeTestRule.onNode(hasTestTag("Types"),true)
            .assertIsEnabled()
            .assertIsDisplayed()
    }

    @Test
    fun WeigthAndHeigthTest() {
        composeTestRule.setContent {
            JetpackComposePokedexTheme {
                val navController = rememberNavController()
                PokemonDetailScreen(
                    navController = navController,
                    viewModel = viewModelSucess,
                    dominantColor = Color.Green,
                    topPadding = 20.dp,
                    pokemonImageSize = 200.dp,
                    pokemonName = "bulbasaur"
                )
            }
        }
        composeTestRule.onNode(hasTestTag("Weigth"),true)
            .assertIsEnabled()
            .assertIsDisplayed()

        composeTestRule.onNode(hasTestTag("Height"),true)
            .assertIsEnabled()
            .assertIsDisplayed()
    }

    @Test
    fun BaseStatTextTest() {
        composeTestRule.setContent {
            JetpackComposePokedexTheme {
                val navController = rememberNavController()
                PokemonDetailScreen(
                    navController = navController,
                    viewModel = viewModelSucess,
                    dominantColor = Color.Green,
                    topPadding = 20.dp,
                    pokemonImageSize = 200.dp,
                    pokemonName = "bulbasaur"
                )
            }
        }
        composeTestRule.onNode(hasTestTag("BaseStatText"),true)
            .assertIsEnabled()
            .assertIsDisplayed()
    }

    @Test
    fun StatTest() {
        composeTestRule.setContent {
            JetpackComposePokedexTheme {
                val navController = rememberNavController()
                PokemonDetailScreen(
                    navController = navController,
                    viewModel = viewModelSucess,
                    dominantColor = Color.Green,
                    topPadding = 20.dp,
                    pokemonImageSize = 200.dp,
                    pokemonName = "bulbasaur"
                )
            }
        }
        composeTestRule.onNode(hasTestTag("StatHP"),true)
            .assertIsEnabled()
            .assertIsDisplayed()

        composeTestRule.onNode(hasTestTag("StatAtk"),true)
            .assertIsEnabled()
            .assertIsDisplayed()

        composeTestRule.onNode(hasTestTag("StatDef"),true)
            .assertIsEnabled()
            .assertIsDisplayed()

        composeTestRule.onNode(hasTestTag("StatSpAtk"),true)
            .assertIsEnabled()
            .assertIsDisplayed()

        composeTestRule.onNode(hasTestTag("StatSpDef"),true)
            .assertIsEnabled()
            .assertIsDisplayed()

        composeTestRule.onNode(hasTestTag("StatSpd"),true)
            .assertIsEnabled()
            .assertIsDisplayed()
    }



}
