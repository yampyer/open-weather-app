package co.jeanpidev.openweatherapp.di

import android.content.Context
import co.jeanpidev.openweatherapp.application.App
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Defines all the classes that need to be provided in the scope of the app (e.g Helpers).
 */
@Module
open class AppModule(private val application: App) {

    @Provides @Singleton fun provideApp() = application

    @Singleton @Provides fun provideContext(): Context = application.applicationContext
}
