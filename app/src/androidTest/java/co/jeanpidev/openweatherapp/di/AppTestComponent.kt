package co.jeanpidev.openweatherapp.di

import co.jeanpidev.openweatherapp.network.NetworkTestModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppTestModule::class, ViewModelModule::class, NetworkTestModule::class, AppSubComponents::class])
interface AppTestComponent : AppComponent
