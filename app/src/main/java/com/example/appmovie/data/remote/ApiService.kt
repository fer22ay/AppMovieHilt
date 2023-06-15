package com.example.appmovie.data.remote

import com.example.appmovie.data.model.MovieResponse
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("/API/MostPopularMovies/k_9v5jw2c1")
    suspend fun getMostPopularMovies(): Response<MovieResponse>

}