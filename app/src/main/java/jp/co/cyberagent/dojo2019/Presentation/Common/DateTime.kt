package jp.co.cyberagent.dojo2019.Presentation.Common

class DateTime {
    fun getCustomizedTime(time: Long?): String {
        time?: return ""
        val diff = System.currentTimeMillis() - time

        val sec = diff / 1000L

        val min = sec / 60L
        if (min == 0L) {
            return "${sec}秒前"
        }

        val hour = min / 60L
        if (hour == 0L) {
            return "${min}分前"
        }

        val day = hour / 24L
        if (day == 0L) {
            return "${hour}時間前"
        }
        return "${day}日前"
    }
}