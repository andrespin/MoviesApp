package android.game.moviesapp.view

import android.content.Intent
import android.content.IntentFilter
import android.game.moviesapp.R
import android.game.moviesapp.model.nowplayingmovies.NowPlayingMovieCard
import android.game.moviesapp.model.upcomingmovies.UpcomingMovieCard
import android.game.moviesapp.view.details.DetailsFragment
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {

    private val receiver = MainBroadcastReceiver()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            putMainFragment()
        }
        registerReceiver(receiver, IntentFilter(Intent.ACTION_AIRPLANE_MODE_CHANGED))
    }

    override fun onDestroy() {
        unregisterReceiver(receiver)
        super.onDestroy()
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