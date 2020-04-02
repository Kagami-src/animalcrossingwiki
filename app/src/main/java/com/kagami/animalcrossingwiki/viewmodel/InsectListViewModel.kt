package com.kagami.animalcrossingwiki.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kagami.animalcrossingwiki.db.FishDao
import com.kagami.animalcrossingwiki.db.FishItem
import com.kagami.animalcrossingwiki.db.InsectDao
import com.kagami.animalcrossingwiki.db.InsectItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class InsectListViewModel @Inject constructor(private val insectDao: InsectDao): ViewModel(){
    private val _insectListLiveData: MutableLiveData<List<InsectItem>> = MutableLiveData()
    val insectListLiveData: LiveData<List<InsectItem>>
        get() = _insectListLiveData
    private val insectList= mutableListOf<InsectItem>()
    init {
        viewModelScope.launch {
            insectList.addAll(findAll())
            _insectListLiveData.postValue(insectList)
        }

    }
    private suspend fun findAll():List<InsectItem> = withContext(Dispatchers.Default){
        insectDao.findAll()
    }
}