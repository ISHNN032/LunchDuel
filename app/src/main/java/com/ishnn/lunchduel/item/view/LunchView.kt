package com.ishnn.lunchduel.item.view

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.util.Log
import android.view.View
import com.ishnn.lunchduel.R
import kotlinx.coroutines.*
import java.io.IOException
import java.lang.Exception
import java.net.HttpURLConnection
import java.net.URL

class LunchView : View {
    private var imageURL: String? = null
    private var lunchName: String? = null
    private var lunchNameColor = 0
    private var lunchVote = 0

    constructor(context: Context?) : super(context) {}
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        val array = context.theme.obtainStyledAttributes(attrs, R.styleable.LunchView, 0, 0)
        try {
            //this.imageURL = array.getString(R.styleable.LunchView_imageURL);
            imageURL =
                "https://firebasestorage.googleapis.com/v0/b/lunchduel.appspot.com/o/menu_image%2Fchicken.jpg?alt=media&token=952282bf-0f04-4f40-87a7-81f78ecb10c1"
            lunchName = array.getString(R.styleable.LunchView_lunchName)
            lunchNameColor = array.getColor(0, Color.WHITE)
            lunchVote = array.getInteger(R.styleable.LunchView_lunchVote, 0)
        } finally {
        }
    }

    fun getImageURL(): String? {
        return imageURL
    }

    fun setImageURL(imageURL: String?) {
        this.imageURL = imageURL
        invalidate()
        requestLayout()
    }

    fun getLunchName(): String? {
        return lunchName
    }

    fun setLunchName(lunchName: String?) {
        this.lunchName = lunchName
        invalidate()
        requestLayout()
    }

    fun getLunchNameColor(): Int? {
        return lunchNameColor
    }

    fun setLunchNameColor(lunchNameColor: Int) {
        this.lunchNameColor = lunchNameColor
        invalidate()
        requestLayout()
    }

    fun getLunchVote(): Int {
        return lunchVote
    }

    fun setLunchVote(lunchVote: Int) {
        this.lunchVote = lunchVote
        invalidate()
        requestLayout()
    }

    @SuppressLint("DrawAllocation")
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        val width = this.measuredWidth
        val height = this.measuredHeight
        GlobalScope.launch {
            try {
                val bitmap = cropBitmap(getBitmapFromURL(imageURL)!!)
                val centreX = (width - bitmap.width) / 2.toFloat()
                val centreY = (height - bitmap.height) / 2.toFloat()
                canvas.drawBitmap(bitmap, centreX, centreY, null)
                val textp = Paint()
                textp.color = Color.WHITE
                textp.textSize = 100f
                textp.textAlign = Paint.Align.CENTER
                canvas.drawText(lunchName!!, width / 2.toFloat(), height / 2.toFloat(), textp)
            } catch (e: Exception) {
                Log.e("e", e.toString())
            }
        }
    }

    companion object {
        fun getBitmapFromURL(src: String?): Bitmap? {
            return try {
                val url = URL(src)
                val connection = url.openConnection() as HttpURLConnection
                connection.doInput = true
                connection.connect()
                val input = connection.inputStream
                BitmapFactory.decodeStream(input)
            } catch (e: IOException) {
                // Log exception
                null
            }
        }

        fun cropBitmap(bitmap: Bitmap): Bitmap {
            val width = bitmap.width;
            val height = bitmap.height;

            // 이미지를 crop 할 좌상단 좌표
            var x = 0
            var y = 0
            if (width > height) { // 이미지의 가로가 view 의 가로보다 크면..
                x = (width - height) / 2
            } else if (height > width) { // 이미지의 세로가 view 의 세로보다 크면..
                y = (height - width) / 2;
            }

            val croppedBitmap = Bitmap.createBitmap(bitmap, x, y, 100, 100);
            return croppedBitmap;
        }
    }
}