package co.jeanpidev.openweatherapp.ui.current

import co.jeanpidev.openweatherapp.di.ActivityScoped
import dagger.Subcomponent

@ActivityScoped
@Subcomponent(modules = [CurrentModule::class])
interface CurrentComponent {
    @Subcomponent.Factory
    interface Factory {
        fun create(): CurrentComponent
    }

    fun inject(activity: CurrentActivity)
}
