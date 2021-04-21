package android.game.moviesapp.room.nowplaying

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class NowPlayingMoviesEntity (
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val name: String = "",
    val date: String = "",
    val adult: Long = 0, // Boolean
    val backdrop_path: String = "",
    val id_movie: Int = 0,
    val original_language: String = "",
    val original_title: String = "",
    val overview: String = "",
    val popularity: Double = 0.0,
    val poster_path: String = "",
    val video: Long = 0, // Boolean
    val vote_average: Double = 0.0,
    val vote_count: Int = 0
    )