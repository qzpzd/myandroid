// 导入必要的包
package com.example.flippage.ui.application

import android.content.Intent
import com.example.flippage.R
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.flippage.ui.agent.AgentActivity
import com.example.flippage.ui.dialog.DialogActivity
import com.google.android.material.textview.MaterialTextView

class ApplicationActivity : AppCompatActivity() {
    // 声明布局中的视图引用
    private var imageViewRow1: ImageView? = null
    private var textViewRow1: TextView? = null
    private var imageViewRow2: ImageView? = null
    private var textViewRow2: TextView? = null
    private var imageViewRow3: ImageView? = null
    private var textViewRow3: TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_application) // 确保XML布局文件名与此处一致，假设为activity_main.xml

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

        // 初始化视图引用
        imageViewRow1 = findViewById<ImageView>(R.id.image_view_row1)
        textViewRow1 = findViewById<TextView>(R.id.text_view_row1)
        imageViewRow2 = findViewById<ImageView>(R.id.image_view_row2)
        textViewRow2 = findViewById<TextView>(R.id.text_view_row2)
        imageViewRow3 = findViewById<ImageView>(R.id.image_view_row3)
        textViewRow3 = findViewById<TextView>(R.id.text_view_row3)

        // 可在此处添加对视图的操作，例如设置点击事件、更新文本等
    }
}