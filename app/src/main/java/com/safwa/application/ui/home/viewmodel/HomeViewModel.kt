package com.safwa.application.ui.home.viewmodel

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.safwa.application.data.datasource.api.RetrofitConstant
import com.safwa.application.data.models.Pokemon
import com.safwa.application.data.repository.PokemonRepository
import com.safwa.application.utils.Logger
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class HomeViewModel @Inject constructor(var repository: PokemonRepository) : ViewModel() {

    private val listPokemon = MutableLiveData<List<Pokemon>>()
    val _listPokemon: LiveData<List<Pokemon>> = listPokemon

    private val compositeDisposable = CompositeDisposable()
    @SuppressLint("CheckResult")
    fun getPokemonFromApi() {
        repository.getPokemonListFromApi()
            .subscribeOn(Schedulers.io())
            .map { pokemonResponse ->
                pokemonResponse.results
                    .map { pokemonResult ->
                        var pokemonId = pokemonResult.url.substringAfter("pokemon/").substringBefore("/")
                        pokemonResult.copy(url = RetrofitConstant.URLImage+pokemonId+".png")

                    }
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe ({
                listPokemon.value = it
                Log.e("TAG", "getPokemonFromApi: "+it )
            },{
                Log.e("PokemonViewModel Url : - 4 ", it.message.toString())
            }).let {
            compositeDisposable.add(it) // ✅ حفظ

        }

    }
}