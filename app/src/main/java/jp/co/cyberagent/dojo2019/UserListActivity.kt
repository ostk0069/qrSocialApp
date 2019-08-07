package jp.co.cyberagent.dojo2019

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class UserListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_list)

        val listView = findViewById<ListView>(R.id.user_list)
        listView.adapter = UserListAdapter(this)
    }
}