package com.kagami.animalcrossingwiki.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.kagami.animalcrossingwiki.datasource.model.FishDTO

@Dao
abstract class FishDao{
    @Insert
    abstract fun insert(item:FishItem)
    @Update
    abstract fun update(item:FishItem)
    @Insert
    abstract fun insertAll(items:List<FishItem>)
    @Query("select * from fishitem order by price desc")
    abstract fun findAll():List<FishItem>
    @Query("select COUNT(*) from fishitem")
    abstract fun count():Int

    fun insertAllTDO(tdos:List<FishDTO>){
        tdos.map {
            FishItem().apply {
                name=it.name
                place=it.place
                size=it.size
                month1=it.month1
                month2=it.month2
                interval=it.interval
                price=it.price
                imagePath=it.imagePath
                imageData=it.imageData
            }
        }.let {
            insertAll(it)
        }
    }
}