package co.jeanpidev.openweatherapp.di

import co.jeanpidev.openweatherapp.base.BaseActivity
import co.jeanpidev.openweatherapp.network.NetworkModule
import co.jeanpidev.openweatherapp.ui.current.CurrentComponent
import co.jeanpidev.openweatherapp.ui.search.SearchComponent
import dagger.Component
import javax.inject.Singleton

/**
 * Main component of the app, created and persisted in the Application class. All modules need to be added here.
 */
@Singleton
@Component(modules = [AppModule::class, ViewModelModule::class, NetworkModule::class, AppSubComponents::class])
    interface AppComponent {

    fun currentComponent(): CurrentComponent.Factory
    fun searchComponent(): SearchComponent.Factory
    fun inject(activity: BaseActivity)
}
