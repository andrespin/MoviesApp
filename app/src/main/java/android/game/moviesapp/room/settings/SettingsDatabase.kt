package android.game.moviesapp.room.settings

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = arrayOf(SettingsEntity::class), version = 1, exportSchema = false)
abstract class SettingsDatabase : RoomDatabase() {
    abstract fun settingsDao(): SettingsDao
}
