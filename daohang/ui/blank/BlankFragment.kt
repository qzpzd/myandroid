package com.example.daohang.ui.blank
//import android.os.Bundle
//import android.util.Log
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.widget.Button
//import android.widget.EditText
//import androidx.fragment.app.Fragment
//import androidx.recyclerview.widget.LinearLayoutManager
//import androidx.recyclerview.widget.RecyclerView
//import com.example.daohang.R
//import okhttp3.OkHttpClient
//import okhttp3.logging.HttpLoggingInterceptor
//import org.json.JSONArray
//import retrofit2.Retrofit
//import retrofit2.converter.gson.GsonConverterFactory
//import retrofit2.Call
//import retrofit2.Callback
//import retrofit2.Response
//import retrofit2.http.Body
//import retrofit2.http.Headers
//import retrofit2.http.POST
//
//
//class BlankFragment : Fragment() {
//    private lateinit var recyclerView: RecyclerView
//    private val chatMessages = mutableListOf<ChatMessage>()
//    private lateinit var chatAdapter: ChatAdapter
//    private lateinit var chatGptService: ChatGptService // 声明 ChatGptService
//
//    override fun onCreateView(
//        inflater: LayoutInflater,
//        container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
////        val chatMessages = mutableListOf(
////            ChatMessage("Hello, how can I help you?", MessageType.USER),
////            ChatMessage("This is a system message.", MessageType.SYSTEM)
////        )
//        // 使用适当的布局文件来设置聊天界面的视图
//        val rootView = inflater.inflate(R.layout.fragment_blank, container, false)
//        val recyclerView = rootView.findViewById<RecyclerView>(R.id.chatRecyclerView)
//        recyclerView.layoutManager = LinearLayoutManager(requireContext())
//        val adapter = ChatAdapter(chatMessages)
//        recyclerView.adapter = adapter
//
//        // 模拟添加用户和系统消息
//        addUserMessage("Hello, how can I help you?")
//        addSystemMessage("This is a system message.")
//
//
//        return rootView
//
//    }
//
//    // 添加用户消息的方法
//    private fun addUserMessage(messageText: String) {
//        val userMessage = ChatMessage(messageText, MessageType.USER)
//        chatMessages.add(userMessage)
//
//        // 通知适配器数据已更改
//        recyclerView.adapter?.notifyItemInserted(chatMessages.size - 1)
//    }
//
//    // 添加系统消息的方法
//    private fun addSystemMessage(messageText: String) {
//        val systemMessage = ChatMessage(messageText, MessageType.SYSTEM)
//        chatMessages.add(systemMessage)
//
//        // 通知适配器数据已更改
//        recyclerView.adapter?.notifyItemInserted(chatMessages.size - 1)
//    }
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//
//        // 查找 RecyclerView
//        val chatRecyclerView = view.findViewById<RecyclerView>(R.id.chatRecyclerView)
//
//        // 查找发送按钮
//        val sendButton = view.findViewById<Button>(R.id.sendButton)
//
//        // 查找消息输入框
//        val messageEditText = view.findViewById<EditText>(R.id.messageEditText)
//
//        // 初始化 ChatAdapter
//        chatAdapter = ChatAdapter(chatMessages)
//
//        // 设置 RecyclerView
//        chatRecyclerView.layoutManager = LinearLayoutManager(requireContext())
//        chatRecyclerView.adapter = chatAdapter
//
//        val httpClient = OkHttpClient.Builder()
//            .addInterceptor(HttpLoggingInterceptor().apply {
//                level = HttpLoggingInterceptor.Level.BODY // 设置日志级别为完整响应内容
//            })
//            .build()
//
//        // 初始化 Retrofit
//        val retrofit = Retrofit.Builder()
//            .baseUrl("https://api-inference.huggingface.co/") // 替换为实际的 API 基础 URL
//            .client(httpClient) // 使用上面创建的 OkHttp 客户端
//            .addConverterFactory(GsonConverterFactory.create())
//            .build()
//
//        // 创建 ChatGptService 接口实例
//        chatGptService = retrofit.create(ChatGptService::class.java)
//
//        // 设置发送按钮的点击事件
//        sendButton.setOnClickListener {
//            val userMessage = messageEditText.text.toString()
//            if (userMessage.isNotEmpty()) {
//                // 将用户消息添加到 chatMessages 列表
//                chatMessages.add(ChatMessage(userMessage, MessageType.USER))
//                chatAdapter.notifyItemInserted(chatMessages.size - 1)
//
//                // 调用 ChatGPT API 并添加回复到 chatMessages 列表
//                val call = chatGptService.getChatGptReply(userMessage)
//
//                call.enqueue(object : Callback<List<ChatGptResponse>> {
//                    override fun onResponse(
//                        call: Call<List<ChatGptResponse>>,
//                        response: Response<List<ChatGptResponse>>
//                    ) {
//                        if (response.isSuccessful) {
//                            val chatGptResponses = response.body()
//                            chatGptResponses?.let { chatGptResponses ->
//                                for (chatGptResponse in chatGptResponses) {
//                                    val chatGptReply = chatGptResponse.generated_text
//                                    // 打印响应内容到日志
//                                    Log.d("ChatGptResponse", "ChatGptResponse: $chatGptReply")
//                                    chatMessages.add(ChatMessage(chatGptReply, MessageType.SYSTEM))
//                                    chatAdapter.notifyItemInserted(chatMessages.size - 1)
//                                }
//                            }
//                        } else {
//                            // 处理网络请求失败等情况
//                            Log.e("MyApp", "Network request failed")
//                        }
//
//                        // 清空输入框
//                        messageEditText.text.clear()
//
//                        // 滚动 RecyclerView 到最后一条消息
//                        chatRecyclerView.scrollToPosition(chatMessages.size - 1)
//                    }
//
//                    override fun onFailure(call: Call<List<ChatGptResponse>>, t: Throwable) {
//                        // 处理网络请求失败等情况
//                        Log.e("MyApp", "Network request failed", t)
//                    }
//                })
//            }
//        }
//    }
//        // 添加 ChatGptService 接口
//    interface ChatGptService {
//        @POST("models/gpt2") // 替换为实际的 API 端点
//        @Headers("Authorization: Bearer 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") // 替换为实际的 API 密钥
//        fun getChatGptReply(@Body userMessage: String): Call<List<ChatGptResponse>>
//    }
//
//}


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.daohang.ModelApiService
import com.example.daohang.R
import com.example.daohang.databinding.FragmentSlideshowBinding
import okhttp3.*
import org.json.JSONArray
import java.io.IOException
import okhttp3.RequestBody
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import com.fasterxml.jackson.databind.ObjectMapper
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject
import java.net.InetSocketAddress
import java.net.Proxy


