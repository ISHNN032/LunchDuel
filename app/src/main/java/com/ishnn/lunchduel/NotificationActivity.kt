package com.ishnn.lunchduel

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_notification.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class NotificationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notification)
    }

    override fun onStart() {
        super.onStart()
        setLog()
    }

    fun setLog(){
        GlobalScope.launch {
            var log = "서버가 열렸습니다\n"

            repeat(11){
                when(it){
                    0 -> {
                        log += "김지수 님이 참가하셨습니다.\n"
                    }
                    1 -> {
                        delay(2000)
                        log += "가성비 님이 참가하셨습니다.\n"
                    }
                    2 -> {
                        delay(1000)
                        log += "압도적 님이 참가하셨습니다.\n"
                    }
                    3 -> {
                        delay(200)
                        log += "예제 님이 참가하셨습니다.\n"
                    }
                    4 -> {
                        delay(500)
                        log += "개발자 님이 참가하셨습니다.\n"
                    }
                    5 -> {
                        delay(1000)
                        log += "구독 님이 참가하셨습니다.\n"
                    }
                    6 -> {
                        log += "구매하기 님이 참가하셨습니다.\n"
                    }
                    7 -> {
                        delay(2000)
                        log += "공유 님이 참가하셨습니다.\n"
                    }
                    8 -> {
                        log += "모든 참가자가 참여했습니다.\n"
                    }
                    9 -> {
                        delay(2000)
                        log += "참가자 데이터를 수집했습니다.\n"
                    }
                    10 -> {
                        delay(500)
                        log += "종목 투표를 시작합니다.\n"
                        runOnUiThread {
                            notification_server_log.text = log
                        }
                        delay(3000)
                        val intent = Intent(baseContext, GameActivity::class.java)
                        startActivity(intent)
                        return@launch
                    }
                }
                runOnUiThread {
                    notification_server_log.text = log
                }
            }
        }
    }
}