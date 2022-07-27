package com.plcoding.jetpackcomposepokedex.pokemonDetailTestInstrumented

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.plcoding.jetpackcomposepokedex.pokemonDetail.PokemonDetailViewModel
import com.plcoding.jetpackcomposepokedex.pokemonDetailTestInstrumented.di.FakeErrorDataRepoImpl
import com.plcoding.jetpackcomposepokedex.pokemonDetailTestInstrumented.di.FakeSucessDataRepoImpl
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test


class PokemonDetailViewModelTest {

    @get:Rule()
    val rule = InstantTaskExecutorRule()

    private lateinit var viewModel: PokemonDetailViewModel

    @Before
    fun setup() {
        Dispatchers.setMain(Dispatchers.Unconfined)
    }

    @After
    fun cleanUp() {
        Dispatchers.resetMain()
    }

    @Test
    fun `When repository return sucess`() = runBlockingTest {
        viewModel = PokemonDetailViewModel(FakeSucessDataRepoImpl())
        //Asserts
        assertEquals(
            FakeSucessDataRepoImpl().getPokemonInfo("").data,
            viewModel.getPokemonInfo("").data
        )
        assert(viewModel.getPokemonInfo("").message == null)
    }

    @Test
    fun `When repository return error`() = runBlockingTest {
        viewModel = PokemonDetailViewModel(FakeErrorDataRepoImpl())
        //Asserts
        assertEquals(
            "Error Mockado",
            viewModel.getPokemonInfo("").message
        )
        assert(viewModel.getPokemonInfo("").data == null)
    }

}

