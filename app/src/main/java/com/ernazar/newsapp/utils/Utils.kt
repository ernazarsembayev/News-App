package com.ernazar.newsapp.utils

import android.annotation.SuppressLint
import org.ocpsoft.prettytime.PrettyTime
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*


object Utils {

    private const val format = "yyyy-MM-dd'T'HH:mm:ss'Z'"

    @SuppressLint("SimpleDateFormat")
    fun getAbbreviatedFromDateTime(dateTime: String): String? {
        val p = PrettyTime(Locale(getCountry()))
        var isTime: String? = null
        try {
            val sdf = SimpleDateFormat(
                format,
                Locale.ENGLISH
            )
            val date: Date? = sdf.parse(dateTime)
            isTime = p.format(date)
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        return isTime
    }

    private fun getCountry(): String {
        val locale = Locale.getDefault()
        val country = java.lang.String.valueOf(locale.country)
        return country.lowercase(Locale.getDefault())
    }

}