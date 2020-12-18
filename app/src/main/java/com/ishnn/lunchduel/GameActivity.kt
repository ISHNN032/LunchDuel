package com.ishnn.lunchduel

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ishnn.lunchduel.ui.modeselect.ModeSelectFragment

class GameActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, ModeSelectFragment.newInstance())
                .commitNow()
        }
    }
}