package com.weather.paris.ui.weather

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.weather.paris.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WeatherActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
