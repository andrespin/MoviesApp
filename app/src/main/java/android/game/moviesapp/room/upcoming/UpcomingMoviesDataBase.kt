package android.game.moviesapp.room.upcoming

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = arrayOf(UpcomingMoviesEntity::class), version = 1, exportSchema = false)
abstract class UpcomingMoviesDataBase : RoomDatabase() {
    abstract fun upcomingMoviesDao(): UpcomingMoviesDao
}
