package com.yan.im.widget

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import com.yan.im.R
import org.jetbrains.anko.sp

/**
 *  @author      : 楠GG
 *  @date        : 2017/11/26 10:13
 *  @description : 联系人界面右侧索引条
 */
class SlideBar: View {

    /** 每一个字母占用的高度 */
    private var sectionHeight = 0f
    private var textBaseLine = 0f

    private val paint = Paint()

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    init {
        paint.apply {
            color = resources.getColor(R.color.qq_section_text_color)
            textSize = sp(12).toFloat()
            //文本居中
            textAlign = Paint.Align.CENTER
        }
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        sectionHeight = h* 1f / SECTIONS.size
        //字体矩阵
        val fontMetrics = paint.fontMetrics
        //计算绘制文本高度
        val textHeight = fontMetrics.descent - fontMetrics.ascent
        //计算基准线
        textBaseLine = sectionHeight/ 2 + (textHeight / 2 - fontMetrics.descent)
    }

    override fun onDraw(canvas: Canvas) {
        val x = width/2f
        var y = textBaseLine
        SECTIONS.forEach {
            canvas.drawText(it, x, y, paint)
            y += sectionHeight
        }
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        when (event.action) {
            MotionEvent.ACTION_DOWN -> setBackgroundResource(R.drawable.bg_slide_bar)
            MotionEvent.ACTION_UP -> setBackgroundColor(Color.TRANSPARENT)
        }
        return true
    }

    companion object {
        private val SECTIONS = arrayOf("A", "B", "C", "D", "E", "F", "G", "H", "I", "J",
                "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z")
    }
}