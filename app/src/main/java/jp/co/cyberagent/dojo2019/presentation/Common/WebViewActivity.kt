package jp.co.cyberagent.dojo2019.presentation.Common

import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import jp.co.cyberagent.dojo2019.R

class WebViewActivity : AppCompatActivity() {

    private lateinit var webView: WebView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_view)

        val url = intent?.getStringExtra("url").toString()
        webView = findViewById(R.id.web_view)
        webView.webViewClient = WebViewClient()
        webView.settings.javaScriptEnabled = true
        webView.loadUrl(url)
    }
}