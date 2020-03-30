package com.kagami.animalcrossingwiki.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface FishDao{
    @Insert
    fun insert(item:FishItem)
    @Query("select * from fishitem")
    fun findAll():List<FishItem>
}