package com.app.bissudroid.customview

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.util.Log
import android.view.View

class EmotionalfaceView(context: Context, attrs: AttributeSet) : View(context, attrs) {
    companion object {
        private const val DEFAULT_FACE_COLOR = Color.YELLOW
        private const val DEFAULT_EYES_COLOR = Color.BLACK
        private const val DEFAULT_MOUTH_COLOR = Color.BLACK
        private const val DEFAULT_BORDER_COLOR = Color.BLACK
        private const val DEFAULT_BORDER_WIDTH = 4.0f

        const val HAPPY = 0L
        const val SAD = 1L
    }

    private val paint = Paint()
    private var faceColor = DEFAULT_FACE_COLOR
    private var eyesColor = DEFAULT_EYES_COLOR
    private var mouthColor = DEFAULT_MOUTH_COLOR
    private var borderColor = DEFAULT_BORDER_COLOR
    // Face border width in pixels
    private var borderWidth = DEFAULT_BORDER_WIDTH
    // View size in pixels
    private var size = 0
    private val mouthPath = Path()
    var happinessState = HAPPY
        set(value) {
            field = value
            invalidate()
        }

    init {
        paint.isAntiAlias = true
        setupAttributes(attrs)

    }

    private fun setupAttributes(attrs: AttributeSet?) {
        // 6
        // Obtain a typed array of attributes
        val typedArray = context.theme.obtainStyledAttributes(
            attrs, R.styleable.EmotionalfaceView,
            0, 0
        )

        // 7
        // Extract custom attributes into member variables
        happinessState = typedArray.getInt(R.styleable.EmotionalfaceView_state, HAPPY.toInt()).toLong()
        faceColor = typedArray.getColor(R.styleable.EmotionalfaceView_faceColor, DEFAULT_FACE_COLOR)
        eyesColor = typedArray.getColor(R.styleable.EmotionalfaceView_eyesColor, DEFAULT_EYES_COLOR)
        mouthColor = typedArray.getColor(R.styleable.EmotionalfaceView_mouthColor, DEFAULT_MOUTH_COLOR)
        borderColor = typedArray.getColor(
            R.styleable.EmotionalfaceView_borderColor,
            DEFAULT_BORDER_COLOR
        )
        borderWidth = typedArray.getDimension(
            R.styleable.EmotionalfaceView_borderWidth,
            DEFAULT_BORDER_WIDTH
        )

        // 8
        // TypedArray objects are shared and must be recycled.
        typedArray.recycle()
    }


    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        drawFaceBackground(canvas)
        drawEyes(canvas)
        drawMouth(canvas)

    }

    private fun drawFaceBackground(canvas: Canvas?) {
        paint.color = faceColor
        paint.style = Paint.Style.FILL
        val radius = size / 2f
        canvas?.drawCircle(size / 2f, size / 2f, radius, paint)
        paint.color = borderColor
        paint.style = Paint.Style.STROKE
        paint.strokeWidth = borderWidth
        println(size / 2f)
        println(radius - borderWidth / 2f)
        canvas?.drawCircle(size / 2f, size / 2f, radius - borderWidth / 2f, paint)

    }

    private fun drawEyes(canvas: Canvas?) {
        paint.color = eyesColor
        paint.style = Paint.Style.FILL
        val lefteyerect = RectF(size * 0.32f, size * 0.23f, size * 0.43f, size * 0.5f)
        println(lefteyerect.left)
        println(lefteyerect.right)
        println(lefteyerect.top)
        println(lefteyerect.bottom)
        canvas?.drawOval(lefteyerect, paint)
        val rightEyeRect = RectF(size * 0.57f, size * 0.23f, size * 0.68f, size * 0.50f)

        canvas?.drawOval(rightEyeRect, paint)


    }

    private fun drawMouth(canvas: Canvas?) {
        mouthPath.reset()
        //1
        Log.d("coordinates", " " + size * 0.22f + " " + size * 0.7f)
        mouthPath.moveTo(size * 0.22f, size * 0.7f)
// 2

        if (happinessState == HAPPY) {
            // 1
            mouthPath.quadTo(size * 0.5f, size * 0.80f, size * 0.78f, size * 0.7f)
            mouthPath.quadTo(size * 0.5f, size * 0.90f, size * 0.22f, size * 0.7f)
        } else {
            // 2
            mouthPath.quadTo(size * 0.5f, size * 0.50f, size * 0.78f, size * 0.7f)
            mouthPath.quadTo(size * 0.5f, size * 0.60f, size * 0.22f, size * 0.7f)
        }

// 4
        paint.color = mouthColor
        paint.style = Paint.Style.FILL
// 5
        canvas?.drawPath(mouthPath, paint)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        size = Math.min(measuredWidth, measuredHeight)
        setMeasuredDimension(size, size)
    }


}