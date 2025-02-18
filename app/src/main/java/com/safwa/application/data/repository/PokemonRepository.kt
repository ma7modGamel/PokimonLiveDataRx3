package com.safwa.application.data.repository

import com.safwa.application.data.datasource.api.ApiServices
import javax.inject.Inject

class PokemonRepository @Inject constructor(private val apiService: ApiServices) {

    fun getPokemonListFromApi() = apiService.getPokemonList()

}