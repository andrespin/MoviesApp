package android.game.moviesapp.repository.settings

interface SettingsRepository {

    fun putAdultSettings(bool: Boolean)

    fun getAdultSettings(): Boolean

}