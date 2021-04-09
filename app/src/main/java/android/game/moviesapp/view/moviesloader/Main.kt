

import android.game.moviesapp.model.upcomingmovies.UpcomingMovieDTO
import android.game.moviesapp.view.moviesloader.ParseString
import android.game.moviesapp.view.moviesloader.UpcomingMoviesLoader
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

fun main() {
  //  loadMovies()
}

 // https://api.themoviedb.org/3/movie/upcoming?api_key=df29ce69d803b8fd9c32aee7fb421a48&language=en-US&page=1

private val key = "df29ce69d803b8fd9c32aee7fb421a48"

fun loadMovies() {
//    Log.d("loadMovies() status", "start to work")
    try {
        val uri =
            URL("https://api.themoviedb.org/3/movie/upcoming?api_key=$key&language=en-US&page=1")

        Thread(Runnable {
            lateinit var urlConnection: HttpsURLConnection
     //       Log.d("loadMovies() status", "try to work")
            try {
                urlConnection = uri.openConnection() as HttpsURLConnection
                urlConnection.requestMethod = "GET"
                urlConnection.readTimeout = 20000
                val bufferedReader =
                    BufferedReader(InputStreamReader(urlConnection.inputStream))
            //    Log.d("loadMovies() status", "upcoming works")
                val s = getLines(bufferedReader)
                print(s)
            } catch (e: Exception) {
           //     Log.e("", "Fail connection", e)
                e.printStackTrace()

            } finally {

            }
        }).start()
    } catch (e: Exception) {
        e.printStackTrace()
    } finally {
    //    Log.d("loadMovies() status", "Fail connection2 upcoming")
    }

}



@RequiresApi(Build.VERSION_CODES.N)
private fun getLines(reader: BufferedReader): String {
    return reader.lines().collect(Collectors.joining("\n"))
}

