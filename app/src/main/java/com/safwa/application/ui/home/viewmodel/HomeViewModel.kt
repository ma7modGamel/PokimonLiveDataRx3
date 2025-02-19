package com.safwa.application.ui.home.viewmodel

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.safwa.application.data.datasource.api.RetrofitConstant
import com.safwa.application.data.models.Pokemon
import com.safwa.application.data.repository.PokemonRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject


@HiltViewModel
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
                        val pokemonId =
                            pokemonResult.url.substringAfter("pokemon/").substringBefore("/")
                        pokemonResult.copy(url = RetrofitConstant.URLImage + pokemonId + ".png")

                    }
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                listPokemon.value = it
                Timber.e("getPokemonFromApixx: " + listPokemon.value)
            }, {
                Timber.e(it.message.toString())
            }).let {
                compositeDisposable.add(it) // ✅ حفظ

            }

    }


    @SuppressLint("CheckResult")
    fun addItemToFavorite(pokemon: Pokemon, isFav: Boolean) {
        Completable.fromCallable {
            repository.insertPokemon(pokemon)
        }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                Timber.e("addItemToFavorite: " + pokemon.name)
            }, {
                Timber.e("ERROR" + it.message.toString())
            })
    }
}