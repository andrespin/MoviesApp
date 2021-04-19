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
import android.game.moviesapp.app.AppLoadingStateUpcomingMovies
import android.game.moviesapp.viewmodel.DetailsViewModel
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider



val api_key = "df29ce69d803b8fd9c32aee7fb421a48"
val language = "en-US"
val page = 1

class UpcomingFragment() : Fragment() {

    private val upcomingMovieCardList: String = "UpcomingMovieCardList"

    private var _binding: FragmentUpcomingBinding? = null
    private val binding get() = _binding!!


    private val viewModel by lazy {
        ViewModelProvider(this).get(DetailsViewModel::class.java)
    }

    private var upcomingMovieCardDb: UpcomingMovieCardDb? = null

    private val adapter = UpcomingMoviesAdapter(object : OnItemViewClickListener {
        override fun onItemViewClick(upcomingMovieCard: UpcomingMovieCard) {
            Log.d("MoviesLoader().loadMovies() Status", "Start")
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
        viewModel.getUpcomingMoviesFromDb()
        binding.recyclerUpcoming.adapter = adapter
        viewModel.getLiveData().observe(viewLifecycleOwner, Observer { putData(it)})
        viewModel.getUpcomingMoviesFromRemoteSource(api_key, language, page)
    }

    private fun putData(appLoadingStateUpcomingMovies: AppLoadingStateUpcomingMovies) {
        when (appLoadingStateUpcomingMovies) {
            is AppLoadingStateUpcomingMovies.ServerSuccess -> {
                Log.d(
                    "size = ",
                    appLoadingStateUpcomingMovies.upcomingMovieCardFromServerData.list.size.toString()
                )
                adapter.setData(appLoadingStateUpcomingMovies.upcomingMovieCardFromServerData.list)
                viewModel.saveUpcomingMoviesToDb(appLoadingStateUpcomingMovies.upcomingMovieCardFromServerData.list)
                // save list
                Log.d("Status", "Upcoming success")
            }
            is AppLoadingStateUpcomingMovies.Loading -> {
                Log.d("Status", "Upcoming loading")
                adapter.setData(viewModel.returnUpcomingMoviesFromDb())
            }
            is AppLoadingStateUpcomingMovies.Error -> {
                Log.d("Status", "Upcoming error")
                viewModel.getUpcomingMoviesFromDb()
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