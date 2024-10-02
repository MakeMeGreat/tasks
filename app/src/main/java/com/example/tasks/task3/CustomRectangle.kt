package com.example.tasks.task3

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import kotlin.random.Random

class CustomRectangle @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAtt: Int = 0
) : View(context, attrs, defStyleAtt) {

    private val paint = Paint()

    private var fillPart: Float = 1.0F

    init {
        paint.color = getRandomColor()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        val fillHeight = height * fillPart

        canvas.drawRect(0f, height - fillHeight, width.toFloat(), height.toFloat(), paint)
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        if (event != null) {
            if (event.action == MotionEvent.ACTION_DOWN) {
                if (fillPart >= 1.0F) fillPart = 0.0F
                fillPart += 0.1F
                paint.color = getRandomColor()
                invalidate()
                requestLayout()
                return true
            }
        }
        return super.onTouchEvent(event)
    }

    private fun getRandomColor(): Int {
        val red = Random.nextInt(256)
        val green = Random.nextInt(256)
        val blue = Random.nextInt(256)
        return Color.rgb(red, green, blue)
    }
}