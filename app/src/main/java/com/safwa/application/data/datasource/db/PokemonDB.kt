package com.safwa.application.data.datasource.db

import androidx.room.Dao
import androidx.room.Database
import androidx.room.RoomDatabase
import com.safwa.application.data.models.Pokemon


@Database(entities = [Pokemon::class], version = 1, exportSchema = false)
abstract class PokemonDB : RoomDatabase() {

    abstract fun pokemonDao(): PokemonDao


}