package android.game.moviesapp.repository.settings

import android.content.SharedPreferences


class SettingsRepositoryImpl(
    private val shared: SharedPreferences
) : SettingsRepository {
    override fun getAll() {
        TODO("Not yet implemented")
    }

    override fun insert() {
        TODO("Not yet implemented")
    }

    override fun update() {
        TODO("Not yet implemented")
    }

    override fun putAdultSettings(bool: Boolean) {
        val editor = shared.edit()
        editor.putBoolean("isAdult", bool)
        editor.apply()
    }

    override fun getAdultSettings(): Boolean {
        return shared.getBoolean("isAdult", false)
    }

}