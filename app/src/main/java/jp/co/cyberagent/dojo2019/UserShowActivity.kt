package jp.co.cyberagent.dojo2019

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
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
        var splitedData = captureData[1].split("&")
        if (splitedData.size < 3){
            Toast.makeText(this, "適切なIDを入力してください", Toast.LENGTH_LONG).show()
            return
        }
        val user = User.create(splitedData[0],splitedData[1],splitedData[2])
        insertUserData(user)
        iamText.text = splitedData[0]
        githubText.text = splitedData[1]
        twitterText.text = splitedData[2]


    }

    private fun insertUserData(user: User) {
        thread {
            database?.userDao()?.insert(user)
        }
    }

    private fun navigateUserList() {
        val intent = Intent(this, UserListActivity::class.java)
        startActivity(intent)
    }

}