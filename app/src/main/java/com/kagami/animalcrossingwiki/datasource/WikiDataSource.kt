package com.kagami.animalcrossingwiki.datasource

import androidx.annotation.WorkerThread
import com.kagami.animalcrossingwiki.datasource.model.FishDTO

interface WikiDataSource {
    @WorkerThread
    suspend fun fetchFishData():List<FishDTO>
}