package android.game.moviesapp.repository.nowplaying

import android.game.moviesapp.model.nowplayingmovies.NowPlayingMovieDTO
import android.game.moviesapp.model.upcomingmovies.UpcomingMovieDTO
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface NowPlayingMoviesAPI {

    // https://api.themoviedb.org/3/movie/upcoming?api_key=df29ce69d803b8fd9c32aee7fb421a48&language=en-US&page=1

    // https://api.themoviedb.org/3/movie/now_playing?api_key=df29ce69d803b8fd9c32aee7fb421a48&language=en-US&page=1

    @GET("3/movie/now_playing")
    fun getNowPlayingMovies(
        @Query("api_key") api_key: String,
        @Query("language") language: String,
        @Query("page") page: Int
    ): Call<NowPlayingMovieDTO>

}