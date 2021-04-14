package android.game.moviesapp.repository.upcoming


import android.game.moviesapp.model.upcomingmovies.UpcomingMovieDTO
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface UpcomingMoviesAPI {

    @GET("3/movie/upcoming")
    fun getUpcomingMovies(
        @Query("api_key") api_key: String,
        @Query("language") language: String,
        @Query("page") page: Int
    ): Call<UpcomingMovieDTO>

}