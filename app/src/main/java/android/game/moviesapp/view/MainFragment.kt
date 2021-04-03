package android.game.moviesapp.view

import android.game.moviesapp.R
import android.game.moviesapp.view.adapters.NowPlayingMoviesAdapterCallback
import android.game.moviesapp.databinding.MainFragmentBinding
import android.game.moviesapp.model.nowplayingmovies.NowPlayingMovieCardDB
import android.game.moviesapp.model.upcomingmovies.UpcomingMovieCardDb
import android.game.moviesapp.viewmodel.MainViewModel
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar


class MainFragment : Fragment(), NowPlayingMoviesAdapterCallback {


    private var _binding: MainFragmentBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: MainViewModel
    private val nowPlayingMovieCardDB: NowPlayingMovieCardDB = NowPlayingMovieCardDB()
    private val upcomingMovieCardDb: UpcomingMovieCardDb = UpcomingMovieCardDb()

    companion object {
        fun newInstance() = MainFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        putNowPlayingMoviesFragment()
        putUpcomingFragment()
        _binding = MainFragmentBinding.inflate(inflater, container, false)
        return binding.getRoot()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        viewModel.getLiveData().observe(viewLifecycleOwner, Observer { })
    }

    private fun putNowPlayingMoviesFragment() {
        Log.d("putNowPlayingMoviesFragment", "Start")
        val nowPlayingMoviesFragment =
            NowPlayingMoviesFragment.newInstance(nowPlayingMovieCardDB)
        requireActivity().supportFragmentManager
            .beginTransaction()
            .replace(R.id.nowPlayingMoviesContainer, nowPlayingMoviesFragment)
            .commit()
        Log.d("putNowPlayingMoviesFragment", "Finish")
    }

    private fun putUpcomingFragment() {
        Log.d("putUpcomingFragment", "Start")
        val upcomingFragment = UpcomingFragment.newInstance(upcomingMovieCardDb)
        requireActivity().supportFragmentManager
            .beginTransaction()
            .replace(R.id.upcomingMoviesContainer, upcomingFragment)
            .commit()
        Log.d("putNowPlayingMoviesFragment", "Finish")
    }

    override fun onOnItemClicked(position: Int) {
        Log.d("Position", position.toString())
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun View.createAndShow(
        text: String, actionText: String, action: (View) -> Unit,
        length: Int = Snackbar.LENGTH_INDEFINITE
    ) {
        Snackbar.make(this, text, length).setAction(actionText, action).show()
    }

}