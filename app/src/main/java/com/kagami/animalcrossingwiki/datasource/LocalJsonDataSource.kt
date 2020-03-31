package com.kagami.animalcrossingwiki.datasource

import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import org.apache.commons.io.IOUtils
import java.io.InputStream

class LocalJsonDataSource(val fishIS:InputStream):WikiDataSource {
    override suspend fun fetchFishData(): List<FishDTO> {
        val gson = GsonBuilder().create()
        val json=IOUtils.toString(fishIS,"utf-8")
        return gson.fromJson(json,Array<FishDTO>::class.java).asList()
    }
}