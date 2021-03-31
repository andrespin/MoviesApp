package android.game.moviesapp.viewmodel

import android.game.moviesapp.model.nowplayingmovies.NowPlayingMovieCardDB

sealed class AppLoadingStatePlayingNowMovies {

    data class Success(val nowPlayingMovieCardDB: NowPlayingMovieCardDB) : AppLoadingStatePlayingNowMovies()
    data class Error(val error: Throwable) : AppLoadingStatePlayingNowMovies()
    object Loading : AppLoadingStatePlayingNowMovies()

}