package com.plcoding.jetpackcomposepokedex.pokemonList

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nhaarman.mockitokotlin2.mock
import com.plcoding.jetpackcomposepokedex.data.models.PokedexListEntry
import com.plcoding.jetpackcomposepokedex.data.remote.PokeApi
import com.plcoding.jetpackcomposepokedex.data.remote.responses.PokemonList
import com.plcoding.jetpackcomposepokedex.data.remote.responses.Result
import com.plcoding.jetpackcomposepokedex.pokemonlist.PokemonListViewModel
import com.plcoding.jetpackcomposepokedex.repository.PokemonRepository
import com.plcoding.jetpackcomposepokedex.util.Resource
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock

class PokemonListViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    private lateinit var viewModel: PokemonListViewModel

    @Before
    fun setup() {
        Dispatchers.setMain(Dispatchers.Unconfined)
    }

    @After
    fun cleanUp() {
        Dispatchers.resetMain()
    }


    @Test
    fun `when view model repository get sucess`() {
        viewModel = PokemonListViewModel(MockRepositorySucess())

        //Asserts
        assertEquals(
            listOf(
                PokedexListEntry(
                    pokemonName = "Bulbasaur",
                    imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/1.png",
                    number = 1
                )
            ), viewModel.pokemonList.value
        )
        assertEquals(false, viewModel.isSearching.value)
        assertEquals(false, viewModel.isLoading.value)
        assertEquals("", viewModel.loadError.value)
    }


    @Test
    fun `when view model repository get error`() {
        viewModel = PokemonListViewModel(MockRepositoryError())

        //Asserts
        assert(viewModel.pokemonList.value.isEmpty())
        assertEquals(false, viewModel.isSearching.value)
        assertEquals(false, viewModel.isLoading.value)
        assertEquals("Erro Mockado", viewModel.loadError.value)
    }

    @Test
    fun `when view model searchPokemonList can find a pokemon`() {
        viewModel = PokemonListViewModel(MockRepositorySucess())
        viewModel.searchPokemonList("bulbasaur")

        //Asserts
        assertEquals(
            listOf(
                PokedexListEntry(
                    pokemonName = "Bulbasaur",
                    imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/1.png",
                    number = 1
                )
            ), viewModel.pokemonList.value
        )
        assertEquals(false, viewModel.isSearching.value)
        assertEquals(false, viewModel.isLoading.value)
        assertEquals("", viewModel.loadError.value)
    }

    @Test
    fun `when view model searchPokemonList can't find a pokemon`() {
        viewModel = PokemonListViewModel(MockRepositorySucess())
        viewModel.searchPokemonList("dragonite")

        //Asserts
        assert(viewModel.pokemonList.value.isEmpty())
        assertEquals(false, viewModel.isSearching.value)
        assertEquals(false, viewModel.isLoading.value)
        assertEquals("", viewModel.loadError.value)
    }
}

private var pokemonListMock = PokemonList(
    1154, "https://pokeapi.co/api/v2/pokemon/?offset=1&limit=1", String(), listOf(
        Result("bulbasaur", "https://pokeapi.co/api/v2/pokemon/1/")
    )
)

@Mock
private val api: PokeApi = mock()

class MockRepositorySucess() : PokemonRepository(api) {
    override suspend fun getPokemonList(limit: Int, offset: Int): Resource<PokemonList> {
        return Resource.Sucess(pokemonListMock)
    }
}

class MockRepositoryError() : PokemonRepository(api) {
    override suspend fun getPokemonList(limit: Int, offset: Int): Resource<PokemonList> {
        return Resource.Error(message = "Erro Mockado")
    }
}