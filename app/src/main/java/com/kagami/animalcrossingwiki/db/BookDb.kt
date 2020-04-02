package com.kagami.animalcrossingwiki.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [
    FishItem::class,
    InsectItem::class],
    version = 1,
    exportSchema = false
)
abstract class BookDb:RoomDatabase(){
    abstract fun fishDao():FishDao
    abstract fun insectDao():InsectDao
}