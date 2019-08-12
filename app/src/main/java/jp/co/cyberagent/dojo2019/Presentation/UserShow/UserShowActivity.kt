package jp.co.cyberagent.dojo2019.Presentation.UserShow

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
import jp.co.cyberagent.dojo2019.Entity.User
import jp.co.cyberagent.dojo2019.Presentation.BottomTab.BottomTabActivity
import jp.co.cyberagent.dojo2019.Presentation.Common.WebViewActivity
import jp.co.cyberagent.dojo2019.R
import kotlinx.coroutines.launch

class UserShowActivity : AppCompatActivity() {

    private var user: User? = null
    private lateinit var viewModel: UserShowViewModel
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

        iamText = findViewById(R.id.user_iam)
        githubText = findViewById(R.id.user_github)
        twitterText = findViewById(R.id.user_twitter)
        userListButton = findViewById(R.id.btn_user_list)
        githubUserImage = findViewById(R.id.user_github_image)
        githubButton = findViewById(R.id.btn_github)
        twitterButton = findViewById(R.id.btn_twitter)
        viewModel = UserShowViewModel(this)

        createUserFromUri()
        showGithubImage()
        iamText.text = user?.iam
        githubText.text = user?.githubID
        twitterText.text = user?.twitterID

        userListButton.setOnClickListener {
            navigateUserList()
        }

        githubButton.setOnClickListener {
            val githubURL = "https://github.com/${user?.githubID}"
            navigateWebView(githubURL)
        }

        twitterButton.setOnClickListener {
            val twitterURL = "https://twitter.com/${user?.twitterID}"
            navigateWebView(twitterURL)
        }
    }

    private fun createUserFromUri() {
        var captureUri: Uri?
        if (intent?.data == null) {
            captureUri = intent?.getStringExtra("url")?.toUri()
        } else {
            captureUri = intent?.data
        }
        val uri: Uri = captureUri?: return
        val iam: String = uri.getQueryParameter("iam").toString()
        val githubID: String = uri.getQueryParameter("gh").toString()
        val twitterID: String = uri.getQueryParameter("tw").toString()
        user = User.create(iam, githubID, twitterID)
        viewModel.insertOrUpdateUser(githubID, user)
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

    private fun navigateUserList() {
        // TODO: select user list tab to be navigated
        val intent = Intent(this, BottomTabActivity::class.java)
        startActivity(intent)
    }

    private fun navigateWebView(url: String) {
        val intent = Intent(this, WebViewActivity::class.java)
        intent.putExtra("url", url)
        startActivity(intent)
    }
}