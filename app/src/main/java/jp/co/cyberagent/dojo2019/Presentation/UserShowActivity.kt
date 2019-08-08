package jp.co.cyberagent.dojo2019.Presentation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.squareup.picasso.Picasso
import jp.co.cyberagent.dojo2019.Database.AppDatabase
import jp.co.cyberagent.dojo2019.Model.User
import jp.co.cyberagent.dojo2019.Presentation.UserIndex.UserIndexActivity
import jp.co.cyberagent.dojo2019.R
import kotlin.concurrent.thread

class UserShowActivity : AppCompatActivity() {

    private var captureURL: String = ""
    private var database: AppDatabase? = null
    private var user: User? = null
    private lateinit var iamText: TextView
    private lateinit var githubText: TextView
    private lateinit var twitterText: TextView
    private lateinit var userListButton: Button
    private lateinit var githubUserImage: ImageView
    private lateinit var githubButton: Button
    private lateinit var twitterButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_show)

        database = AppDatabase.getDatabase(this)

        iamText = findViewById(R.id.user_iam)
        githubText = findViewById(R.id.user_github)
        twitterText = findViewById(R.id.user_twitter)
        userListButton = findViewById(R.id.btn_user_list)
        githubUserImage = findViewById(R.id.user_github_image)
        githubButton = findViewById(R.id.btn_github)
        twitterButton = findViewById(R.id.btn_twitter)

        val dataUri = intent?.data
        if (dataUri == null) {
            captureURL = intent?.getStringExtra("url").toString()
        } else {
            captureURL = dataUri.toString()
        }
        createUserFromUri()

        userListButton.setOnClickListener {
            navigateUserList()
        }

        githubButton.setOnClickListener {
            val githubURL = "https://github.com/${user?.githubID}"
            navigateWebView(this, githubURL)
        }

        twitterButton.setOnClickListener {
            val twitterURL = "https://twitter.com/${user?.twitterID}"
            navigateWebView(this, twitterURL)
        }
    }

    private fun createUserFromUri() {
        var captureData = captureURL.split("?")
        var splitData = captureData[1].split("&", "=")
        user = User.create(splitData[1], splitData[5], splitData[3])
        val userData = user?: return
        thread {
            database?.userDao()?.insert(userData)
        }
        iamText.text = user?.iam
        githubText.text = user?.githubID
        twitterText.text = user?.twitterID

        val imagepath = "https://github.com/"+ splitData[5] +".png"
        Picasso.get()
            .load(imagepath)
            .resize(300, 300)
            .centerCrop()
            .into(githubUserImage)
    }

    private fun navigateUserList() {
        val intent = Intent(this, UserIndexActivity::class.java)
        startActivity(intent)
    }

    private fun navigateWebView(context: Context, url: String) {
        val intent = Intent(context, WebViewActivity::class.java)
        intent.putExtra("url", url)
        context.startActivity(intent)

    }
}