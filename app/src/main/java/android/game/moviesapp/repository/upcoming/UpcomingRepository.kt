package android.game.moviesapp.repository.upcoming

import android.game.moviesapp.model.upcomingmovies.UpcomingMovieDTO

interface UpcomingRepository {

    fun getUpcomingMoviesDetailsFromServer(
        api_key: String,
        language: String,
        page: Int,
        callback: retrofit2.Callback<UpcomingMovieDTO>
    )

}

