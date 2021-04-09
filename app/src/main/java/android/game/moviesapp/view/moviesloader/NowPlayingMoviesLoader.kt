package android.game.moviesapp.view.moviesloader

import android.game.moviesapp.model.nowplayingmovies.NowPlayingMovieCard
import android.game.moviesapp.model.nowplayingmovies.NowPlayingMovieDTO
import android.os.Build
import android.os.Handler
import android.util.Log
import androidx.annotation.RequiresApi
import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.URL
import java.util.stream.Collectors
import javax.net.ssl.HttpsURLConnection

class NowPlayingMoviesLoader(val listener: NowPlayingMoviesLoaderListener) {

    // https://api.themoviedb.org/3/movie/now_playing?api_key=df29ce69d803b8fd9c32aee7fb421a48&language=en-US&page=1

    private val key = "df29ce69d803b8fd9c32aee7fb421a48"

    fun loadMovies() {
        try {
            val uri =
                URL("https://api.themoviedb.org/3/movie/now_playing?api_key=$key&language=en-US&page=1")
            val handler = Handler()
            Thread(Runnable {
                lateinit var urlConnection: HttpsURLConnection
                try {
                    urlConnection = uri.openConnection() as HttpsURLConnection
                    urlConnection.readTimeout = 10000
                    val bufferedReader =
                        BufferedReader(InputStreamReader(urlConnection.inputStream))
                    val jsonStr = getLines(bufferedReader)
                    handler.post {
                        listener.onLoaded(convertFromDTOToCard(parseFromJsonString(jsonStr)))
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                    Log.e("", "Fail connection", e)
                    listener.onFailed(e)
                }

            }).start()
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            Log.d("loadMovies() status", "Fail connection2")
        }
    }


    private fun parseFromJsonString(jsonStr: String): MutableList<NowPlayingMovieDTO> {
        val list = ParseString().getMovieList(jsonStr)
        val listNowPlayingMovieDTO: MutableList<NowPlayingMovieDTO> = ArrayList()
        for (i in 0 until list.size) {
            try {
                val nowPlayingMovieDTO: NowPlayingMovieDTO =
                    Gson().fromJson(list[i], NowPlayingMovieDTO::class.java)
                listNowPlayingMovieDTO.add(nowPlayingMovieDTO)
            } catch (e: JsonSyntaxException) {
                e.printStackTrace()
            } catch (e: NullPointerException) {
                e.printStackTrace()
            }
        }
        return listNowPlayingMovieDTO
    }

    private fun convertFromDTOToCard(list: MutableList<NowPlayingMovieDTO>)
            : MutableList<NowPlayingMovieCard> {
        var listCard: MutableList<NowPlayingMovieCard> = ArrayList()
        for (i in 0 until list.size) {
            listCard.add(
                NowPlayingMovieCard(
                    getYearFromDate(list[i].release_date.toString()),
                    list[i].original_title.toString(),
                    list[i].vote_average.toString(),
                    list[i].overview.toString()
                )
            )
        }
        return listCard
    }

    private fun getYearFromDate(date: String): String {
        val array = date.toCharArray()
        var str = ""
        for (i in 0..3) {
            str += array[i]
        }
        return str
    }

    @RequiresApi(Build.VERSION_CODES.N)
    private fun getLines(reader: BufferedReader): String {
        return reader.lines().collect(Collectors.joining("\n"))
    }


    interface NowPlayingMoviesLoaderListener {
        fun onLoaded(listNowPlayingMovieCard: MutableList<NowPlayingMovieCard>)
        fun onFailed(throwable: Throwable)
    }

}


