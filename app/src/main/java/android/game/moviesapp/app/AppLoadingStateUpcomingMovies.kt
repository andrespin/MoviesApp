package android.game.moviesapp.app

import android.game.moviesapp.model.upcomingmovies.UpcomingMovieCardDb
import android.game.moviesapp.model.upcomingmovies.UpcomingMovieCardFromServerData

sealed class AppLoadingStateUpcomingMovies {

    data class Success(val upcomingMovieCardDB: UpcomingMovieCardDb) :
        AppLoadingStateUpcomingMovies()

    data class Repository(val upcomingMovieCardFromServerData: UpcomingMovieCardFromServerData) :
        AppLoadingStateUpcomingMovies()

    data class ServerSuccess(val upcomingMovieCardFromServerData: UpcomingMovieCardFromServerData) :
        AppLoadingStateUpcomingMovies()

    data class Error(val error: Throwable) : AppLoadingStateUpcomingMovies()

    object Loading : AppLoadingStateUpcomingMovies()
}