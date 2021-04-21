package android.game.moviesapp.app

import android.app.Application
import android.game.moviesapp.room.nowplaying.NowPlayingMoviesDao
import android.game.moviesapp.room.nowplaying.NowPlayingMoviesDatabase
import android.game.moviesapp.room.settings.SettingsDao
import android.game.moviesapp.room.settings.SettingsDatabase
import android.game.moviesapp.room.upcoming.UpcomingMoviesDao
import android.game.moviesapp.room.upcoming.UpcomingMoviesDataBase
import android.util.Log
import androidx.room.Room

class App : Application() {

    private val isAdult = "my_boolean"
    private val adultMode = "AdultMode"


    override fun onCreate() {
        super.onCreate()
        appInstance = this
        Log.d("App class status", "onCreate started")
    }

    companion object {
        private var appInstance: App? = null
        private var upcomingDatabase: UpcomingMoviesDataBase? = null
        private const val UpcomingDB_NAME = "Upcoming.db"

        private var nowPlayingDatabase: NowPlayingMoviesDatabase? = null
        private const val nowPlayingDB_name = "NowPlaying.db"

        private var settingsDatabase: SettingsDatabase? = null
        private const val settingsDB_name = "Settings.db"

        fun getNowPlayingDao(): NowPlayingMoviesDao {
            if (nowPlayingDatabase == null) {
                synchronized(UpcomingMoviesDataBase::class.java) {
                    if (nowPlayingDatabase == null) {
                        if (appInstance == null) throw IllegalStateException("Application is null while creating DataBase")
                        nowPlayingDatabase = Room.databaseBuilder(
                            appInstance!!.applicationContext,
                            NowPlayingMoviesDatabase::class.java,
                            nowPlayingDB_name
                        )
                            .allowMainThreadQueries()
                            .build()
                    }
                }
            }
            return nowPlayingDatabase!!.NowPlayingMoviesDao()
        }


        fun getSettingsDao(): SettingsDao {
            if (settingsDatabase == null) {
                synchronized(UpcomingMoviesDataBase::class.java) {
                    if (settingsDatabase == null) {
                        if (appInstance == null) throw IllegalStateException("Application is null while creating DataBase")
                        settingsDatabase = Room.databaseBuilder(
                            appInstance!!.applicationContext,
                            SettingsDatabase::class.java,
                            settingsDB_name
                        )
                            .allowMainThreadQueries()
                            .build()
                    }
                }
            }
            return settingsDatabase!!.settingsDao()
        }

        fun getUpcomingMoviesDao(): UpcomingMoviesDao {
            if (upcomingDatabase == null) {
                synchronized(UpcomingMoviesDataBase::class.java) {
                    if (upcomingDatabase == null) {
                        if (appInstance == null) throw IllegalStateException("Application is null while creating DataBase")
                        upcomingDatabase = Room.databaseBuilder(
                            appInstance!!.applicationContext,
                            UpcomingMoviesDataBase::class.java,
                            UpcomingDB_NAME
                        )
                            .allowMainThreadQueries()
                            .build()
                    }
                }

            }
            return upcomingDatabase!!.upcomingMoviesDao()
        }
    }

}




