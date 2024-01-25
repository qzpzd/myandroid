package com.example.detector


import okhttp3.Credentials
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.Response
import java.net.InetSocketAddress
import java.net.Proxy
import java.net.ProxySelector
import java.net.URI
import java.io.IOException

object ModelApiService {
    private const val API_URL = "https://api-inference.huggingface.co/models/runwayml/stable-diffusion-v1-5"
    private val PROXY_HOST = "192.168.3.117"
    private val PROXY_PORT = 8787 // 替换为你的代理端口号
    private val PROXY_USERNAME = "your_proxy_username" // 如果需要身份验证，则填入用户名
    private val PROXY_PASSWORD = "your_proxy_password" // 如果需要身份验证，则填入密码

//    private val client = OkHttpClient()
    private val client: OkHttpClient = OkHttpClient.Builder()
        .proxy(Proxy(Proxy.Type.HTTP, InetSocketAddress(PROXY_HOST, PROXY_PORT)))
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
        .build()
    private val JSON_MEDIA_TYPE = "application/json".toMediaType()

    fun generateImageFromText(text: String): ByteArray? {
        val requestBody = "{\"inputs\": \"$text\"}"
        val request = Request.Builder()
            .url(API_URL)
            .post(requestBody.toRequestBody(JSON_MEDIA_TYPE))
            .addHeader("Authorization", "Bearer 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")
            .build()

        try {
            val response: Response = client.newCall(request).execute()
            if (response.isSuccessful) {
            return response.body?.bytes()
            } else {
                println("Error: ${response.code} - ${response.message}")
                return null
            }
        } catch (e: IOException) {
//            e.printStackTrace()
            println("Error: ${e.message}")
            return null
        }
    }
}

