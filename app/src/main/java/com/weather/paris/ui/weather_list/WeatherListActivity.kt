package com.weather.paris.ui.weather_list

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.RequestManager
import com.weather.paris.GlideRequests
import com.weather.paris.R
import com.weather.paris.domain.model.Weather
import com.weather.paris.utils.DataState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_weather_list.*
import javax.inject.Inject

@AndroidEntryPoint
class WeatherListActivity : AppCompatActivity() {

    private val TAG: String = "WeatherListActivity"
    private val viewModel: WeatherListViewModel by viewModels()

    @Inject
    lateinit var glideRequests: RequestManager
    private lateinit var adapter: WeatherRecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weather_list)
        initRecyclerView()
        subscribeObservers()
        viewModel.setStateEvent(WeatherListViewModel.WeatherStateEvent.GetWeatherEvent)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_weather_list, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.refresh) {
            viewModel.setStateEvent(WeatherListViewModel.WeatherStateEvent.RefreshWeatherEvent)
        }
        return super.onOptionsItemSelected(item)
    }

    private fun initRecyclerView() {
        adapter = WeatherRecyclerAdapter(glideRequests)
        val layoutManager = LinearLayoutManager(this,
            LinearLayoutManager.VERTICAL, false)
        weather_recycler_view.layoutManager = layoutManager
        weather_recycler_view.adapter = adapter
    }

    private fun subscribeObservers() {
        viewModel.dataState.observe(this, Observer {dataState ->
            when(dataState) {
                is DataState.Success<List<Weather>> -> {
                    loading(false)
                    adapter.weatherList = dataState.data
                    adapter.notifyDataSetChanged()
                }
                is DataState.Loading -> {
                    loading(true)
                }
                is DataState.Error -> {
                    loading(false)
                    Toast.makeText(this, R.string.error, Toast.LENGTH_LONG).show();
                }
            }
        })
    }

    private fun loading(display: Boolean) {
        progress.visibility = if (display) VISIBLE else GONE
    }




}
