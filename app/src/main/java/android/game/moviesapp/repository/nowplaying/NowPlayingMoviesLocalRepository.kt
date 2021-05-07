package android.game.moviesapp.repository.nowplaying

import android.game.moviesapp.model.nowplayingmovies.NowPlayingMovieCard
import android.game.moviesapp.model.upcomingmovies.UpcomingMovieCard

interface NowPlayingMoviesLocalRepository {

    fun getAll(): List<NowPlayingMovieCard>
    fun saveEntity(list: List<NowPlayingMovieCard>)

}