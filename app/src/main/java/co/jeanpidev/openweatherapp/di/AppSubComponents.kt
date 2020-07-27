package co.jeanpidev.openweatherapp.di

import co.jeanpidev.openweatherapp.ui.current.CurrentComponent
import co.jeanpidev.openweatherapp.ui.search.SearchComponent
import dagger.Module

@Module(subcomponents = [CurrentComponent::class, SearchComponent::class])
class AppSubComponents
