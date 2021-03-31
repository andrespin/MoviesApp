package android.game.moviesapp.model.nowplayingmovies

class RepositoryImpl: Repository {
    override fun getNowPlayingMovieCardDBFromServer(): NowPlayingMovieCardDB {
        return NowPlayingMovieCardDB()
    }

    override fun getNowPlayingMovieCardDBFromLocalStorage(): NowPlayingMovieCardDB {
        return NowPlayingMovieCardDB()
    }


}