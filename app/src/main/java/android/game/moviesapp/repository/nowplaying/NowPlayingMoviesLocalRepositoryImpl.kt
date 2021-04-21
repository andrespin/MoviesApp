package android.game.moviesapp.repository.nowplaying

import android.game.moviesapp.model.nowplayingmovies.NowPlayingMovieCard
import android.game.moviesapp.room.nowplaying.NowPlayingMoviesDao


class NowPlayingMoviesLocalRepositoryImpl(
    private val localDataSource: NowPlayingMoviesDao
): NowPlayingMoviesLocalRepository {
    override fun getAll(): List<NowPlayingMovieCard> {
        TODO("Not yet implemented")
    }

    override fun saveEntity(list: List<NowPlayingMovieCard>) {
        TODO("Not yet implemented")
    }
}

/*

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

 */