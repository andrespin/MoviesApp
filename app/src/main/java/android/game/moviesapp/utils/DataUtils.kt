package android.game.moviesapp.utils

import android.game.moviesapp.app.AppLoadingStatePlayingNowMovies
import android.game.moviesapp.app.AppLoadingStateUpcomingMovies
import android.game.moviesapp.model.nowplayingmovies.NowPlayingMovieCard
import android.game.moviesapp.model.nowplayingmovies.NowPlayingMovieCardFromServerData
import android.game.moviesapp.model.nowplayingmovies.NowPlayingMovieDTO
import android.game.moviesapp.model.upcomingmovies.UpcomingMovieCard
import android.game.moviesapp.model.upcomingmovies.UpcomingMovieCardFromServerData
import android.game.moviesapp.model.upcomingmovies.UpcomingMovieDTO
import android.game.moviesapp.room.upcoming.UpcomingMoviesEntity
import android.util.Log


    fun checkResponse(serverResponse: UpcomingMovieDTO, errorDescription: String): AppLoadingStateUpcomingMovies {
    return if (serverResponse == null || serverResponse.results == null ||
        serverResponse.page == null || serverResponse.total_pages == null
    ) {
        AppLoadingStateUpcomingMovies.Error(Throwable(errorDescription))
    } else {
        val i = convertDtoToModel(serverResponse)
        Log.d("i.list.size ", i.list.size.toString())
        AppLoadingStateUpcomingMovies.ServerSuccess(convertDtoToModel(serverResponse))
    }
}

    fun checkResponse(serverResponse: NowPlayingMovieDTO, errorDescription: String): AppLoadingStatePlayingNowMovies {
    return if (serverResponse == null || serverResponse.results == null ||
        serverResponse.page == null || serverResponse.total_pages == null
    ) {
        AppLoadingStatePlayingNowMovies.Error(Throwable(errorDescription))
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








fun convertUpcomingMoviesEntityToDao(entityList: List<UpcomingMoviesEntity>): List<UpcomingMovieCard> {
    return entityList.map {
        UpcomingMovieCard(
            it.name,
            it.date,
            convertFromLongDigitToBoolean(it.adult),
            it.backdrop_path,
            it.id_movie,
            it.original_language,
            it.original_title,
            it.overview,
            it.popularity,
            it.poster_path,
            convertFromLongDigitToBoolean(it.video),
            it.vote_average,
            it.vote_count
        )
    }
}

fun convertDaoToUpcomingMoviesEntity(list: List<UpcomingMovieCard>): List<UpcomingMoviesEntity> {
    return list.map {
        UpcomingMoviesEntity(
            0,
            it.name,
            it.date,
            convertFromBooleanToDigit(it.adult),
            it.backdrop_path,
            it.id_movie,
            it.original_language,
            it.original_title,
            it.overview,
            it.popularity,
            it.poster_path,
            convertFromBooleanToDigit(it.video),
            it.vote_average,
            it.vote_count
        )
    }
}

fun convertToUpcomingMovieCardFromServerData(list: List<UpcomingMovieCard>): UpcomingMovieCardFromServerData {
    var card = UpcomingMovieCardFromServerData()
    for (i in 0 until list.size) {
        card.list.add(list[i])
    }
    return card
}

private fun convertFromLongDigitToBoolean(digit: Long): Boolean {
    return digit == 1L
}

private fun convertFromBooleanToDigit(bol: Boolean): Long {
    return if (bol) 1L else 0L
}

