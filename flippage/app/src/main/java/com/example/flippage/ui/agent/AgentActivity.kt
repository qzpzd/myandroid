package com.example.flippage.ui.agent

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.example.flippage.R
import com.example.flippage.ui.application.ApplicationActivity
import com.example.flippage.ui.dialog.DialogActivity
import com.google.android.material.textview.MaterialTextView


class AgentActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_agent) // 确保这里的布局文件名与导入语句中的相匹配


        // 初始化顶部"按钮"（实际上是MaterialTextView）
        val textButtonPage1: MaterialTextView = findViewById(R.id.text_button_page1)
        val textButtonPage2: MaterialTextView = findViewById(R.id.text_button_page2)
        val textButtonPage3: MaterialTextView = findViewById(R.id.text_button_page3)

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

        val row1column1 = findViewById<Button>(R.id.button_row1_column1)
        val row1column2 = findViewById<Button>(R.id.button_row1_column2)
        val row2column1 = findViewById<Button>(R.id.button_row2_column1)
        val row2column2 = findViewById<Button>(R.id.button_row2_column2)

        // 为按钮绑定点击事件
        row1column1.setOnClickListener {
            // Button 1 的点击事件处理
            Toast.makeText(this, "Button 1 clicked", Toast.LENGTH_SHORT).show()
        }

        row1column2.setOnClickListener {
            // Button 2 的点击事件处理
            Toast.makeText(this, "Button 2 clicked", Toast.LENGTH_SHORT).show()
        }

        row2column1.setOnClickListener {
            // Button 3 的点击事件处理
            Toast.makeText(this, "Button 3 clicked", Toast.LENGTH_SHORT).show()
        }

        row2column2.setOnClickListener {
            // Button 4 的点击事件处理
            Toast.makeText(this, "Button 4 clicked", Toast.LENGTH_SHORT).show()
        }
    }
}