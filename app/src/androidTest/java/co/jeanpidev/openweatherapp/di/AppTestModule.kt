package co.jeanpidev.openweatherapp.di

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppTestModule(private val mApplication: Application) {

    @Singleton
    @Provides
    internal fun provideApplication(): Application {
        return mApplication
    }

    @Singleton
    @Provides fun provideContext(): Context = mApplication.applicationContext
}
