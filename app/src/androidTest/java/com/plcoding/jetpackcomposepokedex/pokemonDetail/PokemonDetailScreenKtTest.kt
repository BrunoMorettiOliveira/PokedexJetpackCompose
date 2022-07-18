package com.plcoding.jetpackcomposepokedex.pokemonDetail

import androidx.compose.material.MaterialTheme.colors
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
    fun TestFirstPokemon() {
        composeTestRule.setContent {
            JetpackComposePokedexTheme {
                val navController = rememberNavController()
                val defaultDominantColor = colors.surface
                var dominantColor by remember {
                    mutableStateOf(defaultDominantColor)
                }
                PokemonDetailScreen(
                    navController = navController,
                    viewModel = viewModelSucess,
                    dominantColor = dominantColor,
                    topPadding = 20.dp,
                    pokemonImageSize = 200.dp,
                    pokemonName = "bulbasaur"
                )
            }
        }
    }


}
