package com.kagami.animalcrossingwiki.datasource

import com.google.gson.GsonBuilder

object DataSourceUtil {
    suspend fun toFishJsonFromSource(source: WikiDataSource):String{
        val gson=GsonBuilder().create()
        return gson.toJson(source.fetchFishData())
    }
    suspend fun toInsectJsonFromSource(source: WikiDataSource):String{
        val gson=GsonBuilder().create()
        return gson.toJson(source.fetchInsectData())
    }
}