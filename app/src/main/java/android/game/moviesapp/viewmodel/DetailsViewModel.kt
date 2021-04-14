package android.game.moviesapp.viewmodel

import android.game.moviesapp.model.nowplayingmovies.NowPlayingMovieCard
import android.game.moviesapp.model.nowplayingmovies.NowPlayingMovieCardFromServerData
import android.game.moviesapp.model.nowplayingmovies.NowPlayingMovieDTO
import android.game.moviesapp.model.upcomingmovies.*
import android.game.moviesapp.repository.RemoteDataSource
import android.game.moviesapp.repository.nowplaying.NowPlayingRepository
import android.game.moviesapp.repository.nowplaying.NowPlayingRepositoryImpl
import android.game.moviesapp.repository.upcoming.UpcomingRepository
import android.game.moviesapp.repository.upcoming.UpcomingRepositoryImpl
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


private const val SERVER_ERROR = "Ошибка сервера"
private const val REQUEST_ERROR = "Ошибка запроса на сервер"
private const val CORRUPTED_DATA = "Неполные данные"

class DetailsViewModel(
    private val detailsLiveData: MutableLiveData<AppLoadingStateUpcomingMovies> = MutableLiveData(),
    private val repositoryImpl: UpcomingRepository = UpcomingRepositoryImpl(
        RemoteDataSource()
    ),
    private val nowPlayingLiveData: MutableLiveData<AppLoadingStatePlayingNowMovies> = MutableLiveData(),
    private val nowPlayingRepositoryImpl: NowPlayingRepository = NowPlayingRepositoryImpl(
        RemoteDataSource()
    )
) : ViewModel() {

    fun getLiveData() = detailsLiveData

    fun getUpcomingMoviesFromRemoteSource(
        api_key: String,
        language: String,
        page: Int
    ) {
        detailsLiveData.value = AppLoadingStateUpcomingMovies.Loading
        repositoryImpl.getUpcomingMoviesDetailsFromServer(
            api_key,
            language,
            page,
            upcomingCallBack
        )
    }

    fun getNowPlayingLiveData() = nowPlayingLiveData

    fun getNowPlayingMoviesFromRemoteSource(
        api_key: String,
        language: String,
        page: Int
    ) {
        nowPlayingLiveData.value = AppLoadingStatePlayingNowMovies.Loading
        nowPlayingRepositoryImpl.getNowPlayingMoviesFromServer(
            api_key,
            language,
            page,
            nowPlayingCallBack
        )
    }

    private val nowPlayingCallBack = object :
        Callback<NowPlayingMovieDTO> {
        override fun onResponse(
            call: Call<NowPlayingMovieDTO>,
            response: Response<NowPlayingMovieDTO>
        ) {
            val serverResponse: NowPlayingMovieDTO? = response.body()
            nowPlayingLiveData.postValue(
                if (response.isSuccessful && serverResponse != null) {
                    checkResponse(serverResponse)
                } else {
                    AppLoadingStatePlayingNowMovies.Error(Throwable(SERVER_ERROR))
                }
            )
        }

        override fun onFailure(call: Call<NowPlayingMovieDTO>, t: Throwable) {
            nowPlayingLiveData.postValue(
                AppLoadingStatePlayingNowMovies.Error(
                    Throwable(
                        t.message ?: REQUEST_ERROR
                    )
                )
            )
        }
    }

    private val upcomingCallBack = object :
        Callback<UpcomingMovieDTO> {
        override fun onResponse(
            call: Call<UpcomingMovieDTO>,
            response: Response<UpcomingMovieDTO>
        ) {
            val serverResponse: UpcomingMovieDTO? = response.body()
            println("response.body() = " + response.body())
            println("serverResponse?.total_pages = " + serverResponse?.total_pages)
            serverResponse?.total_pages
            detailsLiveData.postValue(
                if (response.isSuccessful && serverResponse != null) {
                    checkResponse(serverResponse)
                } else {
                    AppLoadingStateUpcomingMovies.Error(Throwable(SERVER_ERROR))
                }
            )
        }

        override fun onFailure(call: Call<UpcomingMovieDTO>, t: Throwable) {
            detailsLiveData.postValue(
                AppLoadingStateUpcomingMovies.Error(
                    Throwable(
                        t.message ?: REQUEST_ERROR
                    )
                )
            )
        }
    }

    private fun checkResponse(serverResponse: UpcomingMovieDTO): AppLoadingStateUpcomingMovies {
        return if (serverResponse == null || serverResponse.results == null ||
            serverResponse.page == null || serverResponse.total_pages == null
        ) {
            AppLoadingStateUpcomingMovies.Error(Throwable(CORRUPTED_DATA))
        } else {
            val i = convertDtoToModel(serverResponse)
            Log.d("i.list.size ", i.list.size.toString())
            AppLoadingStateUpcomingMovies.ServerSuccess(convertDtoToModel(serverResponse))
        }
    }

    private fun checkResponse(serverResponse: NowPlayingMovieDTO): AppLoadingStatePlayingNowMovies {
        return if (serverResponse == null || serverResponse.results == null ||
            serverResponse.page == null || serverResponse.total_pages == null
        ) {
            AppLoadingStatePlayingNowMovies.Error(Throwable(CORRUPTED_DATA))
        } else {
            val i = convertDtoToModel(serverResponse)
            Log.d("i.list.size ", i.list.size.toString())
            AppLoadingStatePlayingNowMovies.ServerSuccess(convertDtoToModel(serverResponse))
        }
    }

    private fun convertDtoToModel(serverResponse: NowPlayingMovieDTO): NowPlayingMovieCardFromServerData {
        var nowPlayingMovieCardFromServerData =
            NowPlayingMovieCardFromServerData()
        for (i in 0 until serverResponse.results.size) {
            nowPlayingMovieCardFromServerData.list.add(
                NowPlayingMovieCard(
                    serverResponse.results[i].release_date,
                    serverResponse.results[i].title,
                    serverResponse.results[i].vote_average,
                    serverResponse.results[i].overview,
                    serverResponse.results[i].adult,
                    serverResponse.results[i].backdrop_path,
                    serverResponse.results[i].genre_ids,
                    serverResponse.results[i].id,
                    serverResponse.results[i].original_language,
                    serverResponse.results[i].original_title,
                    serverResponse.results[i].popularity,
                    serverResponse.results[i].poster_path,
                    serverResponse.results[i].video,
                    serverResponse.results[i].vote_count
                )
            )
        }
        return nowPlayingMovieCardFromServerData
    }

    private fun convertDtoToModel(serverResponse: UpcomingMovieDTO): UpcomingMovieCardFromServerData {
        var upcomingMovieCardFromServerData =
            UpcomingMovieCardFromServerData()
        for (i in 0 until serverResponse.results.size) {
            upcomingMovieCardFromServerData.list.add(
                UpcomingMovieCard(
                    serverResponse.results[i].title,
                    serverResponse.results[i].release_date,
                    serverResponse.results[i].adult,
                    serverResponse.results[i].backdrop_path,
                    serverResponse.results[i].genre_ids,
                    serverResponse.results[i].id,
                    serverResponse.results[i].original_language,
                    serverResponse.results[i].original_title,
                    serverResponse.results[i].overview,
                    serverResponse.results[i].popularity,
                    serverResponse.results[i].poster_path,
                    serverResponse.results[i].video,
                    serverResponse.results[i].vote_average,
                    serverResponse.results[i].vote_count
                )
            )
        }
        return upcomingMovieCardFromServerData
    }
}