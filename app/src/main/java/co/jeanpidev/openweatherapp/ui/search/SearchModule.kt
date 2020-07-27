package co.jeanpidev.openweatherapp.ui.search

import androidx.lifecycle.ViewModel
import co.jeanpidev.openweatherapp.di.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
internal abstract class SearchModule {
    @Binds
    @IntoMap
    @ViewModelKey(SearchViewModel::class) abstract fun bindSearchViewModel(
        viewModel: SearchViewModel): ViewModel
}
