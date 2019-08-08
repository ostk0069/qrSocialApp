package jp.co.cyberagent.dojo2019

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import kotlin.concurrent.thread

class UserListAdapter(context: Context): BaseAdapter() {

    private val mContext: Context
    private var database: AppDatabase? = null
    private var users: List<User> = emptyList()

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

        val user = users.get(position)
        val userIAM = userList.findViewById<TextView>(R.id.user_iam)
        userIAM.text = user.iam
        val userGithub = userList.findViewById<TextView>(R.id.user_github)
        userGithub.text = user.githubID
        val githubImageView = userList.findViewById<ImageView>(R.id.github_image)
        githubImageView.setImageResource(R.drawable.github)
        val userTwitter = userList.findViewById<TextView>(R.id.user_twitter)
        userTwitter.text = user.twitterID
        val twitterImageView = userList.findViewById<ImageView>(R.id.twitter_image)
        twitterImageView.setImageResource(R.drawable.twitter)
        val githubButton = userList.findViewById<Button>(R.id.btn_github)
        return userList
    }
}