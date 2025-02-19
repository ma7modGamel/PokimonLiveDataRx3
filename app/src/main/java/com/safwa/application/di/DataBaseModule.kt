package com.safwa.application.di

import androidx.room.Room
import com.safwa.application.MyApplication
import com.safwa.application.data.datasource.db.PokemonDB
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataBaseModule {

    @Provides
    @Singleton
    fun provideDatabase(): PokemonDB {
        return Room.databaseBuilder(
            MyApplication.context,
            PokemonDB::class.java,
            "pokemon_db"
        ).fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideDao(db: PokemonDB) = db.pokemonDao()
}