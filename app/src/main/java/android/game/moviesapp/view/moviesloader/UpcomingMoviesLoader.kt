package android.game.moviesapp.view.moviesloader

import android.game.moviesapp.model.upcomingmovies.UpcomingMovieDTO
import android.game.moviesapp.model.upcomingmovies.UpcomingMovieCard
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


// https://api.themoviedb.org/3/movie/upcoming?api_key=df29ce69d803b8fd9c32aee7fb421a48&language=en-US&page=1

// df29ce69d803b8fd9c32aee7fb421a48

@RequiresApi(Build.VERSION_CODES.N)
class UpcomingMoviesLoader() {

    lateinit var listener: UpcomingMoviesLoaderListener

    constructor(listener: UpcomingMoviesLoaderListener) : this(){
        this.listener = listener
    }

    private val key = "df29ce69d803b8fd9c32aee7fb421a48"

    // "https://api.weather.yandex.ru/v2/informers?lat=${lat}&lon=${lon}"

    fun loadMovies() {
        Log.d("loadMovies() status", "start to work")
        try {
            val uri =
                URL("https://api.themoviedb.org/3/movie/upcoming?api_key=$key&language=en-US&page=1")
            val handler = Handler()
            Thread(Runnable {
                lateinit var urlConnection: HttpsURLConnection
                Log.d("loadMovies() status", "try to work")
                try {
                    urlConnection = uri.openConnection() as HttpsURLConnection
                    urlConnection.requestMethod = "GET"
                    urlConnection.readTimeout = 20000
                    val bufferedReader =
                        BufferedReader(InputStreamReader(urlConnection.inputStream))
                    Log.d("loadMovies() status", "upcoming works")
                    val s = getLines(bufferedReader)
                    print(s)
                    handler.post { listener.onLoaded(convertFromDTOTOCard(parseJsonString(s))) }
                } catch (e: Exception) {
                    Log.e("", "Fail connection", e)
                    e.printStackTrace()
                    listener.onFailed(e)
                } finally {

                }
            }).start()
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            Log.d("loadMovies() status", "Fail connection2 upcoming")
        }

    }

    private fun convertFromDTOTOCard(list: MutableList<UpcomingMovieDTO>): MutableList<UpcomingMovieCard> {
        var listCard: MutableList<UpcomingMovieCard> = ArrayList()
        for (i in 0 until list.size) {
            listCard.add(
                UpcomingMovieCard(
                    list[i].original_title.toString(),
                    list[i].release_date.toString()
                )
            )
//            Log.d("list[i].original_title ", list[i].original_title.toString())
//            Log.d("list[i].release_date ", list[i].release_date.toString())
        }
        return listCard
    }

    private fun parseJsonString(jsonStr: String): MutableList<UpcomingMovieDTO> {
        val list = ParseString().getMovieList(jsonStr)
        val listUpcomingMovieDTO: MutableList<UpcomingMovieDTO> = ArrayList()

        for (i in 0 until list.size) {
            try {
                val upcomingMovieDTO: UpcomingMovieDTO =
                    Gson().fromJson(list[i], UpcomingMovieDTO::class.java)
                listUpcomingMovieDTO.add(upcomingMovieDTO)
            } catch (e: JsonSyntaxException) {
                e.printStackTrace()
            } catch (e: NullPointerException) {
                e.printStackTrace()
            }
        }
        return listUpcomingMovieDTO
    }


    @RequiresApi(Build.VERSION_CODES.N)
    private fun getLines(reader: BufferedReader): String {
        return reader.lines().collect(Collectors.joining("\n"))
    }

    interface UpcomingMoviesLoaderListener {
        fun onLoaded(listUpcomingMovieDTO: MutableList<UpcomingMovieCard>)
        fun onFailed(throwable: Throwable)
    }

    /*
        interface WeatherLoaderListener {
        fun onLoaded(weatherDTO: WeatherDTO)
        fun onFailed(throwable: Throwable)
    }
     */


}

/*
@RequiresApi(Build.VERSION_CODES.N)
class WeatherLoader(private val listener: WeatherLoaderListener, private val lat: Double, private val lon: Double) {

    private val key = "key_yandex"

    @RequiresApi(Build.VERSION_CODES.N)
    fun loadWeather() {
        try {
            val uri =
                URL("https://api.weather.yandex.ru/v2/informers?lat=${lat}&lon=${lon}")
            val handler = Handler()
            Thread(Runnable {
                lateinit var urlConnection: HttpsURLConnection
                try {
                    urlConnection = uri.openConnection() as HttpsURLConnection
                    urlConnection.requestMethod = "GET"
                    urlConnection.addRequestProperty(
                        "X-Yandex-API-Key",
                            key
                    )
                    urlConnection.readTimeout = 10000
                    val bufferedReader =
                        BufferedReader(InputStreamReader(urlConnection.inputStream))

                    // преобразование ответа от сервера (JSON) в модель данных (WeatherDTO)
                    val weatherDTO: WeatherDTO =
                        Gson().fromJson(getLines(bufferedReader), WeatherDTO::class.java)
                    handler.post { listener.onLoaded(weatherDTO) }
                } catch (e: Exception) {
                    Log.e("", "Fail connection", e)
                    e.printStackTrace()
                    listener.onFailed(e)
                } finally {
                    urlConnection.disconnect()
                }
            }).start()
        } catch (e: MalformedURLException) {
            Log.e("", "Fail URI", e)
            e.printStackTrace()
            listener.onFailed(e)
        }
    }


    @RequiresApi(Build.VERSION_CODES.N)
    private fun getLines(reader: BufferedReader): String {
        return reader.lines().collect(Collectors.joining("\n"))
    }

    interface WeatherLoaderListener {
        fun onLoaded(weatherDTO: WeatherDTO)
        fun onFailed(throwable: Throwable)
    }
}
 */