package com.example.daohang.ui.slideshow


import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.daohang.ModelApiService
import com.example.daohang.R
import com.example.daohang.databinding.FragmentSlideshowBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException
import java.net.InetSocketAddress
import java.net.Proxy

class SlideshowFragment : Fragment() {

    private var _binding: FragmentSlideshowBinding? = null
    private lateinit var imageView: ImageView
    private lateinit var textView: TextView
    private var imageUri: Uri? = null // Declare imageUri at the class level

//    private val PROXY_HOST = "192.168.3.117"
//    private val PROXY_PORT = 8787 // 替换为你的代理端口号
//    private val PROXY_USERNAME = "your_proxy_username" // 如果需要身份验证，则填入用户名
//    private val PROXY_PASSWORD = "your_proxy_password" // 如果需要身份验证，则填入密码

//    private val client: OkHttpClient = OkHttpClient.Builder()
//        .proxy(Proxy(Proxy.Type.HTTP, InetSocketAddress(PROXY_HOST, PROXY_PORT)))
//        .proxyAuthenticator { route, response ->
//            // 如果你的代理需要身份验证
//            if (route.proxy.type() == Proxy.Type.HTTP) {
//                val credential = Credentials.basic(PROXY_USERNAME, PROXY_PASSWORD)
//                response.request.newBuilder()
//                    .header("Proxy-Authorization", credential)
//                    .build()
//            } else {
//                throw IOException("Unauthenticated proxy or unsupported proxy type.")
//            }
//        }
//        .build()

    private val client = OkHttpClient()
    private val pickImage = registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
        uri?.let {
            imageView.setImageURI(it)
            imageUri = it // Store the selected image URI
//            convertImageToText(it)
        }
    }

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentSlideshowBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }

    val permission = android.Manifest.permission.READ_EXTERNAL_STORAGE
    val permissionLauncher = registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
        if (isGranted) {
            // Permission granted, you can proceed to access media files
            pickImage.launch("image/*")
        } else {
            // Permission denied, show appropriate message to the user
            Toast.makeText(requireContext(), "需要访问媒体文件的权限", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        imageView = view.findViewById(R.id.image_1)
        textView = requireActivity().findViewById(R.id.textView_2)

        imageView.setOnClickListener {
            // Request new storage permission
            permissionLauncher.launch(permission)
        }

        val convertButton = requireActivity().findViewById<Button>(R.id.but11)

        convertButton.setOnClickListener {
            // Check if an image is selected
            if (imageUri != null) {
                // Convert the selected image to text
                convertImageToText(imageUri!!)
            } else {
                Toast.makeText(requireContext(), "请先选择图像", Toast.LENGTH_SHORT).show()
            }
        }
    }


    private fun convertImageToText(imageUri: Uri) {
        val apiEndpoint =
            "https://api-inference.huggingface.co/models/nlpconnect/vit-gpt2-image-captioning"
//            "https://hf-mirror.com/nlpconnect/vit-gpt2-image-captioning" //镜像huggingface
        val apiKey = "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"

        val imageStream = requireActivity().contentResolver.openInputStream(imageUri)
        val imageBytes = imageStream?.readBytes()

        val requestBody = RequestBody.create("image/*".toMediaTypeOrNull(), imageBytes!!)
        val request = Request.Builder()
            .url(apiEndpoint)
            .header("Authorization", "Bearer $apiKey")
            .post(requestBody)
            .build()

        viewLifecycleOwner.lifecycleScope.launch(Dispatchers.IO) {
            try {
                val response = client.newCall(request).execute()

                if (response.isSuccessful) {
                    val responseBody = response.body?.string()

                    // 使用 Log 输出响应数据
                    Log.d("API Response", responseBody ?: "Response body is null")

                    if (!responseBody.isNullOrEmpty()) {
                        val jsonArray = JSONArray(responseBody)
                        for (i in 0 until jsonArray.length()) {
                            val jsonObject = jsonArray.getJSONObject(i)
                            val generatedText = jsonObject.optString("generated_text")

                            // 在主线程上更新UI
                            withContext(Dispatchers.Main) {
                                // 在UI上显示生成的文本
                                textView.text = generatedText
                            }
                        }
                    } else {
                        withContext(Dispatchers.Main) {
                            // 处理响应体为空的情况
                            textView.text = "未能提取文本"
                        }
                    }
                } else {
                    withContext(Dispatchers.Main) {
                        // 处理非成功响应的情况
                        textView.text = "API请求失败"
                    }
                }
            } catch (e: IOException) {
                e.printStackTrace()
                withContext(Dispatchers.Main) {
                    // 处理IOException
                    textView.text = "发生网络错误"
                }
            } catch (e: JSONException) {
                e.printStackTrace()
                withContext(Dispatchers.Main) {
                    // 处理JSONException
                    textView.text = "JSON解析错误"
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
