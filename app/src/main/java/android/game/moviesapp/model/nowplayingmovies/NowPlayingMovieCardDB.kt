package android.game.moviesapp.model.nowplayingmovies

import java.io.Serializable

class NowPlayingMovieCardDB(): Serializable {

    val list = initList()

    private fun initList(): MutableList<NowPlayingMovieCard> {
        var arraylist = ArrayList<NowPlayingMovieCard>()
        arraylist.add(NowPlayingMovieCard("2021", "Pirates", "7.7"))
        arraylist.add(NowPlayingMovieCard("2020", "New Pirates", "7.8"))
        arraylist.add(NowPlayingMovieCard("2020", "Pirates", "5.7"))
        arraylist.add(NowPlayingMovieCard("2017", "Pirates", "9.7"))
        arraylist.add(NowPlayingMovieCard("2021", "Pirates", "7.7"))
        arraylist.add(NowPlayingMovieCard("2020", "New Pirates", "7.8"))
        arraylist.add(NowPlayingMovieCard("2020", "Pirates", "5.7"))
        arraylist.add(NowPlayingMovieCard("2017", "Pirates", "9.7"))
        return arraylist
    }

}