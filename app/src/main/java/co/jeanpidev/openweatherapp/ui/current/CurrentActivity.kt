package co.jeanpidev.openweatherapp.ui.current

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import co.jeanpidev.openweatherapp.R
import co.jeanpidev.openweatherapp.base.BaseActivity
import co.jeanpidev.openweatherapp.databinding.ActivityCurrentBinding
import co.jeanpidev.openweatherapp.model.WeatherResponse
import co.jeanpidev.openweatherapp.utils.EXTRA_CURRENT_WEATHER
import co.jeanpidev.openweatherapp.utils.extension.app
import co.jeanpidev.openweatherapp.utils.extension.setImage
import javax.inject.Inject

class CurrentActivity : BaseActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var viewModel: CurrentViewModel

    private val binding: ActivityCurrentBinding by lazy { ActivityCurrentBinding.inflate(layoutInflater) }

   override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        app.component.currentComponent().create().inject(this)
        viewModel = ViewModelProvider(this, viewModelFactory).get(CurrentViewModel::class.java)

        intent.getParcelableExtra<WeatherResponse>(EXTRA_CURRENT_WEATHER)?.let {
            supportActionBar?.title = getString(R.string.weather_at, it.name)
            viewModel.getCurrentWeather().value = it
        }

        initObservers()
    }

    private fun initObservers() {
        viewModel.getCurrentWeather().observe(this, Observer {
            populateData(it)
        })
    }

    private fun populateData(currentWeather: WeatherResponse) {
        binding.tvCityCountryName.text = getString(R.string.city_country, currentWeather.name, currentWeather.additionalInfo?.country)
        binding.ivConditionIcon.setImage(currentWeather.weather?.get(0)?.icon)
        binding.tvCondition.text = currentWeather.weather?.get(0)?.status
        binding.tvTemperature.text = getString(R.string.temperature_format, currentWeather.temperature?.temperature)
        binding.tvTemperatureMin.text = getString(R.string.min_temperature, currentWeather.temperature?.temperatureMin)
        binding.tvTemperatureMax.text = getString(R.string.max_temperature, currentWeather.temperature?.temperatureMax)
        binding.tvHumidity.text = getString(R.string.humidity_format, currentWeather.temperature?.humidity)
        binding.tvPressure.text = getString(R.string.pressure_format, currentWeather.temperature?.pressure)
        binding.tvWindSpeedDegrees.text = String.format(getString(R.string.wind_format), currentWeather.wind?.speed, currentWeather.wind?.deg)
    }
}
