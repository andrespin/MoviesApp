package android.game.moviesapp.view

import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import android.game.moviesapp.databinding.FragmentSettingsBinding
import android.game.moviesapp.databinding.FragmentUpcomingBinding


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


// (activity as MainActivity?)!!.putDetailsFragment(upcomingMovieCard)

class SettingsFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private var _binding: FragmentSettingsBinding? = null
    private val binding get() = _binding!!


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       // getSettingsDao().insert()

    }

    private fun initBackButton(){
        binding.imageBack.setOnClickListener(){
            (activity as MainActivity?)!!.putMainFragment()
            println("Works fine")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
      //  return inflater.inflate(R.layout.fragment_settings, container, false)
        _binding = FragmentSettingsBinding.inflate(inflater, container, false)
        return binding.getRoot()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initBackButton()
    }


    /*
            fun newInstance(upcomingMovieCardDb: UpcomingMovieCardDb) =
            UpcomingFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(upcomingMovieCardList, upcomingMovieCardDb)
                }
            }
     */


    companion object {

        fun newInstance() = SettingsFragment()

        fun newInstance(arg: String) =
            SettingsFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }
}