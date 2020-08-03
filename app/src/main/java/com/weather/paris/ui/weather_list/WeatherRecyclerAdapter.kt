package com.weather.paris.ui.weather_list

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.weather.paris.GlideRequests
import com.weather.paris.R
import com.weather.paris.domain.model.Weather
import com.weather.paris.ui.weather_detail.WeatherDetailActivity
import com.weather.paris.utils.Constants
import com.weather.paris.utils.Extension.toDate
import kotlinx.android.synthetic.main.layout_daily_weather_item.view.*

class WeatherRecyclerAdapter(val glideRequests: RequestManager)
    : RecyclerView.Adapter<WeatherRecyclerAdapter.WeatherViewHolder>() {

    var weatherList: List<Weather> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.layout_daily_weather_item,
                parent, false)
        return WeatherViewHolder(view)
    }

    override fun getItemCount(): Int {
        return weatherList.size
    }

    override fun onBindViewHolder(holder: WeatherViewHolder, position: Int) {
        val weather = weatherList[position]
        holder.onBind(position, weather, glideRequests)
    }


    class WeatherViewHolder constructor(
        itemView: View
    ): RecyclerView.ViewHolder(itemView) {
        fun onBind(position: Int, weather: Weather, glideRequests: RequestManager) {
            itemView.date_text.text = weather.date.toDate()
            itemView.title_text.text = weather.weatherDescription.capitalize()
            itemView.subtitle_text.text = weather.temperatureInfo()
            itemView.weather_icon.setImageDrawable(null)
            glideRequests.load(weather.weatherIconUrl()).into(itemView.weather_icon)
            itemView.setOnClickListener {
                val intent = Intent(it.context, WeatherDetailActivity::class.java)
                intent.putExtra(Constants.INDEX_KEY, position)
                it.context.startActivity(intent)
            }
        }
    }
}