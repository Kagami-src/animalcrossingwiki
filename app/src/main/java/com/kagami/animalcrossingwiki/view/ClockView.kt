package com.kagami.animalcrossingwiki.view

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import splitties.dimensions.dp
import kotlin.math.min

class ClockView:View {
    constructor(ctx: Context) : super(ctx)
    constructor(ctx: Context, attrs: AttributeSet) : super(ctx, attrs)

    private val paint=Paint(Paint.ANTI_ALIAS_FLAG)
    private val rectF=RectF()
    private val padding=dp(4)
    init {
        paint.strokeWidth=dp(2)
    }

    override fun onDraw(canvas: Canvas) {
        paint.style=Paint.Style.STROKE
        paint.color= Color.BLACK
        val radius=min(width/2f,height/2f)-padding
        val centerX=width/2f
        val centerY=height/2f
        rectF.left=centerX-radius
        rectF.top=centerY-radius
        rectF.right=centerX+radius
        rectF.bottom=centerY+radius  
        canvas.drawCircle(centerX,centerY, radius,paint)
//        canvas.drawLine(centerX,centerY-radius,centerX,centerY-radius*0.8f,paint)
//        canvas.drawLine(centerX,centerY+radius,centerX,centerY+radius*0.8f,paint)
//        canvas.drawLine(centerX-radius,centerY,centerX-radius*0.8f,centerY,paint)
//        canvas.drawLine(centerX+radius,centerY,centerX+radius*0.8f,centerY,paint)


        paint.style=Paint.Style.FILL
        for(i in 0 until 360 step 30){
            canvas.drawArc(rectF,i-0.5f,1f,true,paint)
        }
        paint.color = Color.WHITE
        canvas.drawCircle(centerX,centerY, radius*0.9f,paint)
        paint.color= Color.BLACK
        paint.style=Paint.Style.FILL
        for(i in 0 until 360 step 90){
            canvas.drawArc(rectF,i-1f,2f,true,paint)
        }
        paint.color = Color.WHITE
        canvas.drawCircle(centerX,centerY, radius*0.8f,paint)
        //canvas.drawArc(rectF,0f,30f,true,paint)
    }

}