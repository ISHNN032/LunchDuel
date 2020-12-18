package com.ishnn.lunchduel.item.view

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.airbnb.lottie.LottieAnimationView
import com.airbnb.lottie.LottieDrawable
import com.ishnn.lunchduel.R
import kotlinx.android.synthetic.main.item_lunch.view.*
import kotlinx.coroutines.*
import java.io.IOException
import java.net.HttpURLConnection
import java.net.URL


class LunchItemListView : ConstraintLayout {
    private var imageURL: String? = null
    private var lunchName: TextView? = null
    private var lunchNameColor = 0
    private var lunchVote = 0

    interface OnKakaoLoginInterface {
        fun onLogin(bitmap: Bitmap)
    }

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        val array = context.theme.obtainStyledAttributes(attrs, R.styleable.LunchView, 0, 0)
        try {
            imageURL = array.getString(R.styleable.LunchView_imageURL);
            val lunchNameText = array.getString(R.styleable.LunchView_lunchName)

            lunchNameColor = array.getColor(0, Color.WHITE)
            lunchVote = array.getInteger(R.styleable.LunchView_lunchVote, 0)

            val service = Context.LAYOUT_INFLATER_SERVICE
            val li = getContext().getSystemService(service) as LayoutInflater

            val layout = li.inflate(R.layout.item_lunch, this, true) as ConstraintLayout
            lunchName = layout.findViewById(R.id.item_lunch_name) as TextView
            lunchName!!.text = lunchNameText
            array.recycle()
        } finally {
        }
    }

    fun getImageURL(): String? {
        return imageURL
    }

    fun setImageURL(imageURL: String?, callbacks: OnKakaoLoginInterface){
        this.imageURL = imageURL
        GlobalScope.launch {
            try {
                val bitmap = getBitmapFromURL(imageURL)!!
                callbacks.onLogin(bitmap)
            } catch (e: Exception) {
                Log.e("setImageURL", "message : $e")
            }
        }
    }

    fun setImageBitmap(bitmap: Bitmap){
        item_lunch_loading.visibility = View.GONE
        item_lunch_image.setImageBitmap(bitmap)
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
                Log.e("getBitmapFromURL", "message : $e")
                null
            }
        }
    }
}