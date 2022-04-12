package com.timusandrei.dogbreeds

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View.SCROLLBARS_INSIDE_OVERLAY
import android.webkit.WebView
import android.webkit.WebViewClient

class WikiActivity : AppCompatActivity() {

    private val defaultUrl : String = "https://en.wikipedia.org/wiki/Dog"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_wiki)

        val url : String? = intent.getStringExtra("url")
        val webView : WebView = findViewById(R.id.web_view)

        webView.settings.loadsImagesAutomatically = true
        webView.scrollBarStyle = SCROLLBARS_INSIDE_OVERLAY
        webView.webViewClient = WebViewClient()

        if(url != null && url.isNotEmpty()) {
            webView.loadUrl(url)
        } else {
            webView.loadUrl(defaultUrl)
        }
    }
}