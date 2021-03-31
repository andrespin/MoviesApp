package android.game.moviesapp.model.upcomingmovies


class RepositoryUpcomingImpl: RepositoryUpcoming {

    override fun getUpcomingMoviesCardDBFromServer(): UpcomingMovieCardDb {
        return UpcomingMovieCardDb()
    }

    override fun getUpcomingMoviesCardDBFromLocalStorage(): UpcomingMovieCardDb {
        return UpcomingMovieCardDb()
    }

}