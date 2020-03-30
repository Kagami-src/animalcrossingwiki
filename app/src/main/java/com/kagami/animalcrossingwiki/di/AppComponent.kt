package com.kagami.animalcrossingwiki.di

import android.app.Application
import com.kagami.animalcrossingwiki.ACWApp
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [AndroidInjectionModule::class,AppModule::class,ActivityModule::class])
interface AppComponent {
    @Component.Builder
    interface Builder{
        @BindsInstance
        fun application(application: Application):Builder
        fun build():AppComponent
    }
    fun inject(app:ACWApp)
}