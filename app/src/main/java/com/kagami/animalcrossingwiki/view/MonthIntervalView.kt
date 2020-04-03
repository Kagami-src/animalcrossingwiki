package com.kagami.animalcrossingwiki.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.kagami.animalcrossingwiki.R
import com.kagami.animalcrossingwiki.global.splitAsIntList
import kotlinx.android.synthetic.main.item_fish.view.*
import kotlinx.android.synthetic.main.view_monthinterval.view.*
import kotlinx.android.synthetic.main.view_monthintervalitem.view.*
import timber.log.Timber

//性能较差 待优化
class MonthIntervalView : FrameLayout {
    constructor(ctx: Context) : super(ctx)
    constructor(ctx: Context, attrs: AttributeSet) : super(ctx, attrs)

    private val monthItems:Array<MonthIntervalItemView>
    init {
        LayoutInflater.from(context).inflate(R.layout.view_monthinterval, this, true)
        monthItems= arrayOf(m1,m2,m3,m4,m5,m6,m7,m8,m9,m10,m11,m12)
        monthItems.forEachIndexed { index, monthIntervalItemView ->
            monthIntervalItemView.setText("${index+1}")
        }
    }

    fun setMonths(north:String,south:String){
        val northMonths = north.splitAsIntList()
        val southMonths = south.splitAsIntList()
        monthItems.forEach { it.clearBG() }
        monthItems.forEachIndexed { index, monthIntervalItemView ->
            if(northMonths.contains(index+1))
                monthIntervalItemView.setNorthBG()
            if(southMonths.contains(index+1))
                monthIntervalItemView.setSouthBG()
        }
    }
}
