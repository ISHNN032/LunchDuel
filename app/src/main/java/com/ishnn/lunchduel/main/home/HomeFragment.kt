package com.ishnn.lunchduel.ui.home

import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.ishnn.lunchduel.NotificationActivity
import com.ishnn.lunchduel.R
import com.ishnn.lunchduel.item.view.LunchView
import kotlinx.android.synthetic.main.fragment_home.*
import java.net.URL

class HomeFragment : Fragment(), LunchView.OnKakaoLoginInterface {
    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        home_image_menu.setImageURL("https://firebasestorage.googleapis.com/v0/b/lunchduel.appspot.com/o/menu_image%2Fchicken.jpg?alt=media&token=21e0221e-c4eb-4456-9b9e-34777e3033c1", this)
        home_button_lunch.setOnClickListener {
            val intent = Intent(context, NotificationActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onLogin(bitmap: Bitmap) {
        activity?.runOnUiThread {
            home_image_menu?.setImageBitmap(bitmap)
        }
    }
}