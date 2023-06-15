package com.example.appmovie.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.appmovie.data.local.entity.Movie
import com.example.appmovie.databinding.AdapterMovieBinding
import javax.inject.Inject

class MovieAdapter @Inject constructor() : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    var movies = mutableListOf<Movie>()
    private var clickInterface: ClickInterface<Movie>? = null


    fun updateMovies(movies: List<Movie>) {
        this.movies = movies.toMutableList()
        notifyItemRangeInserted(0, movies.size)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val binding =
            AdapterMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(binding)
    }

    override fun getItemCount(): Int = movies.size

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = movies[position]
        holder.view.tvTitle.text = movie.title
        holder.view.rvYear.text = "Year : ${movie.year}"
        holder.view.rvRating.text = "Rating : ${movie.imDbRating}"
        Glide
            .with(holder.view.imgMovieImage.context)
            .load(movie.image)
            .centerCrop()
            .into(holder.view.imgMovieImage)
        holder.view.movieCard.setOnClickListener {
            clickInterface?.onClick(movie)
        }
    }

    fun setItemClick(clickInterface: ClickInterface<Movie>) {
        this.clickInterface = clickInterface
    }

    class MovieViewHolder(val view: AdapterMovieBinding) :
        RecyclerView.ViewHolder(view.root)

}

interface ClickInterface<T> {
    fun onClick(data: T)
}