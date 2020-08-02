package com.weather.paris.ui.weather_list

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.weather.paris.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WeatherListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
