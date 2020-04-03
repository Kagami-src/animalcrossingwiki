package com.kagami.animalcrossingwiki.ui.insect

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.PopupMenu
import androidx.recyclerview.widget.RecyclerView
import com.kagami.animalcrossingwiki.R
import com.kagami.animalcrossingwiki.db.InsectItem
import com.kagami.animalcrossingwiki.global.loadBase64Image
import com.kagami.animalcrossingwiki.viewmodel.InsectListViewModel
import kotlinx.android.synthetic.main.item_fish.view.*

class InsectListAdapter(val viewModel:InsectListViewModel) : RecyclerView.Adapter<InsectListAdapter.ViewHolder>() {

    private val insertList= mutableListOf<InsectItem>()
    private var currentMinuOfDay:Int=0


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.item_insect, parent, false))
            .apply {
                itemView.setOnClickListener {
                    showMenu(it,data)
                }
            }
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
        holder.data=data
        holder.itemView.let {
            it.nameText.text=data.name
            it.placeText.text=data.place
            it.priceText.text=data.price.toString()
            it.sizeText.text=data.weather
            it.imageView.loadBase64Image(data.imageData)
            it.monthIntervalView.setMonths(data.month1,data.month2)
            it.clockView.setTimeInterval(data.interval,currentMinuOfDay)
            it.ownedTag.visibility = if(data.owned) View.VISIBLE else View.INVISIBLE
            it.donatedTag.visibility = if(data.donated) View.VISIBLE else View.INVISIBLE
        }
    }

    private fun showMenu(v:View,data: InsectItem){
        val popup= PopupMenu(v.context,v)
        popup.menuInflater.inflate(R.menu.popup_listitem,popup.menu)
        popup.setOnMenuItemClickListener {
            when(it.itemId){
                R.id.mark_owned ->{
                    data.run {
                        owned = !owned
                        viewModel.update(this)
                    }
                    notifyItemChanged(insertList.indexOf(data))
                    true
                }
                R.id.mark_donated ->{
                    data.run {
                        donated = !donated
                        viewModel.update(this)
                    }
                    notifyItemChanged(insertList.indexOf(data))
                    true
                }
                else -> false
            }
        }
        popup.show()
    }


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        lateinit var data:InsectItem
    }
}