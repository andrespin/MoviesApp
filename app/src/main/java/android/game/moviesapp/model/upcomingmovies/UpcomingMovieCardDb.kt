package android.game.moviesapp.model.upcomingmovies

import java.io.Serializable

class UpcomingMovieCardDb: Serializable {

    val list = initList()

    private fun initList(): MutableList<UpcomingMovieCard> {
        val arrayList = ArrayList<UpcomingMovieCard>()
        arrayList.add(UpcomingMovieCard("Pirates1", "2020-02-03"))
        arrayList.add(UpcomingMovieCard("Pirates2", "2020-02-03"))
        arrayList.add(UpcomingMovieCard("Pirates3", "2020-02-03"))
        arrayList.add(UpcomingMovieCard("Pirates4", "2020-02-03"))
        arrayList.add(UpcomingMovieCard("Pirates5", "2020-02-03"))
        arrayList.add(UpcomingMovieCard("Pirates6", "2020-02-03"))
        arrayList.add(UpcomingMovieCard("Pirates7", "2020-02-03"))
        arrayList.add(UpcomingMovieCard("Pirates8", "2020-02-03"))
        arrayList.add(UpcomingMovieCard("Pirates9", "2020-02-03"))
        arrayList.add(UpcomingMovieCard("Pirates10", "2020-02-03"))
        arrayList.add(UpcomingMovieCard("Pirates11", "2020-02-03"))
        arrayList.add(UpcomingMovieCard("Pirates12", "2020-02-03"))
        return arrayList
    }

}