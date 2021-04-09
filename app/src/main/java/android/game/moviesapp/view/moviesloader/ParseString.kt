package android.game.moviesapp.view.moviesloader

class ParseString {

    fun getMovieList(jsonString: String?): MutableList<String> {
        var startToRecord = false
        var strList: MutableList<String> = ArrayList()
        val c: CharArray = jsonString?.toCharArray() ?: charArrayOf()
        var str = ""
        for (i in c.indices) {
            if (c[i] == '{') {
                startToRecord = true
            }
            if (startToRecord) {
                str += "${c[i]}"
            }
            if (c[i] == '}') {
                startToRecord = false
                strList.add(str)
                str = ""
            }
        }
        return strList
    }
}