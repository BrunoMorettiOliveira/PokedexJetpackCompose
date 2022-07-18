package com.plcoding.jetpackcomposepokedex.pokemonlist.di

import com.nhaarman.mockitokotlin2.mock
import com.plcoding.jetpackcomposepokedex.data.remote.responses.PokemonList
import com.plcoding.jetpackcomposepokedex.repository.PokemonRepository
import com.plcoding.jetpackcomposepokedex.util.Resource

class FakeErrorDataRepoImpl : PokemonRepository(mock()) {

    override suspend fun getPokemonList(limit: Int, offset: Int): Resource<PokemonList> =
        Resource.Error("Erro mockado!")
}