package co.jeanpidev.openweatherapp.ui.search

import android.content.Context
import android.content.Intent
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent
import androidx.test.espresso.intent.rule.IntentsTestRule
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.platform.app.InstrumentationRegistry
import co.jeanpidev.openweatherapp.R
import co.jeanpidev.openweatherapp.ui.current.CurrentActivity
import co.jeanpidev.openweatherapp.utils.CustomMatchers
import co.jeanpidev.openweatherapp.utils.MockWebServerBaseTest
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.net.HttpURLConnection

@RunWith(AndroidJUnit4::class)
@LargeTest
class SearchActivityTest: MockWebServerBaseTest() {

    companion object {
        const val CITY = "Medellin"
    }

    @get:Rule
    val activityTestRule: IntentsTestRule<SearchActivity> = IntentsTestRule(SearchActivity::class.java, false, false)

    private val context: Context = InstrumentationRegistry.getInstrumentation().context

    @Test
    fun searchWithoutCityNameTest() {
        activityTestRule.launchActivity(Intent())
        onView(withId(R.id.btn_search_icon)).perform(click())

        onView(withText(R.string.please_enter_city_name)).inRoot(CustomMatchers.isToast()).check(matches(isDisplayed()))
    }

    @Test
    fun searchCityByNameNotFoundTest() {
        mockHttpResponse(context,
            "no_city_weather_response.json",
            HttpURLConnection.HTTP_NOT_FOUND)
        activityTestRule.launchActivity(Intent())

        onView(withId(R.id.et_input_city))
            .perform(typeText(CITY), closeSoftKeyboard())
        onView(withId(R.id.btn_search_icon)).perform(click())

        onView(withText(R.string.no_city_found)).inRoot(CustomMatchers.isToast()).check(matches(isDisplayed()))
    }

    @Test
    fun searchCityByNameTest() {
        mockHttpResponse(context,
            "city_weather_response.json",
            HttpURLConnection.HTTP_OK)
        activityTestRule.launchActivity(Intent())

        onView(withId(R.id.et_input_city))
            .perform(typeText(CITY), closeSoftKeyboard())
        onView(withId(R.id.btn_search_icon)).perform(click())

        intended(hasComponent(CurrentActivity::class.java.name))
    }

    @Test
    fun searchCityByPopularCityBtnTest() {
        mockHttpResponse(context,
            "city_weather_response.json",
            HttpURLConnection.HTTP_OK)
        activityTestRule.launchActivity(Intent())

        onView(withId(R.id.tv_montevideo)).perform(click())

        intended(hasComponent(CurrentActivity::class.java.name))
    }

    override fun isMockServerEnabled() = true
}
