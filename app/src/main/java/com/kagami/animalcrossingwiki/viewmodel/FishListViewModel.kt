package com.kagami.animalcrossingwiki.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kagami.animalcrossingwiki.db.FishDao
import com.kagami.animalcrossingwiki.db.FishItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class FishListViewModel @Inject constructor(private val fishDao: FishDao):ViewModel(){
    private val _fishListLiveData: MutableLiveData<List<FishItem>> = MutableLiveData()
    val fishListLiveData: LiveData<List<FishItem>>
        get() = _fishListLiveData
    private val fishList= mutableListOf<FishItem>()
    init {
        viewModelScope.launch {
            fishList.addAll(findAll())
            _fishListLiveData.value=fishList
        }

    }
    private suspend fun findAll():List<FishItem> = withContext(Dispatchers.Default){
        fishDao.findAll()
    }
}