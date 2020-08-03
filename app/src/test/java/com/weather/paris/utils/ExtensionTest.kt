package com.weather.paris.utils

import com.weather.paris.utils.Extension.toCelsius
import com.weather.paris.utils.Extension.toDate
import com.weather.paris.utils.Extension.toDateAndTime
import com.weather.paris.utils.Extension.toHPa
import com.weather.paris.utils.Extension.toMidnight
import com.weather.paris.utils.Extension.toPercentage
import com.weather.paris.utils.Extension.toTime
import com.weather.paris.utils.Extension.trimTrailingZero
import org.junit.Assert.*
import org.junit.Test

class ExtensionTest {

    @Test
    fun testTrimTrailingZero() {
        assertEquals(3.0f.trimTrailingZero(), "3")
        assertEquals(2.8f.trimTrailingZero(), "2.8")
        assertEquals(3.300f.trimTrailingZero(), "3.3")
    }

    @Test
    fun testToCelsius() {
        assertEquals(35f.toCelsius(), "35°C")
        assertEquals(25.5f.toCelsius(), "26°C")
        assertEquals(23.3f.toCelsius(), "23°C")
    }

    @Test
    fun testToHpa() {
        assertEquals(1055f.toHPa(), "1055hPa")
        assertEquals(1022.6f.toHPa(), "1023hPa")
    }

    @Test
    fun testToPercentage() {
        assertEquals(null.toPercentage(), "0%")
        assertEquals(55f.toPercentage(), "55%")
        assertEquals(12.6f.toPercentage(), "13%")
    }

    @Test
    fun testToTime() {
        assertEquals(1596320667L.toTime(), "12:24am")
        assertEquals(1596342341L.toTime(), "6:25am")
        assertEquals(1596396473L.toTime(), "9:27pm")
    }

    @Test
    fun testToDate() {
        assertEquals(1596320667L.toDate(), "Sunday, August 2")
    }

    @Test
    fun testToDateAndTime() {
        assertEquals(1596320667L.toDateAndTime(), "12:24am, Sunday, August 2, 2020")
        assertEquals(1596329272L.toDateAndTime(), "2:47am, Sunday, August 2, 2020")
    }

    @Test
    fun testToMidnight() {
        assertEquals(1596382242000L.toMidnight(), 1596319200000L)
        assertEquals(1596319200000L.toMidnight(), 1596319200000L)
        assertEquals(1596319100000L.toMidnight(), 1596232800000L)
    }
}