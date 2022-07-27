package com.plcoding.jetpackcomposepokedex.pokemonDetailTestInstrumented.di

import com.nhaarman.mockitokotlin2.mock
import com.plcoding.jetpackcomposepokedex.data.remote.responses.Pokemon
import com.plcoding.jetpackcomposepokedex.repository.PokemonRepository
import com.plcoding.jetpackcomposepokedex.util.Resource

class FakeErrorDataRepoImpl : PokemonRepository(mock()) {

    override suspend fun getPokemonInfo(pokemonName: String): Resource<Pokemon> =
        Resource.Error("Erro mockado!")
}