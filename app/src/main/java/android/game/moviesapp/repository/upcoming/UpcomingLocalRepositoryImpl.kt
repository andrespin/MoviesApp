package android.game.moviesapp.repository.upcoming

import android.game.moviesapp.model.upcomingmovies.UpcomingMovieCard
import android.game.moviesapp.room.upcoming.UpcomingMoviesDao
import android.game.moviesapp.utils.convertDaoToUpcomingMoviesEntity
import android.game.moviesapp.utils.convertUpcomingMoviesEntityToDao

class UpcomingLocalRepositoryImpl(private val localDataSource: UpcomingMoviesDao) :
    UpcomingLocalRepository {

    override fun getAll(): List<UpcomingMovieCard> {
        return convertUpcomingMoviesEntityToDao(localDataSource.getAll())
    }

    override fun saveEntity(list: List<UpcomingMovieCard>) {
        for (i in 0 until list.size){
            localDataSource.insert(convertDaoToUpcomingMoviesEntity(list).get(i))
        }
    }

}