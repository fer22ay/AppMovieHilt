package com.example.appmovie.ui.activities.main

import com.example.appmovie.data.remote.ApiService
import com.example.appmovie.core.Result;
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MainRepository @Inject constructor(private val apiService: ApiService) {

    suspend fun getPopularMovies() = apiService.getMostPopularMovies()

}