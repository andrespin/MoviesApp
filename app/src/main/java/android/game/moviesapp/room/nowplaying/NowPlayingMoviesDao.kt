package android.game.moviesapp.room.nowplaying

import android.game.moviesapp.room.upcoming.UpcomingMoviesEntity
import androidx.room.*

@Dao
interface NowPlayingMoviesDao {

    @Query("SELECT * FROM NowPlayingMoviesEntity")
    fun getAll(): List<UpcomingMoviesEntity>

    @Query("SELECT * FROM NowPlayingMoviesEntity WHERE name LIKE :name")
    fun getDataByMovieName(name: String): List<UpcomingMoviesEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(entity: NowPlayingMoviesEntity)

    @Update
    fun update(entity: NowPlayingMoviesEntity)

    @Delete
    fun delete(entity: NowPlayingMoviesEntity)

}