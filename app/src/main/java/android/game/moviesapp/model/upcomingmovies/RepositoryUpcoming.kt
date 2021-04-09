package android.game.moviesapp.model.upcomingmovies


interface RepositoryUpcoming {
    fun getUpcomingMoviesCardDBFromServer(): UpcomingMovieCardDb
    fun getUpcomingMoviesCardDBFromLocalStorage(): UpcomingMovieCardDb
}