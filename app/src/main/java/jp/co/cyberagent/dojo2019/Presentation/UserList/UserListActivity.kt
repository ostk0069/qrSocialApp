package jp.co.cyberagent.dojo2019.Presentation.UserList

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import jp.co.cyberagent.dojo2019.R

class UserListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_list)

        val listView = findViewById<ListView>(R.id.user_list)
        listView.adapter = UserListAdapter(this)
    }
}