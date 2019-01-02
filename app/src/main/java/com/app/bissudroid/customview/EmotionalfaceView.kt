package com.app.bissudroid.customview

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.util.Log
import android.view.View

class EmotionalfaceView(context: Context, attrs: AttributeSet) : View(context, attrs) {
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val faceColor = Color.YELLOW
    private var eyesColor = Color.BLACK
    private var mouthColor = Color.BLACK
    private var borderColor = Color.BLACK
    // Face border width in pixels
    private var borderWidth = 4.0f
    // View size in pixels
    private var size = 320

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        drawFaceBackground(canvas)

    }

    private fun drawFaceBackground(canvas: Canvas?) {
        paint.color=faceColor
        paint.style=Paint.Style.FILL
        val  radius=size/2f
        canvas?.drawCircle(size/2f,size/2f,radius,paint)
        paint.color=borderColor
        paint.style=Paint.Style.STROKE
        paint.strokeWidth=borderWidth
        println(size/2f)
        println(radius-borderWidth/2f)
        canvas?.drawCircle(size/2f,size/2f,radius-borderWidth/2f,paint)

    }

    private fun drawEyes(canvas: Canvas) {
    }

    private fun drawMouth(canvas: Canvas) {
    }


}