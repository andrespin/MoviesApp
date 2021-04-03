package android.game.moviesapp.model.upcomingmovies


class RepositoryUpcomingImpl : RepositoryUpcoming {

    override fun getUpcomingMoviesCardDBFromServer() = UpcomingMovieCardDb()

    override fun getUpcomingMoviesCardDBFromLocalStorage() = UpcomingMovieCardDb()

}