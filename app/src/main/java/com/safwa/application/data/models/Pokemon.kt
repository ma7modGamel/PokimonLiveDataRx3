package com.safwa.application.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "pokemon")
data class Pokemon(

    @PrimaryKey(autoGenerate = true)
    val id : Int,

    val name: String,
    val url: String
)
