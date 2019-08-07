package jp.co.cyberagent.dojo2019

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlin.concurrent.thread

class UserIndexActivity : AppCompatActivity() {

    private var database: AppDatabase? = null
    private var userList: List<User> = emptyList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_index)

        val context = applicationContext
        database = AppDatabase.getDatabase(context)
        thread {
            userList = database?.userDao()?.getAll().orEmpty()
            findViewById<RecyclerView>(R.id.user_index).also { recyclerView: RecyclerView ->
                recyclerView.adapter = UserIndexAdapter(this, userList)
                recyclerView.layoutManager = LinearLayoutManager(this)
            }
        }
    }
}