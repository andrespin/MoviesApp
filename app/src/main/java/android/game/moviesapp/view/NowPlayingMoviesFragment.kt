package android.game.moviesapp.view

import android.game.moviesapp.databinding.FragmentNowPlayingMoviesBinding
import android.game.moviesapp.model.nowplayingmovies.NowPlayingMovieCard
import android.game.moviesapp.model.nowplayingmovies.NowPlayingMovieCardDB
import android.game.moviesapp.view.adapters.NowPlayingMoviesAdapter
import android.game.moviesapp.viewmodel.AppLoadingStatePlayingNowMovies
import android.game.moviesapp.viewmodel.MainViewModel
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider


class NowPlayingMoviesFragment : Fragment() {

    private val movieCardList = "MovieCardList"

    private var _binding: FragmentNowPlayingMoviesBinding? = null
    private val binding get() = _binding!!
    private val viewModel by lazy {
                ViewModelProvider(this).get(MainViewModel::class.java)
    }

    private var isDataSetRus: Boolean = true

    private var nowPlayingMovieCardDB: NowPlayingMovieCardDB? = null

    private val adapter = NowPlayingMoviesAdapter(object : OnItemViewClickListener {
        override fun onItemViewClick(nowPlayingMovieCard: NowPlayingMovieCard) {
            (activity as MainActivity?)!!.putDetailsFragment(nowPlayingMovieCard)
        }
    })

    interface OnItemViewClickListener {
        fun onItemViewClick(nowPlayingMovieCard: NowPlayingMovieCard)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            nowPlayingMovieCardDB = it.getSerializable(movieCardList) as NowPlayingMovieCardDB
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentNowPlayingMoviesBinding.inflate(inflater, container, false)
        return binding.getRoot()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerNowPlaying.adapter = adapter
        viewModel.getLiveData().observe(viewLifecycleOwner, Observer { putData(it) })
        viewModel.getNowPlayingMovieCardDBFromLocalSource()
    }

    private fun putData(appLoadingStatePlayingNowMovies: AppLoadingStatePlayingNowMovies) {
        when (appLoadingStatePlayingNowMovies) {
            is AppLoadingStatePlayingNowMovies.Success -> {
                adapter.setData(appLoadingStatePlayingNowMovies.nowPlayingMovieCardDB.list)
                Log.d("Status", "Success")
            }
            is AppLoadingStatePlayingNowMovies.Loading -> {
                Log.d("Status", "Loading")
            }
            is AppLoadingStatePlayingNowMovies.Error -> {
                Log.d("Status", "Error")
            }
        }
    }


    override fun onDestroy() {
        adapter.removeListener()
        super.onDestroy()
    }


    companion object {
        @JvmStatic
        fun newInstance(nowPlayingMovieCardDB: NowPlayingMovieCardDB) =
            NowPlayingMoviesFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(movieCardList, nowPlayingMovieCardDB)
                }
            }
    }

}