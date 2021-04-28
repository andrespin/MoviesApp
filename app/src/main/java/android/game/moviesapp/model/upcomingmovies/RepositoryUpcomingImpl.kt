package android.game.moviesapp.model.upcomingmovies


class RepositoryUpcomingImpl : RepositoryUpcoming {

    override fun getUpcomingMoviesCardDBFromServer() = UpcomingMovieCardFromServerData()

    override fun getUpcomingMoviesCardDBFromLocalStorage() = UpcomingMovieCardDb()

}