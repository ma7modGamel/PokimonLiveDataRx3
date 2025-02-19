package com.safwa.application.ui.home.viewmodel

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.safwa.application.data.models.Pokemon
import com.safwa.application.data.repository.PokemonRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor( var repository: PokemonRepository) : ViewModel() {

    private var pokemonList = MutableLiveData<List<Pokemon>>()
    val _pokemonList: LiveData<List<Pokemon>> = pokemonList


    @SuppressLint("CheckResult")
    fun getPokemonFromDb() {


        repository.getPokemonListFromDb()
            .subscribeOn(Schedulers.io())  // تشغيل في IO thread
            .observeOn(AndroidSchedulers.mainThread()) // تحديث LiveData في main thread
            .subscribe({ list ->
                pokemonList.value = list
            }, { error ->
                Log.e("FavoriteViewModel", "Error fetching data: ${error.message}")
            })


    }


    fun deleteItemFromFavorite(pokemon : Pokemon){
        repository.deletePokemon(pokemon)
    }

}