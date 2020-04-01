package com.kagami.animalcrossingwiki.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.kagami.animalcrossingwiki.R
import kotlinx.android.synthetic.main.item_fish.view.*
import kotlinx.android.synthetic.main.view_monthinterval.view.*
import kotlinx.android.synthetic.main.view_monthintervalitem.view.*
import timber.log.Timber

class MonthIntervalView : FrameLayout {
    constructor(ctx: Context) : super(ctx)
    constructor(ctx: Context, attrs: AttributeSet) : super(ctx, attrs)

    private val monthItems:Array<MonthIntervalItemView>
    init {
        LayoutInflater.from(context).inflate(R.layout.view_monthinterval, this, true)
        monthItems= arrayOf(m1,m2,m3,m4,m5,m6,m7,m8,m9,m10,m11,m12)
        monthItems.forEachIndexed { index, monthIntervalItemView ->
            monthIntervalItemView.textView.text="${index+1}"
        }
    }

    fun setMonths(north:String,south:String){
        Timber.d("kagamilog $north  $south")
        val northMonths = north.split(" ")
            .map { it.toIntOrNull() ?: 0 }
        val southMonths = south.split(" ")
            .map { it.toIntOrNull() ?: 0 }
        monthItems.forEach { it.clearBG() }
        monthItems.forEachIndexed { index, monthIntervalItemView ->
            if(northMonths.contains(index+1))
                monthIntervalItemView.setNorthBG()
            if(southMonths.contains(index+1))
                monthIntervalItemView.setSouthBG()
        }
    }
}
