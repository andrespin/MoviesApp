package android.game.moviesapp.viewmodel

import android.game.moviesapp.app.App.Companion.getUpcomingMoviesDao
import android.game.moviesapp.app.AppLoadingStatePlayingNowMovies
import android.game.moviesapp.app.AppLoadingStateUpcomingMovies
import android.game.moviesapp.model.nowplayingmovies.NowPlayingMovieDTO
import android.game.moviesapp.model.upcomingmovies.*
import android.game.moviesapp.repository.RemoteDataSource
import android.game.moviesapp.repository.nowplaying.NowPlayingRepository
import android.game.moviesapp.repository.nowplaying.NowPlayingRepositoryImpl
import android.game.moviesapp.repository.settings.SettingsRepository
import android.game.moviesapp.repository.settings.SettingsRepositoryImpl
import android.game.moviesapp.repository.upcoming.UpcomingLocalRepository
import android.game.moviesapp.repository.upcoming.UpcomingLocalRepositoryImpl
import android.game.moviesapp.repository.upcoming.UpcomingRepository
import android.game.moviesapp.repository.upcoming.UpcomingRepositoryImpl
import android.game.moviesapp.utils.checkResponse
import android.game.moviesapp.utils.convertToUpcomingMovieCardFromServerData
import android.game.moviesapp.view.MainActivity
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
    private val upcomingRepositoryImpl: UpcomingRepository = UpcomingRepositoryImpl(
        RemoteDataSource()
    ),
    private val upcomingLocalRepositoryImpl: UpcomingLocalRepository = UpcomingLocalRepositoryImpl(
        getUpcomingMoviesDao()
    ),
    private val settingsRepositoryImpl: SettingsRepository = SettingsRepositoryImpl(
        MainActivity().sharedPreferences!!
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
        upcomingRepositoryImpl.getUpcomingMoviesDetailsFromServer(
            api_key,
            language,
            page,
            upcomingCallBack
        )
    }

    fun saveUpcomingMoviesToDb(list: List<UpcomingMovieCard>){
        upcomingLocalRepositoryImpl.saveEntity(list)
    }

    fun returnUpcomingMoviesFromDb() : List<UpcomingMovieCard>{
        return upcomingLocalRepositoryImpl.getAll()
    }

    fun getUpcomingMoviesFromDb(){
        detailsLiveData.value = AppLoadingStateUpcomingMovies.Loading
        detailsLiveData.value = AppLoadingStateUpcomingMovies.ServerSuccess(
            convertToUpcomingMovieCardFromServerData(upcomingLocalRepositoryImpl.getAll()) )
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
                    checkResponse(serverResponse, CORRUPTED_DATA)
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
                    checkResponse(serverResponse, CORRUPTED_DATA)
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

}