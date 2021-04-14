package android.game.moviesapp.model.upcomingmovies


interface RepositoryUpcoming {
    fun getUpcomingMoviesCardDBFromServer(): UpcomingMovieCardFromServerData

    fun getUpcomingMoviesCardDBFromLocalStorage(): UpcomingMovieCardDb
}