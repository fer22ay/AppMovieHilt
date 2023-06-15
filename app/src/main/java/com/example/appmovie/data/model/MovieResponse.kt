package com.example.appmovie.data.model

import com.example.appmovie.data.local.entity.Movie

data class MovieResponse(val items: List<Movie>, val errorMessage: String)
