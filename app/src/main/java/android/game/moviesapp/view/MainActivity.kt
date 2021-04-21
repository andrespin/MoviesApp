package android.game.moviesapp.view

import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.SharedPreferences
import android.game.moviesapp.R
import android.game.moviesapp.model.nowplayingmovies.NowPlayingMovieCard
import android.game.moviesapp.model.upcomingmovies.UpcomingMovieCard
import android.game.moviesapp.view.details.DetailsFragment
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private val receiver = MainBroadcastReceiver()
    private val isAdult = "my_boolean"
    private val adultMode = "AdultMode"


    fun getSharedPreferences() =
        applicationContext.getSharedPreferences(adultMode, Context.MODE_PRIVATE)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            putMainFragment()
        }

//        var s = this.getPreferences(MODE_PRIVATE)
//        println("s.getBoolean(\"isAdult\", false) = " + s.getBoolean("isAdult", false))
//        registerReceiver(receiver, IntentFilter(Intent.ACTION_AIRPLANE_MODE_CHANGED))
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu to use in the action bar
        val inflater = menuInflater
        inflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle presses on the action bar menu items
        when (item.itemId) {
            R.id.openSettings -> {
                putSettingsFragment()
                return true
            }

            R.id.contentProvider -> {
                putContentProviderFragment()
                return true
            }

        }
        return super.onOptionsItemSelected(item)
    }

    private fun putContentProviderFragment() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, ContentProviderFragment.newInstance())
            .commitNow()
    }

    override fun onDestroy() {
        unregisterReceiver(receiver)
        super.onDestroy()
    }

    fun putSettingsFragment() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, SettingsFragment.newInstance())
            .commitNow()
    }

    fun putMainFragment() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, MainFragment.newInstance())
            .commitNow()
    }

    fun putDetailsFragment(upcomingMovieCard: UpcomingMovieCard) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, DetailsFragment.newInstance(upcomingMovieCard))
            .commitNow()
    }

    fun putDetailsFragment(nowPlayingMovieCard: NowPlayingMovieCard) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, DetailsFragment.newInstance(nowPlayingMovieCard))
            .commitNow()
    }

    fun callFromFragment() = println("Called from Fragment")

}