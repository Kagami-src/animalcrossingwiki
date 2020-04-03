package com.kagami.animalcrossingwiki.view

import android.content.Context
import android.util.AttributeSet
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.kagami.animalcrossingwiki.R
import com.kagami.animalcrossingwiki.viewmodel.FishListViewModel
import com.kagami.animalcrossingwiki.viewmodel.InsectListViewModel
import kotlinx.android.synthetic.main.bottomsheet_filter.*

class FilterBottomSheetDialog: BottomSheetDialog {
    constructor(ctx: Context) : super(ctx)

    init {
        setContentView(R.layout.bottomsheet_filter)
    }

    fun setupFishFilter(filter:FishListViewModel.Filter){
        if(filter.north == true)
            monthNorth.isChecked=true
        if(filter.south == true)
            monthSouth.isChecked=true
        if(filter.owned == true)
            ownedTure.isChecked=true
        if(filter.owned == false)
            ownedFalse.isChecked=true
        if(filter.donated == true)
            donatedTrue.isChecked = true
        if(filter.donated == false)
            donatedFalse.isChecked = true
    }
    fun onFishFilterApply(cb:(FishListViewModel.Filter)->Unit){
        applyButton.setOnClickListener {
            var north:Boolean?=null
            var south:Boolean?=null
            var owned:Boolean?=null
            var donated:Boolean?=null
            if(monthNorth.isChecked)
                north=true
            if(monthSouth.isChecked)
                south=true
            if(ownedTure.isChecked)
                owned=true
            if(ownedFalse.isChecked)
                owned=false
            if(donatedTrue.isChecked)
                donated=true
            if(donatedFalse.isChecked)
                donated=false
            cb(FishListViewModel.Filter(north, south, owned, donated))
        }

    }

    fun setupInsectFilter(filter: InsectListViewModel.Filter){
        if(filter.north == true)
            monthNorth.isChecked=true
        if(filter.south == true)
            monthSouth.isChecked=true
        if(filter.owned == true)
            ownedTure.isChecked=true
        if(filter.owned == false)
            ownedFalse.isChecked=true
        if(filter.donated == true)
            donatedTrue.isChecked = true
        if(filter.donated == false)
            donatedFalse.isChecked = true
    }
    fun onInsectFilterApply(cb:(InsectListViewModel.Filter)->Unit){
        applyButton.setOnClickListener {
            var north:Boolean?=null
            var south:Boolean?=null
            var owned:Boolean?=null
            var donated:Boolean?=null
            if(monthNorth.isChecked)
                north=true
            if(monthSouth.isChecked)
                south=true
            if(ownedTure.isChecked)
                owned=true
            if(ownedFalse.isChecked)
                owned=false
            if(donatedTrue.isChecked)
                donated=true
            if(donatedFalse.isChecked)
                donated=false
            cb(InsectListViewModel.Filter(north, south, owned, donated))
        }

    }
}