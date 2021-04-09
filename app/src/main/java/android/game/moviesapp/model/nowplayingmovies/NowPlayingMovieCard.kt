package android.game.moviesapp.model.nowplayingmovies

import java.io.Serializable

data class NowPlayingMovieCard(val textYear: String, val textMovieName: String,
                               val textMovieMark: String, val overview: String?) : Serializable