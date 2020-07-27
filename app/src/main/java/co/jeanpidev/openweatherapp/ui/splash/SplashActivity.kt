package co.jeanpidev.openweatherapp.ui.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import co.jeanpidev.openweatherapp.base.BaseActivity
import co.jeanpidev.openweatherapp.databinding.ActivitySplashBinding
import co.jeanpidev.openweatherapp.ui.search.SearchActivity
import co.jeanpidev.openweatherapp.utils.SPLASH_TIME_OUT

class SplashActivity : BaseActivity() {

    private val binding: ActivitySplashBinding by lazy { ActivitySplashBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        Handler().postDelayed({
            startActivity(Intent(this, SearchActivity::class.java))
            finish()
        }, SPLASH_TIME_OUT)
    }
}
