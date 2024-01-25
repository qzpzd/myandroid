package com.example.daohang.ui.blank
//
//import android.view.View
//import android.widget.ImageView
//import android.widget.TextView
//import androidx.recyclerview.widget.RecyclerView
//import com.example.daohang.R
//import com.google.gson.annotations.SerializedName
//import retrofit2.Call
//import retrofit2.http.Body
//import retrofit2.http.Headers
//import retrofit2.http.POST
//
////interface ChatGptService {
////    @Headers("Authorization: Bearer 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")
////    @POST("models/FlagAlpha/Atom-7B") // 替换为实际的 ChatGPT API 终端点
////    suspend fun getChatGptReply(@Body requestBody: ChatGptRequest): ChatGptResponse
////}
//
//数据模型
//data class ChatMessage(
//    val text: String,
//    val messageType: MessageType
//)
//enum class MessageType {
//    USER,
//    SYSTEM
//}
//
//data class ChatGptRequest(val messages: List<Message>)
//
//data class Message(val role: String, val content: String)
//
//data class ChatGptResponse(
//    val generated_text: String,
//    val messageType: MessageType
//)





data class ChatMessage(val text: String, val isUser: Boolean)