class BlankFragment : Fragment() {
    private lateinit var messageEditText: EditText
    private lateinit var sendButton: Button
    private lateinit var chatRecyclerView: RecyclerView
    private lateinit var chatAdapter: ChatAdapter
    private lateinit var chatMessages: MutableList<ChatMessage>

//    private val PROXY_HOST = "192.168.3.117"
//    private val PROXY_PORT = 8787 // 替换为你的代理端口号
//    private val PROXY_USERNAME = "your_proxy_username" // 如果需要身份验证，则填入用户名
//    private val PROXY_PASSWORD = "your_proxy_password" // 如果需要身份验证，则填入密码
//
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
    private val objectMapper = ObjectMapper()
//    private val baseUrl = "https://api-inference.huggingface.co/models/Phind/Phind-CodeLlama-34B-v2"
    private val baseUrl =
        "https://api-inference.huggingface.co/models/microsoft/DialoGPT-large"
//        "https://hf-mirror.com/microsoft/DialoGPT-large"

    private val apiKey = "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"


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val rootView = inflater.inflate(R.layout.fragment_blank, container, false)
        chatRecyclerView = rootView.findViewById(R.id.chatRecyclerView)
        messageEditText = rootView.findViewById(R.id.messageEditText)
        sendButton = rootView.findViewById(R.id.sendButton)

