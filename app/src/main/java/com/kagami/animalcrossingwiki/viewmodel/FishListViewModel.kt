package com.kagami.animalcrossingwiki.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kagami.animalcrossingwiki.db.FishDao
import com.kagami.animalcrossingwiki.db.FishItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.*
import javax.inject.Inject

class FishListViewModel @Inject constructor(private val fishDao: FishDao):ViewModel(){
    private val _fishListLiveData: MutableLiveData<List<FishItem>> = MutableLiveData()
    val fishListLiveData: LiveData<List<FishItem>>
        get() = _fishListLiveData
    private val fishList= mutableListOf<FishItem>()
    var filter:Filter= Filter(null,null,null,null)
        private set

    init {
        viewModelScope.launch {
            fishList.addAll(findAll())
            _fishListLiveData.postValue(fishList)
        }

    }
    private suspend fun findAll():List<FishItem> = withContext(Dispatchers.Default){
        fishDao.findAll()
    }

    fun update(item:FishItem){
        viewModelScope.async {
            fishDao.update(item)
        }
    }

    fun filter(filter:Filter){
        val month= Calendar.getInstance().get(Calendar.MONTH)
        fishList.filter {
            var result=true
            filter.north?.let {
                
            }
        }
    }
    data class Filter(
        val north:Boolean?,
        val south:Boolean?,
        val owned:Boolean?,
        val donated:Boolean?
    )
}