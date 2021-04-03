package android.game.moviesapp.model.nowplayingmovies

class RepositoryImpl : Repository {

    override fun getNowPlayingMovieCardDBFromServer() = NowPlayingMovieCardDB()

    override fun getNowPlayingMovieCardDBFromLocalStorage() = NowPlayingMovieCardDB()

}