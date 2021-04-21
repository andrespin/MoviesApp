package android.game.moviesapp.room.settings

import androidx.room.*

@Dao
interface SettingsDao {

    @Query("SELECT * FROM SettingsEntity")
    fun getAll(): List<SettingsEntity>

    @Query("SELECT * FROM SettingsEntity WHERE id LIKE :id")
    fun getDataByMovieName(id: Long): List<SettingsEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(entity: SettingsEntity)

    @Update
    fun update(entity: SettingsEntity)

    @Delete
    fun delete(entity: SettingsEntity)

}