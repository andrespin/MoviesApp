package android.game.moviesapp.view.adapters

import android.game.moviesapp.R
import android.game.moviesapp.model.nowplayingmovies.NowPlayingMovieCard
import android.game.moviesapp.view.NowPlayingMoviesFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class NowPlayingMoviesAdapter(
    private var onItemViewClickListener: NowPlayingMoviesFragment.OnItemViewClickListener?
) : RecyclerView.Adapter<NowPlayingMoviesAdapter.NowPlayingMoviesViewHolder>() {

    private var nowPlayingMovieCardList: List<NowPlayingMovieCard> = listOf()

    fun setData(data: List<NowPlayingMovieCard>) {
        nowPlayingMovieCardList = data
        notifyDataSetChanged()
    }

    fun removeListener() {
        onItemViewClickListener = null
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): NowPlayingMoviesViewHolder {
        return NowPlayingMoviesViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_now_playing, parent, false) as View
        )
    }

    override fun onBindViewHolder(holder: NowPlayingMoviesViewHolder, position: Int) {
        holder.onBind(nowPlayingMovieCardList.get(position))
    }

    override fun getItemCount(): Int {
        return nowPlayingMovieCardList.size
    }

    companion object {
        private const val TAG = "NowPlayingMoviesAdapter"
    }

    inner class NowPlayingMoviesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun onBind(nowPlayingMovieCard: NowPlayingMovieCard) {
            itemView.findViewById<TextView>(R.id.textYear).text =
                nowPlayingMovieCard.textYear
            itemView.findViewById<TextView>(R.id.textMovieName).text =
                nowPlayingMovieCard.textMovieName
            itemView.findViewById<TextView>(R.id.textMoviewMark).text =
                nowPlayingMovieCard.textMovieMark
            itemView.setOnClickListener {
                onItemViewClickListener?.onItemViewClick(nowPlayingMovieCard)
            }
        }
    }
}