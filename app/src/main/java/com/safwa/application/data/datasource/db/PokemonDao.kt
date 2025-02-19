package com.safwa.application.data.datasource.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.safwa.application.data.models.Pokemon
import io.reactivex.rxjava3.core.Single

@Dao
interface PokemonDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPokemon(pokemon: Pokemon)

    @Query("SELECT * FROM pokemon")
    fun getPokemonList(): Single<List<Pokemon>>

    @Query("DELETE FROM pokemon WHERE name = :name")
    fun deletePokemon(name: String)

}