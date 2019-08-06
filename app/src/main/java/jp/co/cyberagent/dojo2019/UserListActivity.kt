package jp.co.cyberagent.dojo2019

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlin.concurrent.thread

class UserListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_list)

        val listView = findViewById<ListView>(R.id.user_list)
        listView.adapter = UserListAdapter(this)
    }

    private class UserListAdapter(context: Context): BaseAdapter() {

        private val mContext: Context
        private var database: AppDatabase? = null
        private var users: List<User> = emptyList()

        private val names = arrayListOf<String>(
            "Donald Trump", "steve Jobs", "Tim Cook", "Watanabe Teppei", "Takuma Osada"
        )

        init {
            mContext = context
            database = AppDatabase.getDatabase(mContext)
            thread {
                users = database?.userDao()?.getAll().orEmpty()
            }
        }

        override fun getCount(): Int {
            return users.size
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getItem(p0: Int): Any {
            return "TEST String"
        }

        override fun getView(position: Int, convertView: View?, viewGroup: ViewGroup?): View {
            val layoutInflater = LayoutInflater.from(mContext)
            val userList = layoutInflater.inflate(R.layout.user_row, viewGroup, false)

            val userIAM = userList.findViewById<TextView>(R.id.user_iam)
            userIAM.text = users.get(position).iam
            val userGithub = userList.findViewById<TextView>(R.id.user_github)
            userGithub.text = users.get(position).githubID
            val userTwitter = userList.findViewById<TextView>(R.id.user_twitter)
            userTwitter.text = users.get(position).twitterID
            return userList

        }
    }
}