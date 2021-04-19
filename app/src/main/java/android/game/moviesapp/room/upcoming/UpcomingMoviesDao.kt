package android.game.moviesapp.room.upcoming

import androidx.room.*

@Dao
interface UpcomingMoviesDao {

    @Query("SELECT * FROM UpcomingMoviesEntity")
    fun getAll(): List<UpcomingMoviesEntity>

    @Query("SELECT * FROM UpcomingMoviesEntity WHERE name LIKE :name")
    fun getDataByMovieName(name: String): List<UpcomingMoviesEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(entity: UpcomingMoviesEntity)

    @Update
    fun update(entity: UpcomingMoviesEntity)

    @Delete
    fun delete(entity: UpcomingMoviesEntity)

}
