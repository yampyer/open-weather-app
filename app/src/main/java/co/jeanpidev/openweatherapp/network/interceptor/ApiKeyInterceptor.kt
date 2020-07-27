package co.jeanpidev.openweatherapp.network.interceptor

import co.jeanpidev.openweatherapp.BuildConfig
import co.jeanpidev.openweatherapp.utils.METRIC_SYSTEM
import co.jeanpidev.openweatherapp.utils.PARAM_APP_ID
import co.jeanpidev.openweatherapp.utils.PARAM_UNITS
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

class ApiKeyInterceptor() : Interceptor {
    @Throws(IOException::class) override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
        val originalHttpUrl = chain.request().url
        val url = originalHttpUrl.newBuilder().addQueryParameter(PARAM_APP_ID, BuildConfig.KeyAPI)
            .addQueryParameter(PARAM_UNITS, METRIC_SYSTEM).build()
        request.url(url)
        return chain.proceed(request.build())
    }
}
