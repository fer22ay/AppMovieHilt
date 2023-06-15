package com.example.appmovie.ui.activities.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appmovie.core.NetworkHelper
import com.example.appmovie.core.Result
import com.example.appmovie.core.RetrofitHandleCall
import com.example.appmovie.data.model.MovieResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val app: Application,
    private val repository: MainRepository,
    private val networkHelper: NetworkHelper
) : AndroidViewModel(app) {

    val moviesResponse: MutableLiveData<Result<MovieResponse>> = MutableLiveData()

    private suspend fun safeGetMoviesCall() {
        moviesResponse.postValue(Result.Loading)
        try {
            if (networkHelper.isNetworkConnected) {
                val response = repository.getPopularMovies()
                moviesResponse.postValue(RetrofitHandleCall.handleCallResponse(response))
            }
        } catch (t: Throwable) {
            when (t) {
                is IOException -> moviesResponse.postValue(Result.Error(""))
                else -> moviesResponse.postValue(Result.Error(t.message.toString()))
            }
        }
    }

    fun listMovies() = viewModelScope.launch {
        safeGetMoviesCall()
    }

}