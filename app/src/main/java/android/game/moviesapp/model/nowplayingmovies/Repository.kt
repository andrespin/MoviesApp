package android.game.moviesapp.model.nowplayingmovies

interface Repository {
    fun getNowPlayingMovieCardDBFromServer(): NowPlayingMovieCardDB
    fun getNowPlayingMovieCardDBFromLocalStorage(): NowPlayingMovieCardDB
}