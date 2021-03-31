package android.game.moviesapp.viewmodel

import android.game.moviesapp.model.nowplayingmovies.Repository
import android.game.moviesapp.model.nowplayingmovies.RepositoryImpl
import android.game.moviesapp.model.upcomingmovies.RepositoryUpcoming
import android.game.moviesapp.model.upcomingmovies.RepositoryUpcomingImpl
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel(
    private val liveDataToObserve: MutableLiveData<AppLoadingStatePlayingNowMovies> = MutableLiveData(),
    private val upcomingLiveDataToObserve: MutableLiveData<AppLoadingStateUpcomingMovies> = MutableLiveData(),
    private val repositoryImpl: Repository = RepositoryImpl(),
    private val repositoryUpcomingImpl: RepositoryUpcoming = RepositoryUpcomingImpl()
) : ViewModel() {

    fun getLiveData() = liveDataToObserve

    fun getUpcomingLiveData() = upcomingLiveDataToObserve

    fun getNowPlayingMovieCardDBFromLocalSource() = getNowPlayingDataFromLocalSource()

    fun getNowPlayingMovieCardDBFromRemoteSource() = getNowPlayingDataFromLocalSource()

    fun getUpcomingMovieCardDBFromLocalSource() = getUpcomingDataFromLocalSource()

    fun getUpcomingMovieCardDBFromRemoteSource() = getUpcomingDataFromLocalSource()


    private fun getNowPlayingDataFromLocalSource() {
        liveDataToObserve.value = AppLoadingStatePlayingNowMovies.Loading
        Thread {
            Thread.sleep(1000)
            liveDataToObserve.postValue(AppLoadingStatePlayingNowMovies.Success(repositoryImpl.getNowPlayingMovieCardDBFromLocalStorage()))
        }.start()
    }

    private fun getUpcomingDataFromLocalSource(){
        upcomingLiveDataToObserve.value = AppLoadingStateUpcomingMovies.Loading
        Thread{
            Thread.sleep(1000)
            upcomingLiveDataToObserve.postValue(AppLoadingStateUpcomingMovies.Success(
                repositoryUpcomingImpl.getUpcomingMoviesCardDBFromLocalStorage())
            )
        }.start()
    }

}