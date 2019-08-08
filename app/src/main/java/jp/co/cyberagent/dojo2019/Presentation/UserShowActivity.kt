package jp.co.cyberagent.dojo2019.Presentation

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import jp.co.cyberagent.dojo2019.Database.AppDatabase
import jp.co.cyberagent.dojo2019.Model.User
import jp.co.cyberagent.dojo2019.Presentation.UserList.UserListActivity
import jp.co.cyberagent.dojo2019.R
import kotlin.concurrent.thread

class UserShowActivity : AppCompatActivity() {

    private var captureURL: String = ""
    private var database: AppDatabase? = null
    private lateinit var iamText: TextView
    private lateinit var githubText: TextView
    private lateinit var twitterText: TextView
    private lateinit var userListButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_show)

        val context = applicationContext
        database = AppDatabase.getDatabase(context)

        iamText = findViewById(R.id.user_iam)
        githubText = findViewById(R.id.user_github)
        twitterText = findViewById(R.id.user_twitter)
        userListButton = findViewById(R.id.btn_user_list)

        val dataUri = intent?.data
        captureURL = dataUri.toString()
        createUserFromUri(captureURL)

        userListButton.setOnClickListener {
            navigateUserList()
        }
    }

    private fun createUserFromUri(data: String) {
        var captureData = captureURL.split("?")
        var splitData = captureData[1].split("&", "=")
        val user = User.create(splitData[1], splitData[3], splitData[5])
        thread {
            database?.userDao()?.insert(user)
        }
        iamText.text = splitData[1]
        githubText.text = splitData[3]
        twitterText.text = splitData[5]
    }

    private fun navigateUserList() {
        val intent = Intent(this, UserListActivity::class.java)
        startActivity(intent)
    }

}