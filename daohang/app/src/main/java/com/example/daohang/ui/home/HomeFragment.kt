package com.example.daohang.ui.home

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.daohang.R
import com.example.daohang.databinding.FragmentHomeBinding
import kotlin.random.Random


class HomeFragment : Fragment() {

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
//        val homeViewModel =
//            ViewModelProvider(this).get(HomeViewModel::class.java)
//
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root
//        val quotetextView: TextView = binding.textMingyan
//        homeViewModel.text.observe(viewLifecycleOwner) {
//            quotetextView.text = it
//
//        }

        displayRandomQuote()
        return root
    }

    private fun displayRandomQuote() {
        val randomIndex = Random.nextInt(quotes.size)
        binding.textMingyan.text = quotes[randomIndex]
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val button1: Button = requireActivity().findViewById(R.id.but1)
        val button2: Button = requireActivity().findViewById(R.id.but2)
        val button3: Button = requireActivity().findViewById(R.id.but3)
        val button4: Button = requireActivity().findViewById(R.id.but4)

        val button5: Button = requireActivity().findViewById(R.id.but5)
        val button6: Button = requireActivity().findViewById(R.id.but6)
        val button7: Button = requireActivity().findViewById(R.id.but7)
        val button8: Button = requireActivity().findViewById(R.id.but8)
        val button9: Button = requireActivity().findViewById(R.id.but9)

        button1.setOnClickListener {
            val uri = Uri.parse("https://www.runoob.com")    //设置跳转的网站
            val intent = Intent(Intent.ACTION_VIEW, uri)
            startActivity(intent)
        }
        button2.setOnClickListener {
            val uri = Uri.parse("https://chat1.geekgpt.org")    //设置跳转的网站
            val intent = Intent(Intent.ACTION_VIEW, uri)
            startActivity(intent)
        }
        button3.setOnClickListener {
            val uri = Uri.parse("https://blog.csdn.net/m0_47709941?type=blog")    //设置跳转的网站
            val intent = Intent(Intent.ACTION_VIEW, uri)
            startActivity(intent)
        }
        button4.setOnClickListener {
            val uri = Uri.parse("https://www.cnblogs.com/qzpzd")    //设置跳转的网站
            val intent = Intent(Intent.ACTION_VIEW, uri)
            startActivity(intent)
        }

        button5.setOnClickListener {
            val uri = Uri.parse("https://www.zhihu.com/")    //设置跳转的网站
            val intent = Intent(Intent.ACTION_VIEW, uri)
            startActivity(intent)
        }
        button6.setOnClickListener {
            val uri =
                Uri.parse("https://mp.weixin.qq.com/cgi-bin/home?t=home/index&lang=zh_CN&token=1763045802#tab=sent-panel")    //设置跳转的网站
            val intent = Intent(Intent.ACTION_VIEW, uri)
            startActivity(intent)
        }
        button7.setOnClickListener {
            val uri = Uri.parse("https://www.w3schools.cn/#section4")    //设置跳转的网站
            val intent = Intent(Intent.ACTION_VIEW, uri)
            startActivity(intent)
        }
        button8.setOnClickListener {
            val uri = Uri.parse("https://tool.lu/")    //设置跳转的网站
            val intent = Intent(Intent.ACTION_VIEW, uri)
            startActivity(intent)
        }
        button9.setOnClickListener {
            val searchEditText: EditText = requireActivity().findViewById(R.id.searchEditText)
            val query = searchEditText.text.toString()
            if (query.isNotEmpty()) {
                val url = "https://cn.bing.com/search?q=$query"
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                startActivity(intent)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
