package com.kagami.animalcrossingwiki.datasource

import androidx.annotation.WorkerThread
import com.kagami.animalcrossingwiki.datasource.model.FishDTO
import com.kagami.animalcrossingwiki.datasource.model.InsectDTO

interface WikiDataSource {
    @WorkerThread
    suspend fun fetchFishData():List<FishDTO>
    @WorkerThread
    suspend fun fetchInsectData():List<InsectDTO>
}