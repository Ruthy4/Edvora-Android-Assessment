package com.example.edvoraassessment

import android.content.Context
import android.graphics.*
import android.graphics.drawable.shapes.Shape
import android.util.AttributeSet
import android.view.DragEvent
import android.view.MotionEvent
import android.view.View
import android.view.ViewConfiguration
import androidx.core.content.res.ResourcesCompat
import kotlin.math.abs


private const val stroke_width = 3f
var canvasState = "canvasState"
const val PENCIL = "canvasState"
const val ARROW = "arrow"
const val CIRCLE = "circle"
const val SQUARE = "square"


class CanvasView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    //background color
    private val backgroundColor = ResourcesCompat.getColor(resources, R.color.white, null)
    private val touchTolerance = ViewConfiguration.get(context).scaledEdgeSlop
    private lateinit var firstCanvas: Canvas
    private lateinit var firstBitmap: Bitmap
    var radius = 8f

    //pen color
    private val penColor = ResourcesCompat.getColor(resources, R.color.black, null)

    //paint properties
    private val paint = Paint().apply {
        color = penColor
        isAntiAlias = true
        isDither = true
        style = Paint.Style.STROKE
        strokeWidth = stroke_width
        strokeCap = Paint.Cap.ROUND
        strokeJoin = Paint.Join.ROUND
    }

    private var path = Path()
    private var motionX = 0f
    private var motionY = 0f
    private var currentX = 0f
    private var currentY = 0f

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)

        if (::firstBitmap.isInitialized) firstBitmap.recycle() //recycle bitmap to prevent memory leak

        firstBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888)
        firstCanvas = Canvas(firstBitmap)
        firstCanvas.drawColor(backgroundColor)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        when(canvasState) {
            PENCIL -> {
                invalidate()
                canvas?.drawBitmap(firstBitmap, 0f, 0f, null)
            }
            ARROW -> {
                invalidate()
                canvas?.drawBitmap(firstBitmap, 0f, 0f, null)
            }
            SQUARE -> {
                invalidate()
                canvas?.drawRect(100f, 50f, 300f, 300f, paint)
                canvas?.drawRect(250f, 600f, 500f, 800f, paint)
                canvas?.drawRect(210f, 500f, 500f, 400f, paint)
            }
            CIRCLE -> {
                invalidate()
                canvas?.drawCircle(280f, 700f, 100f, paint)
                canvas?.drawCircle(480f, 800f, 50f, paint)
                canvas?.drawOval(75f, 500f, 150f, 100f, paint)
            }
        }
    }

    fun setColor(color: Int){
        paint.color = color
        invalidate()
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {

        motionX = event!!.x
        motionY = event.y

        when (event.action) {
            MotionEvent.ACTION_DOWN -> touchStart()
            MotionEvent.ACTION_UP -> touchUp()
            MotionEvent.ACTION_MOVE -> touchMove()
        }
        return true
    }

    private fun touchMove() {
        val dx = abs(motionX - currentX)
        val dy = abs(motionY - currentY)
        if (dx >= touchTolerance || dy >= touchTolerance) {
            path.quadTo(currentX, currentY, (motionX + currentX) / 2, (motionY + currentY) / 2)
            currentX = motionX
            currentY = motionY
            firstCanvas.drawPath(path, paint)
        }
        invalidate()
    }

    private fun touchUp() {
        path.reset()
    }

    private fun touchStart() {
        path.reset()
        path.moveTo(motionX, motionY)
        currentX = motionX
        currentY = motionY
    }
}