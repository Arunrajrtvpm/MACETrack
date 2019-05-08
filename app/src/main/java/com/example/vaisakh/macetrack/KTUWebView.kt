package com.example.vaisakh.macetrack

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient

class KTUWebView : AppCompatActivity() {

    var mywebview: WebView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ktuweb_view)
        mywebview = findViewById<WebView>(R.id.Webview)
        mywebview!!.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                view?.loadUrl(url)
                return true
            }
        }
        mywebview!!.loadUrl("https://www.google.co.in/")
    }
}
