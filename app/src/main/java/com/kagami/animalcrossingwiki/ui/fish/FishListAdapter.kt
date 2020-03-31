package com.kagami.animalcrossingwiki.ui.fish

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kagami.animalcrossingwiki.R
import com.kagami.animalcrossingwiki.db.FishItem
import com.kagami.animalcrossingwiki.global.loadBase64Image
import kotlinx.android.synthetic.main.item_fish.view.*

class FishListAdapter : RecyclerView.Adapter<FishListAdapter.ViewHolder>() {

    private val fislList= mutableListOf<FishItem>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.item_fish, parent, false)
        )
    }

    fun updateList(data:List<FishItem>){
        fislList.clear()
        fislList.addAll(data)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = fislList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data=fislList[position]
        holder.itemView.let {
            it.nameText.text=data.name
            it.imageView.loadBase64Image(data.imageData)
        }
    }


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    }
}