        chatMessages = mutableListOf()
        chatAdapter = ChatAdapter(chatMessages)
        chatRecyclerView.adapter = chatAdapter
        chatRecyclerView.layoutManager = LinearLayoutManager(requireContext())

//        sendButton.setOnClickListener {
//            val userMessage = messageEditText.text.toString()
//            if (userMessage.isNotBlank()) {
//                sendMessage(userMessage)
//                messageEditText.text.clear()
//            }
//        }

        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        messageEditText = view.findViewById(R.id.messageEditText)
        sendButton = view.findViewById(R.id.sendButton)
        chatRecyclerView = view.findViewById(R.id.chatRecyclerView)

        chatMessages = mutableListOf()
        chatAdapter = ChatAdapter(chatMessages)
        chatRecyclerView.adapter = chatAdapter
        chatRecyclerView.layoutManager = LinearLayoutManager(requireContext())



        sendButton.setOnClickListener {
            val userMessage = messageEditText.text.toString()
            if (userMessage.isNotBlank()) {
                sendMessage(userMessage)
                messageEditText.text.clear()
            }
        }
    }

    private fun sendMessage(message: String) {
        val chatMessage = ChatMessage(message, true)
        chatMessages.add(chatMessage)
        chatAdapter.notifyItemInserted(chatMessages.size - 1)
        chatRecyclerView.scrollToPosition(chatMessages.size - 1)


//        val requestJson = objectMapper.writeValueAsString(mapOf("message" to message))
//        val mediaType = "application/json; charset=utf-8".toMediaTypeOrNull()
//        val requestBody = RequestBody.create(mediaType, requestJson)

        val JSON_MEDIA_TYPE = "application/json".toMediaType()
        val requestBody = "{\"inputs\": \"$message\"}"
        val request = Request.Builder()
            .url(baseUrl)
            .header("Authorization", "Bearer $apiKey")
            .post(requestBody.toRequestBody(JSON_MEDIA_TYPE))
            .build()

        Log.d("ChatGptrequest", "request: $requestBody ")
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                Log.e("Request", "Failed to make request: ${e.message}")
                requireActivity().runOnUiThread {
                    val errorMessage = "Failed to send message"
                    showError(errorMessage)
                    val systemMessage = ChatMessage(errorMessage, false)
                    chatMessages.add(systemMessage)
                    chatAdapter.notifyItemInserted(chatMessages.size - 1)
                    chatRecyclerView.scrollToPosition(chatMessages.size - 1)
                }
            }

            override fun onResponse(call: Call, response: Response) {
                val responseBody = response.body?.string()
                Log.d("ChatGptResponse", "responseBody: $responseBody")
                if (response.isSuccessful && !responseBody.isNullOrBlank()) {
//                    val jsonResponse = JSONArray(responseBody).getJSONObject(0)
                    val jsonResponse = JSONObject(responseBody)
                    val systemMessage = jsonResponse.getString("generated_text")


                    requireActivity().runOnUiThread {
                        val chatMessage = ChatMessage(systemMessage, false)
                        chatMessages.add(chatMessage)
                        chatAdapter.notifyItemInserted(chatMessages.size - 1)
                        chatRecyclerView.scrollToPosition(chatMessages.size - 1)
                    }
                } else {
                    Log.e("Response", "Response was unsuccessful: $responseBody")
                    requireActivity().runOnUiThread {
                        val errorMessage = "Failed to get response"
                        showError(errorMessage)
                        val systemMessage = ChatMessage(errorMessage, false)
                        chatMessages.add(systemMessage)
                        chatAdapter.notifyItemInserted(chatMessages.size - 1)
                        chatRecyclerView.scrollToPosition(chatMessages.size - 1)
                    }
                }
            }
        })
    }

    private fun showError(errorMessage: String) {
        // Show error toast or handle error UI
    }
}
