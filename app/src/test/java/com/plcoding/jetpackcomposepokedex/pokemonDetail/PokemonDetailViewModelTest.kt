package com.plcoding.jetpackcomposepokedex.pokemonDetail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule


class PokemonDetailViewModelTest {

    @get:Rule()
    val rule = InstantTaskExecutorRule()

    private lateinit var viewModel: PokemonDetailViewModel

    @Before
    fun setup() {
        Dispatchers.setMain(Dispatchers.Unconfined)
        viewModel = PokemonDetailViewModel(FakeSucessDataRepoImpl())
    }

    @After
    fun cleanUp() {
        Dispatchers.resetMain()
    }

}

