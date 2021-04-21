package android.game.moviesapp.app

import android.game.moviesapp.model.nowplayingmovies.NowPlayingMovieCardDB
import android.game.moviesapp.model.nowplayingmovies.NowPlayingMovieCardFromServerData

sealed class AppLoadingStatePlayingNowMovies {

    data class Success(val nowPlayingMovieCardDB: NowPlayingMovieCardDB) :
        AppLoadingStatePlayingNowMovies()

    data class ServerSuccess(val nowPlayingMovieCardFromServerData: NowPlayingMovieCardFromServerData) :
        AppLoadingStatePlayingNowMovies()

    data class Error(val error: Throwable) : AppLoadingStatePlayingNowMovies()
    object Loading : AppLoadingStatePlayingNowMovies()

}