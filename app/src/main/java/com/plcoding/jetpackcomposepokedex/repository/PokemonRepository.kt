package com.plcoding.jetpackcomposepokedex.repository

import com.plcoding.jetpackcomposepokedex.data.remote.PokeApi
import com.plcoding.jetpackcomposepokedex.data.remote.responses.Pokemon
import com.plcoding.jetpackcomposepokedex.data.remote.responses.PokemonList
import com.plcoding.jetpackcomposepokedex.util.Resource
import dagger.hilt.android.scopes.ActivityScoped
import java.lang.Exception
import javax.inject.Inject

@ActivityScoped
open class PokemonRepository @Inject constructor(
    val api : PokeApi
){
    open suspend fun getPokemonList(limit : Int, offset : Int) : Resource<PokemonList>{
        val response = try {
            api.getPokemonList(limit,offset)
        }catch (e : Exception){
            return Resource.Error("An unknown error occured")
        }
        return Resource.Sucess(response)
    }

    open suspend fun getPokemonInfo(pokemonName: String) : Resource<Pokemon>{
        val response = try {
            api.getPokemonInfo(pokemonName)
        }catch (e : Exception){
            return Resource.Error("An unknown error occured")
        }
        return Resource.Sucess(response)
    }
}