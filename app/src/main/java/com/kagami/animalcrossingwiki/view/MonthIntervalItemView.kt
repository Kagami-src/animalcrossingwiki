package com.kagami.animalcrossingwiki.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.graphics.drawable.Drawable
import android.text.TextPaint
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import com.kagami.animalcrossingwiki.R
import kotlinx.android.synthetic.main.view_monthintervalitem.view.*
import splitties.dimensions.dp

class MonthIntervalItemView: View {
    constructor(ctx: Context) : super(ctx)
    constructor(ctx: Context, attrs: AttributeSet) : super(ctx, attrs)
    private var text:String = ""
    private val textPaint=TextPaint(Paint.ANTI_ALIAS_FLAG)
    //private val paint=Paint(Paint.ANTI_ALIAS_FLAG)
    var isCurrent=false
    private val northBG:Drawable
    private val viewBG:Drawable
    private val currentMonthBG:Drawable
    private val southBG:Drawable
    private var drawNorthBG=false
    private var drawSouthBG=false
    init {
        textPaint.textAlign= Paint.Align.CENTER
        textPaint.textSize=dp(11)
        northBG = resources.getDrawable(R.drawable.shape_month1bg,null)
        southBG = resources.getDrawable(R.drawable.shape_month2bg,null)
        viewBG = resources.getDrawable(R.drawable.shape_monthbg,null)
        currentMonthBG = resources.getDrawable(R.drawable.shape_currentmonth,null)
        //LayoutInflater.from(context).inflate(R.layout.view_monthintervalitem, this, true)
    }

    fun setText(text:String){
        this.text=text
    }
    fun setNorthBG(){
        drawNorthBG=true
        //northView.visibility=View.VISIBLE
    }
    fun setSouthBG(){
        drawSouthBG=true
        //southView.visibility=View.VISIBLE
    }
    fun clearBG(){
        drawNorthBG=false
        drawSouthBG=false
        //northView.visibility= View.INVISIBLE
        //southView.visibility= View.INVISIBLE
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        val padding=dp(2).toInt()
        val space=dp(1).toInt()
        viewBG.bounds = Rect(padding,padding,w-padding,h-padding)
        currentMonthBG.bounds = Rect(padding,padding,w-padding,h-padding)
        northBG.bounds = Rect(padding,padding,w-padding,h/2-space)
        southBG.bounds = Rect(padding,h/2+space,w-padding,h-padding)
    }
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        viewBG.draw(canvas)
        if(drawNorthBG)
            northBG.draw(canvas)
        if(drawSouthBG)
            southBG.draw(canvas)
        if(isCurrent)
            currentMonthBG.draw(canvas)
        val textYOffset=(textPaint.descent() + textPaint.ascent()) / 2
        canvas.drawText(text,width/2f,height/2f-textYOffset,textPaint)
    }

}