package com.safwa.application.ui.home.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.safwa.application.data.models.Pokemon
import com.safwa.application.data.repository.PokemonRepository
import io.reactivex.rxjava3.schedulers.Schedulers
import rx.android.schedulers.AndroidSchedulers
import javax.inject.Inject

class HomeViewModel @Inject constructor(var repository: PokemonRepository) : ViewModel() {

    private val listPokemon = MutableLiveData<List<Pokemon>>()
    val _listPokemon: LiveData<List<Pokemon>> = listPokemon

    fun getPokemonFromApi() {
        repository.getPokemonListFromApi()
            .subscribeOn(Schedulers.io())

    }
}