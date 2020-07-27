package co.jeanpidev.openweatherapp.ui.current

import androidx.lifecycle.ViewModel
import co.jeanpidev.openweatherapp.di.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
internal abstract class CurrentModule {
    @Binds
    @IntoMap
    @ViewModelKey(CurrentViewModel::class) abstract fun bindCurrentViewModel(
        viewModel: CurrentViewModel): ViewModel
}
