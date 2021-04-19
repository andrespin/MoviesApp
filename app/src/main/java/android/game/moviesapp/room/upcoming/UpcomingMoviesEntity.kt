package android.game.moviesapp.room.upcoming

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class UpcomingMoviesEntity(
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





/*

val name: String,
val date: String,
val adult: Boolean,
val backdrop_path: String,
val genre_ids: List<Int>,
val id: Int,
val original_language: String,
val original_title: String,
val overview: String,
val popularity: Double,
val poster_path: String,
val video: Boolean,
val vote_average: Double,
val vote_count: Int

*/


/*

const val ID = "id"
const val CITY = "city"
const val TEMPERATURE = "temperature"

@Entity
data class HistoryEntity(

    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val city: String = "",
    val temperature: Int = 0,
    val condition: String = ""
)

 */