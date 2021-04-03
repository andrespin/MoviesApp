package android.game.moviesapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.game.moviesapp.databinding.FragmentUpcomingBinding
import android.game.moviesapp.model.upcomingmovies.UpcomingMovieCard
import android.game.moviesapp.model.upcomingmovies.UpcomingMovieCardDb
import android.game.moviesapp.view.adapters.UpcomingMoviesAdapter
import android.game.moviesapp.viewmodel.AppLoadingStateUpcomingMovies
import android.game.moviesapp.viewmodel.MainViewModel
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider

class UpcomingFragment() : Fragment() {

    private val upcomingMovieCardList: String = "UpcomingMovieCardList"

    private var _binding: FragmentUpcomingBinding? = null
    private val binding get() = _binding!!
    private val viewModel by lazy {
        ViewModelProvider(this).get(MainViewModel::class.java)
    }
    private var upcomingMovieCardDb: UpcomingMovieCardDb? = null

    private val adapter = UpcomingMoviesAdapter(object : OnItemViewClickListener {
        override fun onItemViewClick(upcomingMovieCard: UpcomingMovieCard) {
            (activity as MainActivity?)!!.putDetailsFragment(upcomingMovieCard)
        }
    })

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (activity as MainActivity?)!!.callFromFragment()
        arguments?.let {
            upcomingMovieCardDb = it.getSerializable(upcomingMovieCardList) as UpcomingMovieCardDb
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentUpcomingBinding.inflate(inflater, container, false)
        return binding.getRoot()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerUpcoming.adapter = adapter
        viewModel.getUpcomingLiveData().observe(viewLifecycleOwner, Observer { putData(it) })
        viewModel.getUpcomingMovieCardDBFromLocalSource()
    }

    private fun putData(appLoadingStateUpcomingMovies: AppLoadingStateUpcomingMovies) {
        when (appLoadingStateUpcomingMovies) {
            is AppLoadingStateUpcomingMovies.Success -> {
                adapter.setData(appLoadingStateUpcomingMovies.upcomingMovieCardDB.list)
                Log.d("Status", "Success")
            }
            is AppLoadingStateUpcomingMovies.Loading -> {
                Log.d("Status", "Loading")
            }
            is AppLoadingStateUpcomingMovies.Error -> {
                Log.d("Status", "Error")
            }
        }
    }


    override fun onDestroy() {
        adapter.removeListener()
        super.onDestroy()
    }

    companion object {
        fun newInstance() = UpcomingFragment()
        fun newInstance(upcomingMovieCardDb: UpcomingMovieCardDb) =
            UpcomingFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(upcomingMovieCardList, upcomingMovieCardDb)
                }
            }
    }


    interface OnItemViewClickListener {
        fun onItemViewClick(upcomingMovieCard: UpcomingMovieCard)
    }

}