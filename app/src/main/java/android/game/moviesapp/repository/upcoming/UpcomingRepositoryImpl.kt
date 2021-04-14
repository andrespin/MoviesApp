package android.game.moviesapp.repository.upcoming

import android.game.moviesapp.model.upcomingmovies.UpcomingMovieDTO
import android.game.moviesapp.repository.RemoteDataSource
import retrofit2.Callback

class UpcomingRepositoryImpl(private val remoteDataSource: RemoteDataSource) :
    UpcomingRepository {
    override fun getUpcomingMoviesDetailsFromServer(
        api_key: String,
        language: String,
        page: Int,
        callback: Callback<UpcomingMovieDTO>
    ) {
        remoteDataSource.getUpcomingMoviesDetails(api_key, language, page, callback)
    }
}
