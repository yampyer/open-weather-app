package co.jeanpidev.openweatherapp.ui.current

import android.content.Context
import android.content.Intent
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import co.jeanpidev.openweatherapp.R
import co.jeanpidev.openweatherapp.model.WeatherResponse
import co.jeanpidev.openweatherapp.utils.*
import com.google.gson.GsonBuilder
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class CurrentActivityTest {

    companion object {
        const val CITY_COUNTRY = "Medellín, CO"
        const val CONDITION = "Clouds"
        const val TEMPERATURE = "20.02°C"
        const val TEMPERATURE_MIN = "Min: 17.22°C"
        const val TEMPERATURE_MAX = "Max: 24.00°C"
        const val HUMIDITY = "57%"
        const val PRESSURE = "1022 hPa"
        const val WIND = "1.50m/s 10°"
    }

    @get:Rule
    val activityTestRule = ActivityTestRule(CurrentActivity::class.java, false, false)

    private val context: Context = InstrumentationRegistry.getInstrumentation().context

    private lateinit var weatherResponse: WeatherResponse

    @Before
    fun init() {
        val gson = GsonBuilder().create()
        weatherResponse = gson.fromJson(RestServiceTestHelper.getStringFromFile(context, "city_weather_response.json"), WeatherResponse::class.java)
    }

    @Test
    fun searchCityByNameTest() {
        val intent = Intent().putExtra(EXTRA_CURRENT_WEATHER, weatherResponse)
        activityTestRule.launchActivity(intent)

        onView(withId(R.id.tv_city_country_name)).check(matches(withText(CITY_COUNTRY)))
        onView(withId(R.id.tv_condition)).check(matches(withText(CONDITION)))
        onView(withId(R.id.tv_temperature)).check(matches(withText(TEMPERATURE)))
        onView(withId(R.id.tv_temperature_min)).check(matches(withText(TEMPERATURE_MIN)))
        onView(withId(R.id.tv_temperature_max)).check(matches(withText(TEMPERATURE_MAX)))
        onView(withId(R.id.tv_humidity)).check(matches(withText(HUMIDITY)))
        onView(withId(R.id.tv_pressure)).check(matches(withText(PRESSURE)))
        onView(withId(R.id.tv_wind_speed_degrees)).check(matches(withText(WIND)))
    }
}
