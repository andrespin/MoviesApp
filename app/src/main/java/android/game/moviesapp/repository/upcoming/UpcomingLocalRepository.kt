package android.game.moviesapp.repository.upcoming

import android.game.moviesapp.model.upcomingmovies.UpcomingMovieCard

interface UpcomingLocalRepository {

    fun getAll(): List<UpcomingMovieCard>
    fun saveEntity(list: List<UpcomingMovieCard>)

}