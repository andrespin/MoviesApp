package android.game.moviesapp.repository.settings

interface SettingsRepository {



    fun getAll()

    fun insert()

    fun update()

    fun putAdultSettings(bool: Boolean)

    fun getAdultSettings(): Boolean

}