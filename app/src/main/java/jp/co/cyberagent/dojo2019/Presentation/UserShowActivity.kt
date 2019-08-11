package jp.co.cyberagent.dojo2019.Presentation

import android.content.Context
import android.content.Intent
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.net.toUri
import androidx.lifecycle.lifecycleScope
import com.squareup.picasso.Picasso
import jp.co.cyberagent.dojo2019.Database.AppDatabase
import jp.co.cyberagent.dojo2019.Entity.User
//import jp.co.cyberagent.dojo2019.Presentation.UserIndex.UserIndexActivity
import jp.co.cyberagent.dojo2019.R
import kotlinx.coroutines.launch

class UserShowActivity : AppCompatActivity() {

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

        var captureUri: Uri?
        if (intent?.data == null) {
            captureUri = intent?.getStringExtra("url")?.toUri()
        } else {
            captureUri = intent?.data
        }
        createUserFromUri(captureUri)
        iamText.text = user?.iam
        githubText.text = user?.githubID
        twitterText.text = user?.twitterID
        showGithubImage()

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

    private fun createUserFromUri(uri: Uri?) {
        val uri: Uri = uri?: return
        val iam: String = uri.getQueryParameter("iam").toString()
        val githubID: String = uri.getQueryParameter("gh").toString()
        val twitterID: String = uri.getQueryParameter("tw").toString()
        lifecycleScope.launch {
            val existUser = database?.userDao()?.findUserByGithubId(githubID)
            if (existUser != null) {
                // there is user with same github id exist. delete old & save new.
                database?.userDao()?.deleteByUid(existUser.uid)
            }
            user = User.create(iam, githubID, twitterID)
            val userData = user?: return@launch
            database?.userDao()?.insert(userData)
        }
    }

    private fun navigateUserList() {
//        val intent = Intent(this, UserIndexActivity::class.java)
//        startActivity(intent)
    }

    private fun navigateWebView(context: Context, url: String) {
        val intent = Intent(context, WebViewActivity::class.java)
        intent.putExtra("url", url)
        context.startActivity(intent)
    }

    private fun showGithubImage() {
        val githubID: String = user?.githubID.toString()
        if (githubID.length < 4) {
            val drawable: Drawable? = getDrawable(R.drawable.failed)
            drawable?: return
            githubUserImage.setImageDrawable(drawable)
        } else {
            Picasso.get()
                .load("https://github.com/$githubID.png")
                .resize(300, 300)
                .centerCrop()
                .into(githubUserImage)
        }

    }
}