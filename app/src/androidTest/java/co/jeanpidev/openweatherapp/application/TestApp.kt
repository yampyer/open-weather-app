package co.jeanpidev.openweatherapp.application

import co.jeanpidev.openweatherapp.di.AppComponent
import co.jeanpidev.openweatherapp.di.AppTestModule
import co.jeanpidev.openweatherapp.di.DaggerAppTestComponent

class TestsApp : App() {

    override fun initializeComponent(): AppComponent {
        return DaggerAppTestComponent.builder().appTestModule(AppTestModule(this)).build()
    }
}
