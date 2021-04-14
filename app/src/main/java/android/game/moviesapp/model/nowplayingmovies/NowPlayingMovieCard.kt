package android.game.moviesapp.model.nowplayingmovies

import java.io.Serializable

data class NowPlayingMovieCard(val textYear: String?,
                               val textMovieName: String?,
                               val textMovieMark: Double?,
                               val overview: String?,
                               val adult: Boolean?,
                               val backdrop_path: String?,
                               val genre_ids: List<Int>?,
                               val id: Int?,
                               val original_language: String?,
                               val original_title: String?,
                               val popularity: Double?,
                               val poster_path: String?,
                               val video: Boolean?,
                               val vote_count: Int?
                               ) : Serializable


