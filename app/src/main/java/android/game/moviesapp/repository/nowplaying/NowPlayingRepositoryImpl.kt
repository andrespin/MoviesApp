package android.game.moviesapp.repository.nowplaying

import android.game.moviesapp.model.nowplayingmovies.NowPlayingMovieDTO
import android.game.moviesapp.repository.RemoteDataSource
import retrofit2.Callback

class NowPlayingRepositoryImpl(
    private val remoteDataSource: RemoteDataSource
) : NowPlayingRepository  {
    override fun getNowPlayingMoviesFromServer(
        api_key: String,
        language: String,
        page: Int,
        callback: Callback<NowPlayingMovieDTO>
    ) {
        remoteDataSource.getNowPlayingMoviesDetails(api_key, language, page, callback)
    }
}