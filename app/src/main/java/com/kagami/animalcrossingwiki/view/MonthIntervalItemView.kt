package com.kagami.animalcrossingwiki.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.kagami.animalcrossingwiki.R
import kotlinx.android.synthetic.main.view_monthintervalitem.view.*

class MonthIntervalItemView: FrameLayout {
    constructor(ctx: Context) : super(ctx)
    constructor(ctx: Context, attrs: AttributeSet) : super(ctx, attrs)
    init {
        LayoutInflater.from(context).inflate(R.layout.view_monthintervalitem, this, true)
    }

    fun setNorthBG(){
        northView.setBackgroundResource(R.drawable.shape_month1bg)
    }
    fun setSouthBG(){
        southView.setBackgroundResource(R.drawable.shape_month2bg)
    }
    fun clearBG(){
        northView.background=null
        southView.background=null
    }

}