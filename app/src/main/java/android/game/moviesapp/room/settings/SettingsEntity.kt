package android.game.moviesapp.room.settings

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class SettingsEntity (
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,

    val isAdult: Long = 0 // True - 1, false - 0
)

