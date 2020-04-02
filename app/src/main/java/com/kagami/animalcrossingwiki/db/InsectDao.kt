package com.kagami.animalcrossingwiki.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.kagami.animalcrossingwiki.datasource.model.InsectDTO

@Dao
abstract class InsectDao{
    @Insert
    abstract fun insert(item:InsectItem)
    @Update
    abstract fun update(item:InsectItem)
    @Insert
    abstract fun insertAll(items:List<InsectItem>)
    @Query("select * from insectitem order by price desc")
    abstract fun findAll():List<InsectItem>
    @Query("select COUNT(*) from insectitem")
    abstract fun count():Int

    fun insertAllTDO(tdos:List<InsectDTO>){
        tdos.map {
            InsectItem().apply {
                name=it.name
                place=it.place
                weather=it.weather
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