package com.kagami.animalcrossingwiki.ui.insect

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kagami.animalcrossingwiki.R
import com.kagami.animalcrossingwiki.db.FishItem
import com.kagami.animalcrossingwiki.db.InsectItem
import com.kagami.animalcrossingwiki.global.loadBase64Image
import kotlinx.android.synthetic.main.item_fish.view.*
import timber.log.Timber
import java.util.*

class InsectListAdapter : RecyclerView.Adapter<InsectListAdapter.ViewHolder>() {

    private val insertList= mutableListOf<InsectItem>()
    private var currentMinuOfDay:Int=0


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.item_insect, parent, false)
        )
    }

    fun updateList(data:List<InsectItem>){
        insertList.clear()
        insertList.addAll(data)
        notifyDataSetChanged()
    }
    fun setCurrentTime(minuOfDay:Int,month:Int){
        currentMinuOfDay=minuOfDay
    }

    override fun getItemCount(): Int = insertList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data=insertList[position]
        holder.itemView.let {
            it.nameText.text=data.name
            it.placeText.text=data.place
            it.priceText.text=data.price.toString()
            it.sizeText.text=data.weather
            it.imageView.loadBase64Image(data.imageData)
            it.monthIntervalView.setMonths(data.month1,data.month2)
            it.clockView.setTimeInterval(data.interval,currentMinuOfDay)
        }
    }


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    }
}