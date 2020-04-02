package com.kagami.animalcrossingwiki.di

import com.kagami.animalcrossingwiki.ui.fish.FishListFragment
import com.kagami.animalcrossingwiki.ui.insect.InsectListFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBuildersModule {
    @ContributesAndroidInjector
    abstract fun contributeFishListFragment(): FishListFragment
    @ContributesAndroidInjector
    abstract fun contributeInsectListFragment(): InsectListFragment
}