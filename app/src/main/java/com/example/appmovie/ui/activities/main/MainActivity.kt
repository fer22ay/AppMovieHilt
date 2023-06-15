package com.example.appmovie.ui.activities.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import com.example.appmovie.core.Result
import com.example.appmovie.core.Utils
import com.example.appmovie.data.local.entity.Movie
import com.example.appmovie.databinding.ActivityMainBinding
import com.example.appmovie.ui.adapter.ClickInterface
import com.example.appmovie.ui.adapter.MovieAdapter
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()

    @Inject
    lateinit var movieAdapter: MovieAdapter

    @Inject
    lateinit var utils: Utils

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvMovies.adapter = movieAdapter

        movieAdapter.setItemClick(object : ClickInterface<Movie> {
            override fun onClick(data: Movie) {
                Toast.makeText(this@MainActivity, data.title, Toast.LENGTH_SHORT).show()
            }
        })

        subscribeToObservers()

    }

    private fun subscribeToObservers() {
        viewModel.listMovies()

        viewModel.moviesResponse.observe(this) {
            when (it) {
                is Result.Success -> {
                    it.data.let { movie ->
                        hideProgressBar()
                        movieAdapter.updateMovies(movie.items)
                    }
                }
                is Result.Error -> it.message.let { message ->
                    utils.showInfoDialog(
                        this@MainActivity,
                        message
                    )
                }
                is Result.Loading -> showProgressBar()
                is Result.Nothing -> {}
            }
        }

    }

    private fun hideProgressBar() {
        binding.progressbar.visibility = View.GONE;
    }

    private fun showProgressBar() {
        binding.progressbar.visibility = View.VISIBLE;
    }

}