package jp.co.cyberagent.dojo2019

import android.os.Bundle
import android.webkit.WebView
import androidx.appcompat.app.AppCompatActivity

class WebViewActivity : AppCompatActivity() {

    private lateinit var webView: WebView
    private var githubID: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_view)

        githubID = intent?.getStringExtra("githubURL").toString()

        webView = findViewById(R.id.web_view)
        webView.loadUrl("https://github.com/${githubID}")
    }
}