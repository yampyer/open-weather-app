package co.jeanpidev.openweatherapp.application

import android.app.Application
import co.jeanpidev.openweatherapp.di.AppComponent
import co.jeanpidev.openweatherapp.di.AppModule
import co.jeanpidev.openweatherapp.di.DaggerAppComponent

open class App : Application() {

    val component: AppComponent by lazy {
        initializeComponent()
    }

    open fun initializeComponent(): AppComponent =
        DaggerAppComponent.builder().appModule(AppModule(this)).build()
}
