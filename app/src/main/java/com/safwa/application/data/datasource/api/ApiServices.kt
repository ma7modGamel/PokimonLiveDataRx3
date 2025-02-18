package com.safwa.application.data.datasource.api

import com.safwa.application.data.models.PokemonResponse
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET

interface ApiServices {


    @GET()
    fun getPokemonList() : Observable<PokemonResponse>
}