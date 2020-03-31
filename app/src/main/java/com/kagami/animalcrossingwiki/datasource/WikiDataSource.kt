package com.kagami.animalcrossingwiki.datasource

import androidx.annotation.WorkerThread

interface WikiDataSource {
    @WorkerThread
    suspend fun fetchFishData():List<FishDTO>
}