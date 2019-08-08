package jp.co.cyberagent.dojo2019

import android.os.Bundle
import android.webkit.WebView
import androidx.appcompat.app.AppCompatActivity

class WebViewActivity : AppCompatActivity() {

    private lateinit var webView: WebView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_view)

        val url = intent?.getStringExtra("url").toString()
        webView = findViewById(R.id.web_view)
        webView.loadUrl(url)
    }
}