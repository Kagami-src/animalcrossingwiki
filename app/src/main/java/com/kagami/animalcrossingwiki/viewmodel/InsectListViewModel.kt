package com.kagami.animalcrossingwiki.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kagami.animalcrossingwiki.db.InsectDao
import com.kagami.animalcrossingwiki.db.InsectItem
import com.kagami.animalcrossingwiki.global.splitAsIntList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.*
import javax.inject.Inject

class InsectListViewModel @Inject constructor(private val insectDao: InsectDao): ViewModel(){
    private val _insectListLiveData: MutableLiveData<List<InsectItem>> = MutableLiveData()
    val insectListLiveData: LiveData<List<InsectItem>>
        get() = _insectListLiveData
    private val insectList= mutableListOf<InsectItem>()
    var filter:Filter= Filter(null,null,null,null)
        private set
    init {
        viewModelScope.launch {
            insectList.addAll(findAll())
            _insectListLiveData.postValue(insectList)
        }

    }
    private suspend fun findAll():List<InsectItem> = withContext(Dispatchers.Default){
        insectDao.findAll()
    }

    fun update(item:InsectItem){
        viewModelScope.async {
            insectDao.update(item)
        }
    }

    fun filter(filter: Filter){
        this.filter=filter
        viewModelScope.launch {
            _insectListLiveData.value=filterList(filter)
        }
    }
    private suspend fun filterList(filter: Filter) =
        withContext(Dispatchers.Default) {
            val month = Calendar.getInstance().get(Calendar.MONTH) + 1
            return@withContext insectList.filter { item ->
                var result = true
                val nmonth = item.month1.splitAsIntList()
                val smonth = item.month2.splitAsIntList()
                filter.north?.let {
                    result = result && nmonth.contains(month)
                }
                filter.south?.let {
                    result = result && smonth.contains(month)
                }
                filter.owned?.let {
                    result = result && it == item.owned
                }
                filter.donated?.let {
                    result = result && it == item.donated
                }
                result
            }
        }
    data class Filter(
        val north:Boolean?,
        val south:Boolean?,
        val owned:Boolean?,
        val donated:Boolean?
    )
}