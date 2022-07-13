package com.plcoding.jetpackcomposepokedex.pokemonList

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.compose.runtime.*
import androidx.lifecycle.*
import com.nhaarman.mockitokotlin2.mock
import com.plcoding.jetpackcomposepokedex.data.remote.PokeApi
import com.plcoding.jetpackcomposepokedex.data.remote.responses.Pokemon
import com.plcoding.jetpackcomposepokedex.data.remote.responses.PokemonList
import com.plcoding.jetpackcomposepokedex.pokemonlist.PokemonListViewModel
import com.plcoding.jetpackcomposepokedex.repository.PokemonRepository
import com.plcoding.jetpackcomposepokedex.util.Resource
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock

class PokemonListViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    private lateinit var viewModelSucess: PokemonListViewModel
    private lateinit var viewModelError: PokemonListViewModel



    @Test
    fun `when view model repository get sucess`() = runBlockingTest {
        viewModelSucess = PokemonListViewModel(MockRepositorySucess())


        viewModelSucess.loadPokemonPaginated()



        //Asserts
        assertEquals(false, viewModelSucess.isLoading.value)
        assertEquals("", viewModelSucess.loadError.value)
    }


    @Test
    fun `when view model repository get error`() {
        viewModelError = PokemonListViewModel(MockRepositoryError())

        viewModelError.loadPokemonPaginated()

        //Asserts
        assertEquals(false, viewModelError.isLoading.value)
        assertEquals("", viewModelError.loadError.value)
    }
}


@Mock
private lateinit var pokemonListMock: PokemonList

@Mock
private lateinit var pokemonMock: Pokemon



class MockRepositorySucess() : PokemonRepository(mock()) {
    override suspend fun getPokemonList(limit: Int, offset: Int): Resource<PokemonList> {
        return Resource.Sucess(pokemonListMock)
    }

    override suspend fun getPokemonInfo(pokemonName: String): Resource<Pokemon> {
        return Resource.Sucess(pokemonMock)
    }

}

class MockRepositoryError() : PokemonRepository(mock()) {
    override suspend fun getPokemonList(limit: Int, offset: Int): Resource<PokemonList> {
        return Resource.Error(message = "Erro Mockado")
    }

    override suspend fun getPokemonInfo(pokemonName: String): Resource<Pokemon> {
        return Resource.Error(message = "Erro Mockado")
    }
}




