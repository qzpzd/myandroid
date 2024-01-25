package com.example.daohang.ui.home


import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.daohang.FullScreenWebViewActivity
import com.example.daohang.R
import com.example.daohang.databinding.FragmentHomeBinding
import kotlin.random.Random


class HomeFragment : Fragment(), ButtonSettingsDialog.OnConfirmListener {

    private var _binding: FragmentHomeBinding? = null
    private val quotes = arrayOf(
        "少壮不努力，老大徒悲伤。——长歌行",
        "人无善志，虽勇必伤。——《淮南子》",
        "丈夫之志，能屈能伸。——程允升",
        "不傲才以骄人，不以宠而作威。——诸葛亮",
        // Add more quotes here
    )
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root
        displayRandomQuote()
        return root
    }

    private fun displayRandomQuote() {
        val randomIndex = Random.nextInt(quotes.size)
        binding.textMingyan.text = quotes[randomIndex]
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

//        val webView: WebView = activity?.findViewById(R.id.webview) ?: return // 假设有id为webView的WebView组件已在布局文件中定义
//
//        setupWebView(webView)

        val button1: Button = requireActivity().findViewById(R.id.but1)
        val button2: Button = requireActivity().findViewById(R.id.but2)
        val button3: Button = requireActivity().findViewById(R.id.but3)
        val button4: Button = requireActivity().findViewById(R.id.but4)

        val button5: Button = requireActivity().findViewById(R.id.but5)
        val button6: Button = requireActivity().findViewById(R.id.but6)
        val button7: Button = requireActivity().findViewById(R.id.but7)
        val button8: Button = requireActivity().findViewById(R.id.but8)
        val button9: Button = requireActivity().findViewById(R.id.but9)
        val btn_add: Button = requireActivity().findViewById(R.id.btn_add)

        button1.setOnClickListener {
            val intent = Intent(requireContext(), FullScreenWebViewActivity::class.java)
            intent.putExtra("url", "https://www.runoob.com")
            startActivity(intent)
//            webView.loadUrl("https://www.runoob.com")
        }
        button2.setOnClickListener {
            val intent = Intent(requireContext(), FullScreenWebViewActivity::class.java)
            intent.putExtra("url", "https://chat1.geekgpt.org")
            startActivity(intent)
//            webView.loadUrl("https://chat1.geekgpt.org")
        }
        button3.setOnClickListener {
            val intent = Intent(requireContext(), FullScreenWebViewActivity::class.java)
            intent.putExtra("url", "https://blog.csdn.net/m0_47709941?type=blog")
            startActivity(intent)
//            webView.loadUrl("https://blog.csdn.net/m0_47709941?type=blog")
        }
        button4.setOnClickListener {
            val intent = Intent(requireContext(), FullScreenWebViewActivity::class.java)
            intent.putExtra("url", "https://www.cnblogs.com/qzpzd")
            startActivity(intent)
//            webView.loadUrl("https://www.cnblogs.com/qzpzd")
        }
        button5.setOnClickListener {
            val intent = Intent(requireContext(), FullScreenWebViewActivity::class.java)
            intent.putExtra("url", "https://www.zhihu.com/")
            startActivity(intent)
//            webView.loadUrl("https://www.zhihu.com/")
        }
        button6.setOnClickListener {
            val intent = Intent(requireContext(), FullScreenWebViewActivity::class.java)
            intent.putExtra("url", "https://mp.weixin.qq.com/cgi-bin/home?t=home/index&lang=zh_CN&token=1763045802#tab=sent-panel")
            startActivity(intent)
//            webView.loadUrl("https://mp.weixin.qq.com/cgi-bin/home?t=home/index&lang=zh_CN&token=1763045802#tab=sent-panel")
        }
        button7.setOnClickListener {
            val intent = Intent(requireContext(), FullScreenWebViewActivity::class.java)
            intent.putExtra("url", "https://www.w3schools.cn/#section4")
            startActivity(intent)
//            webView.loadUrl("https://www.w3schools.cn/#section4")
        }
        button8.setOnClickListener {
            val intent = Intent(requireContext(), FullScreenWebViewActivity::class.java)
            intent.putExtra("url", "https://tool.lu/")
            startActivity(intent)
//            webView.loadUrl("https://tool.lu/")
        }
        btn_add.setOnClickListener {
            onAddButtonClick(view)
        }
        button9.setOnClickListener {
            val searchEditText: EditText = requireActivity().findViewById(R.id.searchEditText)
            val query = searchEditText.text.toString()
            if (query.isNotEmpty()) {
                val url = "https://cn.bing.com/search?q=$query"
                val intent = Intent(requireContext(), FullScreenWebViewActivity::class.java)
                intent.putExtra("url", url)
                startActivity(intent)
//                webView.loadUrl(url)
            }
        }
    }


    override fun onConfirm(width: Int, height: Int,  buttonText: String) {
        val newButton = createCustomButton(width, height, buttonText)
//        addButtonToContainerAndReorder(requireActivity(), newButton)
        addButtonToContainerAndReorder(requireActivity(), newButton, buttonText)
    }
    fun onAddButtonClick(view: View?) {

        val buttonSettingsDialog = ButtonSettingsDialog(this, requireContext())
        buttonSettingsDialog.setOnConfirmListener(this)
        buttonSettingsDialog.show()
    }
    private fun addButtonToContainerAndReorder(activity: Activity, newButton: Button, buttonText: String) {
        val buttonsContainer: LinearLayout = activity.findViewById(R.id.buttons_container)

        // 首先检查并移除newButton是否已经存在于某个父视图中
        val currentParent = newButton.parent as? ViewGroup
        currentParent?.removeView(newButton)

        // 创建一个TextView，其内容与按钮相同
        val textViewBelowButton = TextView(activity)
        textViewBelowButton.text = buttonText
        textViewBelowButton.layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)

        // 创建一个LinearLayout用于包裹按钮和TextView
        val buttonAndTextViewWrapper = LinearLayout(activity).apply {
            orientation = LinearLayout.VERTICAL
            layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)

            // 添加按钮
            addView(newButton)
            // 添加TextView
            addView(textViewBelowButton)
        }

        // 将包含按钮和TextView的LinearLayout添加到buttons_container中
        buttonsContainer.addView(buttonAndTextViewWrapper)

        // 移动addButton到最后
        val addButton: Button = activity.findViewById(R.id.btn_add)
        buttonsContainer.removeView(addButton)
        buttonsContainer.addView(addButton)
    }

    fun createCustomButton(width: Int, height: Int, text: String): Button {
        val newButton = Button(requireContext())
        // 设置ID（如果需要持久化的话，需要关联到数据库或其他持久化存储）
        val generatedId = View.generateViewId()
        newButton.id = generatedId
        newButton.layoutParams = LinearLayout.LayoutParams(width, height)
        // 设置背景、文字颜色、图标等...
        // 设置背景颜色（例如，纯色背景）
        newButton.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.purple_200))
        // 设置文字内容
        newButton.text = text
        // 设置文字颜色
        newButton.setTextColor(ContextCompat.getColor(requireContext(), R.color.white))
        // 设置字体大小（单位：sp）
        newButton.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16f) // 示例值为16sp，您可以替换为您所需的大小
        return newButton
    }
    private fun addButtonToContainerAndReorder(activity: Activity, newButton: Button) {
        val buttonsContainer: LinearLayout = activity.findViewById(R.id.buttons_container)
        buttonsContainer.addView(newButton)

        // 移动addButton到最后
        val addButton: Button = activity.findViewById(R.id.btn_add)
        buttonsContainer.removeView(addButton)
        buttonsContainer.addView(addButton)
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

class ButtonSettingsDialog(private val homeFragment: HomeFragment,context: Context) : Dialog(context) {

    private lateinit var buttonId: String
    private lateinit var buttonText: EditText
    private lateinit var buttonWidth: EditText
    private lateinit var buttonHeight: EditText
    // 其他控件如背景、图标的编辑框..
    private lateinit var onConfirmListener: OnConfirmListener
    fun setOnConfirmListener(listener: OnConfirmListener) {
        onConfirmListener = listener
    }
    // 在ButtonSettingsDialog类中添加一个接口，用于通知主界面设置已完成
    interface OnConfirmListener {
        fun onConfirm(width: Int, height: Int, text: String)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_button_settings)

        // 初始化各个编辑框
        buttonText = findViewById(R.id.et_button_text)
        buttonWidth = findViewById(R.id.et_button_width)
        buttonHeight = findViewById(R.id.et_button_height)

        // 确认按钮点击事件，用来创建和保存按钮
        findViewById<Button>(R.id.btn_confirm).setOnClickListener {
            // 获取并保存设置的属性值
            val width = buttonWidth.text.toString().toIntOrNull() ?: 83
            val height = buttonHeight.text.toString().toIntOrNull() ?: 82
            val text = buttonText.text.toString()

           // 创建新按钮
            val newButton = homeFragment.createCustomButton(width, height, text)

            // 保存按钮及其设置（例如使用SharedPreferences或数据库）
            homeFragment.onConfirm(width, height, text)
            dismiss()

        }
    }
}

