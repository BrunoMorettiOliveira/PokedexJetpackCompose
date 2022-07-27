package com.plcoding.jetpackcomposepokedex.pokemonListTestInstrumented.di

import com.nhaarman.mockitokotlin2.mock
import com.plcoding.jetpackcomposepokedex.data.remote.responses.PokemonList
import com.plcoding.jetpackcomposepokedex.data.remote.responses.Result
import com.plcoding.jetpackcomposepokedex.repository.PokemonRepository
import com.plcoding.jetpackcomposepokedex.util.Resource

class FakeSucessDataRepoImpl : PokemonRepository(mock()) {

    override suspend fun getPokemonList(limit: Int, offset: Int): Resource<PokemonList> =
        Resource.Sucess(
            PokemonList(
                1154, "https://pokeapi.co/api/v2/pokemon/?offset=0&limit=1", String(), listOf(
                    Result("bulbasaur", "https://pokeapi.co/api/v2/pokemon/1/"),
                    Result("ivysaur", "https://pokeapi.co/api/v2/pokemon/2/"),
                    Result("venusaur", "https://pokeapi.co/api/v2/pokemon/3/"),
                    Result("charmander", "https://pokeapi.co/api/v2/pokemon/4/"),
                    Result("charmeleon", "https://pokeapi.co/api/v2/pokemon/5/"),
                    Result("charizard", "https://pokeapi.co/api/v2/pokemon/6/"),
                    Result("squirtle", "https://pokeapi.co/api/v2/pokemon/7/"),
                    Result("wartortle", "https://pokeapi.co/api/v2/pokemon/8/")
                )
            )
        )
}