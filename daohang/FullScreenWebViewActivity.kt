package com.example.daohang

import android.os.Bundle
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity

class FullScreenWebViewActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fullscreen_webview)

        val webView: WebView = findViewById(R.id.fullscreen_webview)

        webView.apply {
            settings.javaScriptEnabled = true
            webViewClient = object : WebViewClient() {
                override fun shouldOverrideUrlLoading(view: WebView, request: WebResourceRequest): Boolean {
                    view.loadUrl(request.url.toString())
                    return true
                }
            }
        }

        // 获取传递过来的URL参数并加载网页
        val url = intent.getStringExtra("url")
        if (url != null) {
            webView.loadUrl(url)
        }
    }
}