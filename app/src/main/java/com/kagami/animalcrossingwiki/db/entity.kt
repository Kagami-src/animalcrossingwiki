package com.kagami.animalcrossingwiki.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "fishitem")
class FishItem{
    @PrimaryKey(autoGenerate = true)
    var id=0L
    var name=""
    var place=""
    var size=""
    var month1=""
    var month2=""
    var interval=""
    var price=0
    var imagePath=""
    var imageData=""
}