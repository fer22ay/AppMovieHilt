package com.example.appmovie.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Movie(
    @PrimaryKey
    val id: String,
    val title: String,
    val year: String,
    val image: String,
    val imDbRating: String
)