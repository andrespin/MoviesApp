package android.game.moviesapp.viewmodel

import android.game.moviesapp.model.upcomingmovies.UpcomingMovieCardDb

sealed class AppLoadingStateUpcomingMovies {

    data class Success(val upcomingMovieCardDB: UpcomingMovieCardDb) : AppLoadingStateUpcomingMovies()
    data class Error(val error: Throwable) : AppLoadingStateUpcomingMovies()
    object Loading : AppLoadingStateUpcomingMovies()

}