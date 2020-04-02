package com.kagami.animalcrossingwiki.view

import android.content.Context
import android.graphics.*
import android.text.TextPaint
import android.util.AttributeSet
import android.view.View
import com.kagami.animalcrossingwiki.global.splitAsIntList
import splitties.dimensions.dp
import timber.log.Timber
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.min
import kotlin.math.sin

class ClockView:View {
    constructor(ctx: Context) : super(ctx)
    constructor(ctx: Context, attrs: AttributeSet) : super(ctx, attrs)

    private val paint=Paint(Paint.ANTI_ALIAS_FLAG)
    private val textPaint=TextPaint()
    private val rectF=RectF()
    private val padding=dp(4)
    private val arcList= mutableListOf<Int>()
    private var currentTimeAngle:Float=0f
    init {
        paint.strokeWidth=dp(2)
        textPaint.textAlign= Paint.Align.CENTER
        textPaint.textSize=dp(10)
    }

    fun setTimeInterval(str:String,currentMinuOfDay:Int){
        currentTimeAngle=currentMinuOfDay/60f*15-90
        arcList.clear()
        str.splitAsIntList()
            .forEach {
                arcList.add(it*15-90)
            }
        invalidate()

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

        val textYOffset=(textPaint.descent() + textPaint.ascent()) / 2
        canvas.drawText("0",centerX,centerY-radius*0.6f-textYOffset,textPaint)
        canvas.drawText("6",centerX+radius*0.6f,centerY-textYOffset,textPaint)
        canvas.drawText("12",centerX,centerY+radius*0.6f-textYOffset,textPaint)
        canvas.drawText("18",centerX-radius*0.6f,centerY-textYOffset,textPaint)

        paint.color = 0x66ff0000.toInt()
        arcList.forEach {
            canvas.drawArc(rectF,it.toFloat(),15f,true,paint)
        }

        paint.color=Color.RED
        paint.style=Paint.Style.STROKE
        val cx= cos(currentTimeAngle/180.0*PI) * radius
        val cy= sin(currentTimeAngle/180.0*PI) * radius

        canvas.drawLine(centerX,centerY,centerX+cx.toFloat(),centerY+cy.toFloat(),paint)


    }

}