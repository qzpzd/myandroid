package com.example.flippage.ui.dialog

import com.example.flippage.R
import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.util.TypedValue
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.flippage.ui.agent.AgentActivity
import com.example.flippage.ui.application.ApplicationActivity
import com.google.android.material.textview.MaterialTextView


class DialogActivity : AppCompatActivity(){

    private fun addMessageToContainer(message: String, isUserMessage: Boolean) {
        val messageView = TextView(this)
        val layoutParams = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )

        messageView.run {
            when (isUserMessage) {
                true -> {
//                    setBackgroundResource(R.drawable.user)
                    setPadding(12, 8, 12, 8)
                    textAlignment = View.TEXT_ALIGNMENT_VIEW_END
                }
                false -> {
//                    setBackgroundResource(R.drawable.robot)
                    setPadding(12, 8, 12, 8)
                    textAlignment = View.TEXT_ALIGNMENT_VIEW_START
                }
            }

            this.text = message
            setTextColor(getColor(if (isUserMessage) R.color.light_black else R.color.light_black))
            setTextSize(TypedValue.COMPLEX_UNIT_SP, 10f)
        }

        // 获取消息容器并添加新的消息视图
        val messagePairContainer = findViewById<LinearLayout>(R.id.message_pair_container)
        messagePairContainer.addView(messageView, layoutParams)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_dialog)
        // 当需要显示对话框时，调用 showCustomDialog 函数
        showCustomDialog()

        // 在Activity或Fragment的onCreate视图初始化部分
        val inputText = findViewById<EditText>(R.id.input_text)
        val sendButton = findViewById<Button>(R.id.send_button)

        sendButton.setOnClickListener {
            val userInput = inputText.text.toString().trim()
            val robbotInput = "待补充"
            // 检查消息是否为空
            if (userInput.isNotBlank()) {
                // 将用户消息添加到消息容器
                addMessageToContainer("用户：$userInput", true)
                // 清空输入框
                inputText.text.clear()
                // 可选：模拟机器人回复，添加机器人消息
                addMessageToContainer("机器人回复：$robbotInput", false)
            }
        }


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
    }


    private fun showCustomDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("标题")
            .setMessage("这里是对话框的消息内容")
            .setPositiveButton(
                "确定"
            ) { dialog, which ->
                onPositiveButtonClicked()
            }
            .setNegativeButton(
                "取消"
            ) { dialog, which ->
                onNegativeButtonClicked()
            }

        val dialog = builder.create()
        dialog.show()
    }

    fun onPositiveButtonClicked() {
        // 处理“确定”按钮点击事件
        Toast.makeText(this, "确定按钮被点击", Toast.LENGTH_SHORT).show()
    }

    fun onNegativeButtonClicked() {
        // 处理“取消”按钮点击事件
        Toast.makeText(this, "取消按钮被点击", Toast.LENGTH_SHORT).show()
    }
}