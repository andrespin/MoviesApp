package android.game.moviesapp.model.nowplayingmovies

import java.io.Serializable

class NowPlayingMovieCardDB() : Serializable {

    val list = listOf(
        NowPlayingMovieCard("2021", "Pirates", "7.7"),
        NowPlayingMovieCard("2020", "New Pirates", "7.8"),
        NowPlayingMovieCard("2020", "Pirates", "5.7"),
        NowPlayingMovieCard("2017", "Pirates", "9.7"),
        NowPlayingMovieCard("2021", "Pirates", "7.7"),
        NowPlayingMovieCard("2020", "New Pirates", "7.8"),
        NowPlayingMovieCard("2020", "Pirates", "5.7"),
        NowPlayingMovieCard("2017", "Pirates", "9.7")
    )

}