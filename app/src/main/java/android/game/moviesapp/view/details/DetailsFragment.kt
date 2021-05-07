package android.game.moviesapp.view.details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.game.moviesapp.R
import android.game.moviesapp.databinding.FragmentDetailsBinding
import android.game.moviesapp.model.nowplayingmovies.NowPlayingMovieCard
import android.game.moviesapp.model.nowplayingmovies.NowPlayingMovieCardDB
import android.game.moviesapp.model.upcomingmovies.UpcomingMovieCard
import android.game.moviesapp.view.MainActivity

class DetailsFragment : Fragment() {

    private val upcomingMovieCardKey = "upcomingMovieCardKey"
    private var isUpcomingMovieCardKeyGot = false

    private val playingNowMovieCardKey = "playingNowMovieCardKey"
    private var isPlayingNowMovieCardKeyGot = false

    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!

    private var upcomingMovieCard: UpcomingMovieCard? = null
    private var nowPlayingMovieCard: NowPlayingMovieCard? = null

        // штрафы гибдд
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.getRoot()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.textMovieNameRus.text = getMovieName()
        binding.imageView.setOnClickListener {
            (activity as MainActivity?)!!.putMainFragment()
        }
    }

    private fun getMovieName(): String {
        var movieName = ""
        if (isUpcomingMovieCardKeyGot) {
            arguments?.let {
                upcomingMovieCard = it.getSerializable(upcomingMovieCardKey) as UpcomingMovieCard
                movieName = upcomingMovieCard?.name ?: "Нет данных об имени"
            }
        } else if (isPlayingNowMovieCardKeyGot) {
            arguments?.let {
                nowPlayingMovieCard =
                    it.getSerializable(playingNowMovieCardKey) as NowPlayingMovieCard
                movieName = nowPlayingMovieCard?.textMovieName ?: "Нет данных об имени"
            }
        }
        return movieName
    }


    companion object {

        fun newInstance() = DetailsFragment()

        fun newInstance(upcomingMovieCard: UpcomingMovieCard) = DetailsFragment().apply {
            arguments = Bundle().apply {
                putSerializable(upcomingMovieCardKey, upcomingMovieCard)
                isUpcomingMovieCardKeyGot = true
            }
        }

        fun newInstance(nowPlayingMovieCard: NowPlayingMovieCard) = DetailsFragment().apply {
            arguments = Bundle().apply {
                putSerializable(playingNowMovieCardKey, nowPlayingMovieCard)
                isPlayingNowMovieCardKeyGot = true
            }
        }

    }
}