package com.safwa.application.data.repository

import com.safwa.application.data.datasource.api.ApiServices
import com.safwa.application.data.datasource.db.PokemonDao
import com.safwa.application.data.models.Pokemon
import javax.inject.Inject

class PokemonRepository @Inject constructor(private val apiService: ApiServices ,private val pokemonDao: PokemonDao) {

    fun getPokemonListFromApi() = apiService.getPokemonList()
    fun getPokemonListFromDb() = pokemonDao.getPokemonList()
    fun insertPokemon(pokemon: Pokemon) = pokemonDao.insertPokemon(pokemon)
    fun deletePokemon(pokemon: Pokemon)  = pokemonDao.deletePokemon(pokemon.name)

}