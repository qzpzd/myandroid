package com.example.flippage

import android.app.Application
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.flippage.ui.application.ApplicationActivity
import com.example.flippage.ui.dialog.DialogActivity
import com.example.flippage.ui.agent.AgentActivity
import com.google.android.material.textview.MaterialTextView


class MainActivity : AppCompatActivity() {
    private var textButtonPage1: MaterialTextView? = null
    private var textButtonPage2: MaterialTextView? = null
    private var textButtonPage3: MaterialTextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 初始化"按钮"（实际上是MaterialTextView）
        textButtonPage1 = findViewById(R.id.text_button_page1)
        textButtonPage2 = findViewById(R.id.text_button_page2)
        textButtonPage3 = findViewById(R.id.text_button_page3)

        // 设置"按钮"点击事件
        textButtonPage1?.setOnClickListener { view ->
            val intent = Intent(this, DialogActivity::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
        }

        textButtonPage2?.setOnClickListener { view ->
            val intent = Intent(this, AgentActivity::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
        }

        textButtonPage3?.setOnClickListener { view ->
            val intent = Intent(this, ApplicationActivity::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
        }
    }
}