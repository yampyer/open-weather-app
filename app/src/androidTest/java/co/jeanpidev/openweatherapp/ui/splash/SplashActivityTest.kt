package co.jeanpidev.openweatherapp.ui.splash

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.matcher.IntentMatchers
import androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent
import androidx.test.espresso.intent.rule.IntentsTestRule
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import co.jeanpidev.openweatherapp.R
import co.jeanpidev.openweatherapp.ui.search.SearchActivity
import co.jeanpidev.openweatherapp.utils.SPLASH_TIME_OUT
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class SplashActivityTest {

    @Rule
    @JvmField
    var activityTestRule = IntentsTestRule(SplashActivity::class.java)

    @Test
    fun initialStateSplashScreenTest() {
        onView(withId(R.id.iv_logo_app))
            .check(matches(isDisplayed()))

        Thread.sleep(SPLASH_TIME_OUT)
        intended(hasComponent(SearchActivity::class.java.name))
    }
}
