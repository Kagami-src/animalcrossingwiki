package com.kagami.animalcrossingwiki.di

import com.kagami.animalcrossingwiki.ui.fish.FishListFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBuildersModule {
    @ContributesAndroidInjector
    abstract fun contributeFishListFragment(): FishListFragment
}