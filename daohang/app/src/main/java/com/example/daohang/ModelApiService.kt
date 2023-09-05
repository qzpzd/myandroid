package com.example.daohang


import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.Response
import java.io.IOException

object ModelApiService {
    private const val API_URL = "https://api-inference.huggingface.co/models/runwayml/stable-diffusion-v1-5"

    private val client = OkHttpClient()
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
            return response.body?.bytes()
        } catch (e: IOException) {
            e.printStackTrace()
            return null
        }
    }
}

