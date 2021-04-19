package android.game.moviesapp.app

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.game.moviesapp.room.upcoming.UpcomingMoviesDao
import android.game.moviesapp.room.upcoming.UpcomingMoviesDataBase
import android.util.Log
import androidx.room.Room

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        appInstance = this
        Log.d("App class status", "onCreate started")
    }






    companion object {
        private var appInstance: App? = null
        private var db: UpcomingMoviesDataBase? = null
        private const val DB_NAME = "Upcoming.db"

        fun getUpcomingMoviesDao(): UpcomingMoviesDao {
            if (db == null) {
                synchronized(UpcomingMoviesDataBase::class.java) {
                    if (db == null) {
                        if (appInstance == null) throw IllegalStateException("Application is null while creating DataBase")
                        db = Room.databaseBuilder(
                            appInstance!!.applicationContext,
                            UpcomingMoviesDataBase::class.java,
                            DB_NAME
                        )
                            .allowMainThreadQueries()
                            .build()
                    }
                }

            }
            return db!!.upcomingMoviesDao()
        }


    }


    /*
        fun putSettings(bol: Boolean) {
            var editor = sharedPreference.edit()
            editor.putBoolean(isAdult, bol)
            editor.apply()
        }

        fun getSettings(): Boolean {
            return sharedPreference.getBoolean(isAdult, false)
        }
     */

}




