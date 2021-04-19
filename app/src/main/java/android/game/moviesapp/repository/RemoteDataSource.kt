package android.game.moviesapp.repository

import android.game.moviesapp.model.nowplayingmovies.NowPlayingMovieDTO
import android.game.moviesapp.model.upcomingmovies.UpcomingMovieDTO
import android.game.moviesapp.repository.nowplaying.NowPlayingMoviesAPI
import android.game.moviesapp.repository.upcoming.UpcomingMoviesAPI
import com.google.gson.GsonBuilder
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import kotlin.jvm.Throws

class RemoteDataSource {

    // https://api.themoviedb.org/3/movie/upcoming?api_key=df29ce69d803b8fd9c32aee7fb421a48&language=en-US&page=1

    private val upcomingMoviesApi = Retrofit.Builder()
        .baseUrl("https://api.themoviedb.org/")
        .addConverterFactory(
            GsonConverterFactory.create(
                GsonBuilder().setLenient().create()
            )
        )
        .client(createOkHttpClient(MoviesApiInterceptor()))
        .build().create(UpcomingMoviesAPI::class.java)

    private val nowPlayingMoviesApi = Retrofit.Builder()
        .baseUrl("https://api.themoviedb.org/")
        .addConverterFactory(
            GsonConverterFactory.create(
                GsonBuilder().setLenient().create()
            )
        )
        .client(createOkHttpClient(MoviesApiInterceptor()))
        .build().create(NowPlayingMoviesAPI::class.java)


    fun getNowPlayingMoviesDetails(api_key: String, language: String, page: Int, callback: Callback<NowPlayingMovieDTO>){
        nowPlayingMoviesApi.getNowPlayingMovies(api_key, language, page).enqueue(callback)
    }

    fun getUpcomingMoviesDetails(api_key: String, language: String, page: Int, callback: Callback<UpcomingMovieDTO>){
        upcomingMoviesApi.getUpcomingMovies(api_key, language, page).enqueue(callback)
    }

    private fun createOkHttpClient(interceptor: Interceptor): OkHttpClient {
        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor(interceptor)
        httpClient.addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
        return httpClient.build()
    }

    inner class MoviesApiInterceptor : Interceptor {
        @Throws(IOException::class)
        override fun intercept(chain: Interceptor.Chain): okhttp3.Response {
            return chain.proceed(chain.request())
        }
    }

}