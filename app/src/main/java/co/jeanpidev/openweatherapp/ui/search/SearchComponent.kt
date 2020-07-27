package co.jeanpidev.openweatherapp.ui.search

import co.jeanpidev.openweatherapp.di.ActivityScoped
import dagger.Subcomponent

@ActivityScoped
@Subcomponent(modules = [SearchModule::class])
interface SearchComponent {
    @Subcomponent.Factory
    interface Factory {
        fun create(): SearchComponent
    }

    fun inject(activity: SearchActivity)
}
