package android.game.moviesapp.view.adapters

import android.game.moviesapp.R
import android.game.moviesapp.model.upcomingmovies.UpcomingMovieCard
import android.game.moviesapp.view.UpcomingFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class UpcomingMoviesAdapter(
    private var onItemViewClickListener: UpcomingFragment.OnItemViewClickListener?
) : RecyclerView.Adapter<UpcomingMoviesAdapter.UpcomingViewHolder>() {

    private var UpcomingMovieCardList = listOf<UpcomingMovieCard>()

    fun setData(data: List<UpcomingMovieCard>) {
        UpcomingMovieCardList = data
        notifyDataSetChanged()
    }

    fun removeListener() {
        onItemViewClickListener = null
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UpcomingViewHolder {
        return UpcomingViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_upcoming, parent, false) as View
        )
    }

    override fun onBindViewHolder(holder: UpcomingViewHolder, position: Int) {
        holder.onBind(UpcomingMovieCardList.get(position))
    }

    override fun getItemCount(): Int {
        return UpcomingMovieCardList.size
    }

    inner class UpcomingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun onBind(upcomingMovieCard: UpcomingMovieCard) {
            itemView.apply {
                findViewById<TextView>(R.id.textUpcomingMovieName).text =
                    upcomingMovieCard.date
                findViewById<TextView>(R.id.textUpcomingDate).text =
                    upcomingMovieCard.date
                setOnClickListener{
                    onItemViewClickListener?.onItemViewClick(upcomingMovieCard)
                }
            }
        }
    }

}