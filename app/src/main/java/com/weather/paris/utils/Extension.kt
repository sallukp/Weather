package com.weather.paris.utils

import com.weather.paris.utils.Extension.toDate
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.roundToInt


object Extension {

    fun Float.trimTrailingZero(): String {
        val text = this.toString()
        return if (text.indexOf(".") < 0) {
            text
        } else {
            text.replace("0*$".toRegex(), "").replace("\\.$".toRegex(), "")
        }
    }

    fun Float.toCelsius(): String = "${this.roundToInt()}°C"

    fun Float.toHPa(): String = "${this.roundToInt()}hPa"

    fun Float?.toPercentage() = if (this == null) { "0%" } else { "${this.roundToInt()}%" }

    //Long extension

    // long should be an time stamp in the range of seconds
    fun Long.toTime(): String {
        val date = Date(this * 1000)
        val dateFormat = SimpleDateFormat("h:mma")
        return dateFormat.format(date).toLowerCase()
    }

    // long should be an time stamp in the range of seconds
    fun Long.toDate(): String {
        val date = Date(this * 1000)
        val dateFormat = SimpleDateFormat("EEE, MMM dd")
        return dateFormat.format(date)
    }

    // long should be an time stamp in the range of seconds
    fun Long.toDateAndTime(): String {
        val date = Date(this * 1000)
        val dateFormat = SimpleDateFormat("EEEE, MMMM d, yyyy")
        return "${this.toTime()}, ${dateFormat.format(date)}"
    }

    // long should be an time stamp in the range of milliseconds
    fun Long.toMidnight(): Long {
        val calender = Calendar.getInstance()
        calender.timeInMillis = this
        calender.set(Calendar.HOUR_OF_DAY, 0)
        calender.set(Calendar.MINUTE, 0)
        calender.set(Calendar.SECOND, 0)
        calender.set(Calendar.MILLISECOND, 0)
        return calender.timeInMillis
    }
}