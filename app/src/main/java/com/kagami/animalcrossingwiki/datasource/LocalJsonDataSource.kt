package com.kagami.animalcrossingwiki.datasource

import com.google.gson.GsonBuilder
import com.kagami.animalcrossingwiki.datasource.model.FishDTO
import com.kagami.animalcrossingwiki.datasource.model.InsectDTO
import org.apache.commons.io.IOUtils
import java.io.InputStream

class LocalJsonDataSource(val fishIS:InputStream,val insectIS:InputStream):WikiDataSource {
    override suspend fun fetchFishData(): List<FishDTO> {
        val gson = GsonBuilder().create()
        val json=IOUtils.toString(fishIS,"utf-8")
        return gson.fromJson(json,Array<FishDTO>::class.java).asList()
    }

    override suspend fun fetchInsectData(): List<InsectDTO> {
        val gson = GsonBuilder().create()
        val json=IOUtils.toString(insectIS,"utf-8")
        return gson.fromJson(json,Array<InsectDTO>::class.java).asList()
    }
}