fun printMovies(jsonStr: String){

    val list = ParseString().getMovieList(jsonStr)

    val listUpcomingMovieDTO: MutableList<UpcomingMovieDTO> = ArrayList()

    for (i in 0 until list.size){
        try{
            val  upcomingMovieDTO : UpcomingMovieDTO = Gson().fromJson(list[i], UpcomingMovieDTO::class.java )
            listUpcomingMovieDTO.add(upcomingMovieDTO)
        }catch(e: JsonSyntaxException){
            e.printStackTrace()
        }
        catch(e: NullPointerException){
            e.printStackTrace()
        }
    }

    for(i in 0 until listUpcomingMovieDTO.size){
        println(listUpcomingMovieDTO[i].original_title)
        println(listUpcomingMovieDTO[i].release_date)
        println(listUpcomingMovieDTO[i].overview)
    }
}




    val json = "{\n" +
            "  \"dates\": {\n" +
            "    \"maximum\": \"2021-04-26\",\n" +
            "    \"minimum\": \"2021-04-09\"\n" +
            "  },\n" +
            "  \"page\": 1,\n" +
            "  \"results\": [\n" +
            "    {\n" +
            "      \"adult\": false,\n" +
            "      \"backdrop_path\": \"/hJuDvwzS0SPlsE6MNFOpznQltDZ.jpg\",\n" +
            "      \"genre_ids\": [\n" +
            "        16,\n" +
            "        12,\n" +
            "        14,\n" +
            "        10751,\n" +
            "        28\n" +
            "      ],\n" +
            "      \"id\": 527774,\n" +
            "      \"original_language\": \"en\",\n" +
            "      \"original_title\": \"Raya and the Last Dragon\",\n" +
            "      \"overview\": \"Long ago, in the fantasy world of Kumandra, humans and dragons lived together in harmony. But when an evil force threatened the land, the dragons sacrificed themselves to save humanity. Now, 500 years later, that same evil has returned and it’s up to a lone warrior, Raya, to track down the legendary last dragon to restore the fractured land and its divided people.\",\n" +
            "      \"popularity\": 4243.624,\n" +
            "      \"poster_path\": \"/lPsD10PP4rgUGiGR4CCXA6iY0QQ.jpg\",\n" +
            "      \"release_date\": \"2021-03-03\",\n" +
            "      \"title\": \"Raya and the Last Dragon\",\n" +
            "      \"video\": false,\n" +
            "      \"vote_average\": 8.3,\n" +
            "      \"vote_count\": 1943\n" +
            "    },\n" +
            "    {\n" +
            "      \"adult\": false,\n" +
            "      \"backdrop_path\": \"/fev8UFNFFYsD5q7AcYS8LyTzqwl.jpg\",\n" +
            "      \"genre_ids\": [\n" +
            "        28,\n" +
            "        35,\n" +
            "        10751,\n" +
            "        16\n" +
            "      ],\n" +
            "      \"id\": 587807,\n" +
            "      \"original_language\": \"en\",\n" +
            "      \"original_title\": \"Tom & Jerry\",\n" +
            "      \"overview\": \"Tom the cat and Jerry the mouse get kicked out of their home and relocate to a fancy New York hotel, where a scrappy employee named Kayla will lose her job if she can’t evict Jerry before a high-class wedding at the hotel. Her solution? Hiring Tom to get rid of the pesky mouse.\",\n" +
            "      \"popularity\": 2483.116,\n" +
            "      \"poster_path\": \"/6KErczPBROQty7QoIsaa6wJYXZi.jpg\",\n" +
            "      \"release_date\": \"2021-02-11\",\n" +
            "      \"title\": \"Tom & Jerry\",\n" +
            "      \"video\": false,\n" +
            "      \"vote_average\": 7.4,\n" +
            "      \"vote_count\": 1064\n" +
            "    },\n" +
            "    {\n" +
            "      \"adult\": false,\n" +
            "      \"backdrop_path\": \"/zDq2pwPyt4xwSFHKUoNN2LohDWj.jpg\",\n" +
            "      \"genre_ids\": [\n" +
            "        27\n" +
            "      ],\n" +
            "      \"id\": 632357,\n" +
            "      \"original_language\": \"en\",\n" +
            "      \"original_title\": \"The Unholy\",\n" +
            "      \"overview\": \"Alice, a young hearing-impaired girl who, after a supposed visitation from the Virgin Mary, is inexplicably able to hear, speak and heal the sick. As word spreads and people from near and far flock to witness her miracles, a disgraced journalist hoping to revive his career visits the small New England town to investigate. When terrifying events begin to happen all around, he starts to question if these phenomena are the works of the Virgin Mary or something much more sinister.\",\n" +
            "      \"popularity\": 892.508,\n" +
            "      \"poster_path\": \"/pIr6xlGIePQ7JxIqc7jz7z5ql8y.jpg\",\n" +
            "      \"release_date\": \"2021-03-31\",\n" +
            "      \"title\": \"The Unholy\",\n" +
            "      \"video\": false,\n" +
            "      \"vote_average\": 5.8,\n" +
            "      \"vote_count\": 11\n" +
            "    },\n" +
            "    {\n" +
            "      \"adult\": false,\n" +
            "      \"backdrop_path\": \"/3GW0A72MxsSgghqpjc2O2MvO8Ec.jpg\",\n" +
            "      \"genre_ids\": [\n" +
            "        27,\n" +
            "        35\n" +
            "      ],\n" +
            "      \"id\": 714277,\n" +
            "      \"original_language\": \"es\",\n" +
            "      \"original_title\": \"La Funeraria\",\n" +
            "      \"overview\": \"Bernardo is an undertaker. He runs his mortuary business in the same house where he resides. In the front, he has his clients. And in the back, his dysfunctional family lives amongst coffins, wreaths, and the mischievous but nonviolent ghosts that visit on a daily basis. But when a malevolent entity enters the scene, it wreaks havoc on the already fractured household.\",\n" +
            "      \"popularity\": 464.942,\n" +
            "      \"poster_path\": \"/1rlgIzw129hEl46bFaJZ7wpEEZZ.jpg\",\n" +
            "      \"release_date\": \"2021-04-15\",\n" +
            "      \"title\": \"The Funeral Home\",\n" +
            "      \"video\": false,\n" +
            "      \"vote_average\": 0,\n" +
            "      \"vote_count\": 0\n" +
            "    },\n" +
            "    {\n" +
            "      \"adult\": false,\n" +
            "      \"backdrop_path\": \"/d2UxKyaJ5GgzuHaSsWinFfv3g6L.jpg\",\n" +
            "      \"genre_ids\": [\n" +
            "        28,\n" +
            "        27,\n" +
            "        53\n" +
            "      ],\n" +
            "      \"id\": 581392,\n" +
            "      \"original_language\": \"ko\",\n" +
            "      \"original_title\": \"반도\",\n" +
            "      \"overview\": \"A soldier and his team battle hordes of post-apocalyptic zombies in the wastelands of the Korean Peninsula.\",\n" +
            "      \"popularity\": 412.674,\n" +
            "      \"poster_path\": \"/7S9RvfMBWSTlUZ4VbxDY7oLeenk.jpg\",\n" +
            "      \"release_date\": \"2020-07-15\",\n" +
            "      \"title\": \"Peninsula\",\n" +
            "      \"video\": false,\n" +
            "      \"vote_average\": 7,\n" +
            "      \"vote_count\": 1347\n" +
            "    },\n" +
            "    {\n" +
            "      \"adult\": false,\n" +
            "      \"backdrop_path\": \"/d1sVANghKKMZNvqjW0V6y1ejvV9.jpg\",\n" +
            "      \"genre_ids\": [\n" +
            "        16,\n" +
            "        28,\n" +
            "        12,\n" +
            "        14,\n" +
            "        18\n" +
            "      ],\n" +
            "      \"id\": 635302,\n" +
            "      \"original_language\": \"ja\",\n" +
            "      \"original_title\": \"劇場版「鬼滅の刃」無限列車編\",\n" +
            "      \"overview\": \"Tanjirō Kamado, joined with Inosuke Hashibira, a boy raised by boars who wears a boar's head, and Zenitsu Agatsuma, a scared boy who reveals his true power when he sleeps, boards the Infinity Train on a new mission with the Fire Hashira, Kyōjurō Rengoku, to defeat a demon who has been tormenting the people and killing the demon slayers who oppose it!\",\n" +
            "      \"popularity\": 317.42,\n" +
            "      \"poster_path\": \"/yF45egpHwaYLn4jTyZAgk0Cmug9.jpg\",\n" +
            "      \"release_date\": \"2020-10-16\",\n" +
            "      \"title\": \"Demon Slayer - Kimetsu no Yaiba - The Movie: Mugen Train\",\n" +
            "      \"video\": false,\n" +
            "      \"vote_average\": 8,\n" +
            "      \"vote_count\": 533\n" +
            "    },\n" +
            "    {\n" +
            "      \"adult\": false,\n" +
            "      \"backdrop_path\": \"/3phkepIrEHnTNGE6ajEGdBfRxki.jpg\",\n" +
            "      \"genre_ids\": [\n" +
            "        14,\n" +
            "        28,\n" +
            "        12\n" +
            "      ],\n" +
            "      \"id\": 460465,\n" +
            "      \"original_language\": \"en\",\n" +
            "      \"original_title\": \"Mortal Kombat\",\n" +
            "      \"overview\": \"MMA fighter Cole Young seeks out Earth's greatest champions in order to stand against the enemies of Outworld in a high stakes battle for the universe.\",\n" +
            "      \"popularity\": 245.541,\n" +
            "      \"poster_path\": \"/8yhtzsbBExY8mUct2GOk4LDDuGH.jpg\",\n" +
            "      \"release_date\": \"2021-04-07\",\n" +
            "      \"title\": \"Mortal Kombat\",\n" +
            "      \"video\": false,\n" +
            "      \"vote_average\": 0,\n" +
            "      \"vote_count\": 0\n" +
            "    },\n" +
            "    {\n" +
            "      \"adult\": false,\n" +
            "      \"backdrop_path\": \"/ne4QPHn56Z1vxk6iKJKvDnmNI9Y.jpg\",\n" +
            "      \"genre_ids\": [\n" +
            "        28,\n" +
            "        80,\n" +
            "        18,\n" +
            "        53\n" +
            "      ],\n" +
            "      \"id\": 611914,\n" +
            "      \"original_language\": \"en\",\n" +
            "      \"original_title\": \"The Courier\",\n" +
            "      \"overview\": \"This intense action-thriller unfolds in real time as two embattled souls fight for their lives. Gary Oldman stars as a vicious crime boss out to kill Nick, the lone witness set to testify against him. He hires a mysterious female motorcycle courier to unknowingly deliver a poison-gas bomb to slay Nick, but after she rescues Nick from certain death, the duo must confront an army of ruthless hired killers in order to survive the night.\",\n" +
            "      \"popularity\": 330.373,\n" +
            "      \"poster_path\": \"/cmC7bJZ5sCzx9ZsrlXOrYJfYtLc.jpg\",\n" +
            "      \"release_date\": \"2019-11-22\",\n" +
            "      \"title\": \"The Courier\",\n" +
            "      \"video\": false,\n" +
            "      \"vote_average\": 6.3,\n" +
            "      \"vote_count\": 347\n" +
            "    },\n" +
            "    {\n" +
            "      \"adult\": false,\n" +
            "      \"backdrop_path\": \"/bjjZXrP8PEdFeJkKERc62xlarMI.jpg\",\n" +
            "      \"genre_ids\": [\n" +
            "        18,\n" +
            "        37\n" +
            "      ],\n" +
            "      \"id\": 581734,\n" +
            "      \"original_language\": \"en\",\n" +
            "      \"original_title\": \"Nomadland\",\n" +
            "      \"overview\": \"A woman in her sixties embarks on a journey through the western United States after losing everything in the Great Recession, living as a van-dwelling modern-day nomad.\",\n" +
            "      \"popularity\": 294.257,\n" +
            "      \"poster_path\": \"/fmHBjfiMb7cP0cikF17LoA8E1bp.jpg\",\n" +
            "      \"release_date\": \"2020-12-26\",\n" +
            "      \"title\": \"Nomadland\",\n" +
            "      \"video\": false,\n" +
            "      \"vote_average\": 7.6,\n" +
            "      \"vote_count\": 426\n" +
            "    },\n" +
            "    {\n" +
            "      \"adult\": false,\n" +
            "      \"backdrop_path\": \"/nfK1HKYhtseKfBCs71sIweuQeuZ.jpg\",\n" +
            "      \"genre_ids\": [\n" +
            "        18\n" +
            "      ],\n" +
            "      \"id\": 694843,\n" +
            "      \"original_language\": \"he\",\n" +
            "      \"original_title\": \"אסיה\",\n" +
            "      \"overview\": \"Asia is the single mother of 17-year-old Vika. Vika's deteriorating health urges Asia to finally find her voice as a mother and to embrace and cherish their time together.\",\n" +
            "      \"popularity\": 207.394,\n" +
            "      \"poster_path\": \"/3dvOmfAHkMoTQfogaUnfnpRfRDd.jpg\",\n" +
            "      \"release_date\": \"2021-04-23\",\n" +
            "      \"title\": \"Asia\",\n" +
            "      \"video\": false,\n" +
            "      \"vote_average\": 0,\n" +
            "      \"vote_count\": 0\n" +
            "    },\n" +
            "    {\n" +
            "      \"adult\": false,\n" +
            "      \"backdrop_path\": \"/vX5JtEcumMQvMCLVcIqfetc7hdg.jpg\",\n" +
            "      \"genre_ids\": [\n" +
            "        35,\n" +
            "        80,\n" +
            "        53\n" +
            "      ],\n" +
            "      \"id\": 601666,\n" +
            "      \"original_language\": \"en\",\n" +
            "      \"original_title\": \"I Care a Lot\",\n" +
            "      \"overview\": \"A court-appointed legal guardian defrauds her older clients and traps them under her care. But her latest mark comes with some unexpected baggage.\",\n" +
            "      \"popularity\": 259.955,\n" +
            "      \"poster_path\": \"/gKnhEsjNefpKnUdAkn7INzIFLSu.jpg\",\n" +
            "      \"release_date\": \"2021-02-19\",\n" +
            "      \"title\": \"I Care a Lot\",\n" +
            "      \"video\": false,\n" +
            "      \"vote_average\": 6.7,\n" +
            "      \"vote_count\": 1116\n" +
            "    },\n" +
            "    {\n" +
            "      \"adult\": false,\n" +
            "      \"backdrop_path\": \"/5NxjLfs7Bi07bfZCRl9CCnUw7AA.jpg\",\n" +
            "      \"genre_ids\": [\n" +
            "        878,\n" +
            "        28,\n" +
            "        12,\n" +
            "        53\n" +
            "      ],\n" +
            "      \"id\": 412656,\n" +
            "      \"original_language\": \"en\",\n" +
            "      \"original_title\": \"Chaos Walking\",\n" +
            "      \"overview\": \"Two unlikely companions embark on a perilous adventure through the badlands of an unexplored planet as they try to escape a dangerous and disorienting reality, where all inner thoughts are seen and heard by everyone.\",\n" +
            "      \"popularity\": 257.19,\n" +
            "      \"poster_path\": \"/3W9EpQCVzOfZmeWDMMEiLdtSKSe.jpg\",\n" +
            "      \"release_date\": \"2021-02-24\",\n" +
            "      \"title\": \"Chaos Walking\",\n" +
            "      \"video\": false,\n" +
            "      \"vote_average\": 7.3,\n" +
            "      \"vote_count\": 83\n" +
            "    },\n" +
            "    {\n" +
            "      \"adult\": false,\n" +
            "      \"backdrop_path\": \"/h3weAFgg06GqchI2xDfufBgSFTj.jpg\",\n" +
            "      \"genre_ids\": [\n" +
            "        18\n" +
            "      ],\n" +
            "      \"id\": 600354,\n" +
            "      \"original_language\": \"en\",\n" +
            "      \"original_title\": \"The Father\",\n" +
            "      \"overview\": \"A man refuses all assistance from his daughter as he ages and, as he tries to make sense of his changing circumstances, he begins to doubt his loved ones, his own mind and even the fabric of his reality.\",\n" +
            "      \"popularity\": 157.379,\n" +
            "      \"poster_path\": \"/uxWXW1YYQENSv7OzHB4Hds0bK3b.jpg\",\n" +
            "      \"release_date\": \"2021-02-26\",\n" +
            "      \"title\": \"The Father\",\n" +
            "      \"video\": false,\n" +
            "      \"vote_average\": 8.3,\n" +
            "      \"vote_count\": 146\n" +
            "    },\n" +
            "    {\n" +
            "      \"adult\": false,\n" +
            "      \"backdrop_path\": \"/5KmhjlR5CEarB8mKtpjcjHRYIu9.jpg\",\n" +
            "      \"genre_ids\": [\n" +
            "        80,\n" +
            "        28,\n" +
            "        18\n" +
            "      ],\n" +
            "      \"id\": 535292,\n" +
            "      \"original_language\": \"en\",\n" +
            "      \"original_title\": \"21 Bridges\",\n" +
            "      \"overview\": \"An embattled NYPD detective, is thrust into a citywide manhunt for a pair of cop killers after uncovering a massive and unexpected conspiracy. As the night unfolds, lines become blurred on who he is pursuing, and who is in pursuit of him.\",\n" +
            "      \"popularity\": 179.629,\n" +
            "      \"poster_path\": \"/bSN9SysoRBMwJKNkfOlQlCw2YQO.jpg\",\n" +
            "      \"release_date\": \"2019-10-24\",\n" +
            "      \"title\": \"21 Bridges\",\n" +
            "      \"video\": false,\n" +
            "      \"vote_average\": 6.8,\n" +
            "      \"vote_count\": 1225\n" +
            "    },\n" +
            "    {\n" +
            "      \"adult\": false,\n" +
            "      \"backdrop_path\": \"/9Is9OrQUnKczCfsLSbsbx8YSmES.jpg\",\n" +
            "      \"genre_ids\": [\n" +
            "        16,\n" +
            "        35,\n" +
            "        14\n" +
            "      ],\n" +
            "      \"id\": 797394,\n" +
            "      \"original_language\": \"ru\",\n" +
            "      \"original_title\": \"Ганзель, Гретель и Агентство Магии\",\n" +
            "      \"overview\": \"The Secret Magic Control Agency sends its two best agents, Hansel and Gretel, to fight against the witch of the Gingerbread House.\",\n" +
            "      \"popularity\": 186.425,\n" +
            "      \"poster_path\": \"/4ZSzEDVdxWVMVO4oZDvoodQOEfr.jpg\",\n" +
            "      \"release_date\": \"2021-03-18\",\n" +
            "      \"title\": \"Secret Magic Control Agency\",\n" +
            "      \"video\": false,\n" +
            "      \"vote_average\": 7.4,\n" +
            "      \"vote_count\": 56\n" +
            "    },\n" +
            "    {\n" +
            "      \"adult\": false,\n" +
            "      \"backdrop_path\": \"/fatz1aegtBGh7KS0gipcsw9MqUn.jpg\",\n" +
            "      \"genre_ids\": [\n" +
            "        18,\n" +
            "        36\n" +
            "      ],\n" +
            "      \"id\": 583406,\n" +
            "      \"original_language\": \"en\",\n" +
            "      \"original_title\": \"Judas and the Black Messiah\",\n" +
            "      \"overview\": \"Bill O'Neal infiltrates the Black Panthers on the orders of FBI Agent Mitchell and J. Edgar Hoover. As Black Panther Chairman Fred Hampton ascends—falling for a fellow revolutionary en route—a battle wages for O’Neal’s soul.\",\n" +
            "      \"popularity\": 173.157,\n" +
            "      \"poster_path\": \"/iIgr75GoqFxe1X5Wz9siOODGe9u.jpg\",\n" +
            "      \"release_date\": \"2021-02-12\",\n" +
            "      \"title\": \"Judas and the Black Messiah\",\n" +
            "      \"video\": false,\n" +
            "      \"vote_average\": 7.5,\n" +
            "      \"vote_count\": 318\n" +
            "    },\n" +
            "    {\n" +
            "      \"adult\": false,\n" +
            "      \"backdrop_path\": \"/sBwGOfJtSF6hlXaEgvFfBfeLqMk.jpg\",\n" +
            "      \"genre_ids\": [\n" +
            "        28,\n" +
            "        53,\n" +
            "        80\n" +
            "      ],\n" +
            "      \"id\": 615457,\n" +
            "      \"original_language\": \"en\",\n" +
            "      \"original_title\": \"Nobody\",\n" +
            "      \"overview\": \"Hutch Mansell, a suburban dad, overlooked husband, nothing neighbor — a \\\"nobody.\\\" When two thieves break into his home one night, Hutch's unknown long-simmering rage is ignited and propels him on a brutal path that will uncover dark secrets he fought to leave behind.\",\n" +
            "      \"popularity\": 117.648,\n" +
            "      \"poster_path\": \"/oBgWY00bEFeZ9N25wWVyuQddbAo.jpg\",\n" +
            "      \"release_date\": \"2021-03-18\",\n" +
            "      \"title\": \"Nobody\",\n" +
            "      \"video\": false,\n" +
            "      \"vote_average\": 7.8,\n" +
            "      \"vote_count\": 26\n" +
            "    },\n" +
            "    {\n" +
            "      \"adult\": false,\n" +
            "      \"backdrop_path\": \"/gESm6yc2MZpSXakgqLF0OlLaRn1.jpg\",\n" +
            "      \"genre_ids\": [\n" +
            "        53,\n" +
            "        80,\n" +
            "        18\n" +
            "      ],\n" +
            "      \"id\": 582014,\n" +
            "      \"original_language\": \"en\",\n" +
            "      \"original_title\": \"Promising Young Woman\",\n" +
            "      \"overview\": \"A young woman, traumatized by a tragic event in her past, seeks out vengeance against those who crossed her path.\",\n" +
            "      \"popularity\": 80.828,\n" +
            "      \"poster_path\": \"/cjzU4g6SlScnP4MdkleyI25KGlR.jpg\",\n" +
            "      \"release_date\": \"2020-12-13\",\n" +
            "      \"title\": \"Promising Young Woman\",\n" +
            "      \"video\": false,\n" +
            "      \"vote_average\": 7.6,\n" +
            "      \"vote_count\": 589\n" +
            "    },\n" +
            "    {\n" +
            "      \"adult\": false,\n" +
            "      \"backdrop_path\": \"/sthDtZfswdU0d0U8SImsy5eEYy4.jpg\",\n" +
            "      \"genre_ids\": [\n" +
            "        18\n" +
            "      ],\n" +
            "      \"id\": 615643,\n" +
            "      \"original_language\": \"en\",\n" +
            "      \"original_title\": \"Minari\",\n" +
            "      \"overview\": \"A Korean-American family moves to Arkansas in search of their own American Dream. With the arrival of their sly, foul-mouthed, but incredibly loving grandmother, the stability of their relationships is challenged even more in this new life in the rugged Ozarks, testing the undeniable resilience of family and what really makes a home.\",\n" +
            "      \"popularity\": 67.1,\n" +
            "      \"poster_path\": \"/9Bb6K6HINl3vEKCu8WXEZyHvvpq.jpg\",\n" +
            "      \"release_date\": \"2021-02-12\",\n" +
            "      \"title\": \"Minari\",\n" +
            "      \"video\": false,\n" +
            "      \"vote_average\": 7.7,\n" +
            "      \"vote_count\": 202\n" +
            "    },\n" +
            "    {\n" +
            "      \"adult\": false,\n" +
            "      \"backdrop_path\": \"/9WIY9zi8f8ckBZ64DxFw62ATXwk.jpg\",\n" +
            "      \"genre_ids\": [\n" +
            "        28,\n" +
            "        53\n" +
            "      ],\n" +
            "      \"id\": 797294,\n" +
            "      \"original_language\": \"en\",\n" +
            "      \"original_title\": \"Assault on VA-33\",\n" +
            "      \"overview\": \"Decorated veteran and PTSD sufferer, Jason Hill meets his wife, Jennifer, for lunch at the Veteran's Affairs hospital where she works. After Jennifer is called away for an emergency consultation with the head of US Military's Joint Chiefs of Staff, the hospital is taken hostage by heavily armed terrorists. Jason becomes the last line of defense and must battle the terrorists and his own PTSD induced demons to save his wife, the General, the hospital's staff and patients.\",\n" +
            "      \"popularity\": 54.265,\n" +
            "      \"poster_path\": \"/xsMxZUaKlmDqMPFCL75F1kWxzsX.jpg\",\n" +
            "      \"release_date\": \"2021-04-02\",\n" +
            "      \"title\": \"Assault on VA-33\",\n" +
            "      \"video\": false,\n" +
            "      \"vote_average\": 0,\n" +
            "      \"vote_count\": 0\n" +
            "    }\n" +
            "  ],\n" +
            "  \"total_pages\": 9,\n" +
            "  \"total_results\": 169\n" +
            "}"