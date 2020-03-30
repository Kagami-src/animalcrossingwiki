package com.kagami.animalcrossingwiki

import android.app.Activity
import android.app.Application
import com.kagami.animalcrossingwiki.di.AppInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import timber.log.Timber
import javax.inject.Inject

class ACWApp: Application() , HasActivityInjector {
    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Activity>
    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
        AppInjector.init(this)
    }
    override fun activityInjector() = dispatchingAndroidInjector
}