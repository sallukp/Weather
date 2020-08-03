package com.weather.paris.domain.model

data class Temperatures(val day: Float = 0f, val night: Float = 0f,
                        val morning: Float = 0f, val evening: Float = 0f)