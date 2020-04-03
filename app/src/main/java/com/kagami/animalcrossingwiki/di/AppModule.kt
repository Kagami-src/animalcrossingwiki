package com.kagami.animalcrossingwiki.di

import android.app.Application
import androidx.room.Room
import com.kagami.animalcrossingwiki.db.BookDb
import com.kagami.animalcrossingwiki.db.FishDao
import com.kagami.animalcrossingwiki.db.InsectDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [ViewModelModule::class])
class AppModule {
    @Singleton
    @Provides
    fun provideDb(app: Application): BookDb {
        return Room
            .databaseBuilder(app, BookDb::class.java, "book.db")
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideFishDao(db: BookDb): FishDao {
        return db.fishDao()
    }
    @Singleton
    @Provides
    fun provideInsectDao(db: BookDb): InsectDao {
        return db.insectDao()
    }
}