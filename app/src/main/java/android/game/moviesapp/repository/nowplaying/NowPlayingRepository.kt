package android.game.moviesapp.repository.nowplaying

import android.game.moviesapp.model.nowplayingmovies.NowPlayingMovieDTO

interface NowPlayingRepository {

    fun getNowPlayingMoviesFromServer(
        api_key: String,
        language: String,
        page: Int,
        callback: retrofit2.Callback<NowPlayingMovieDTO>
    )

}