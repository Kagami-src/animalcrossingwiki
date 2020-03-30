package com.kagami.animalcrossingwiki.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.kagami.animalcrossingwiki.viewmodel.ACWViewModelFactory
import com.kagami.animalcrossingwiki.viewmodel.FishListViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(FishListViewModel::class)
    abstract fun bindFishListViewModel(fishListViewModel: FishListViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: ACWViewModelFactory): ViewModelProvider.Factory
}