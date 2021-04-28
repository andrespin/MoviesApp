package android.game.moviesapp.room.nowplaying

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = arrayOf(NowPlayingMoviesEntity::class), version = 1, exportSchema = false)
abstract class NowPlayingMoviesDatabase : RoomDatabase() {
    abstract fun NowPlayingMoviesDao(): NowPlayingMoviesDao
}
