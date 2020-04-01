package com.kagami.animalcrossingwiki.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
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
        northView.visibility=View.VISIBLE
    }
    fun setSouthBG(){
        southView.visibility=View.VISIBLE
    }
    fun clearBG(){
        northView.visibility= View.INVISIBLE
        southView.visibility= View.INVISIBLE
    }

}