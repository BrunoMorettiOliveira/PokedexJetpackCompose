package com.plcoding.jetpackcomposepokedex.pokemonDetail.di

import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.mock
import com.plcoding.jetpackcomposepokedex.data.remote.responses.*
import com.plcoding.jetpackcomposepokedex.repository.PokemonRepository
import com.plcoding.jetpackcomposepokedex.util.Resource

class FakeErrorDataRepoImpl : PokemonRepository(mock()) {

    override suspend fun getPokemonInfo(pokemonName: String): Resource<Pokemon> =
        Resource.Error("Error Mockado")
}