package jp.co.cyberagent.dojo2019.Presentation.UserList

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import jp.co.cyberagent.dojo2019.Entity.User
import jp.co.cyberagent.dojo2019.Presentation.Common.DateTime
import jp.co.cyberagent.dojo2019.Presentation.Common.WebViewActivity
import jp.co.cyberagent.dojo2019.R

class UserListAdapter(private val context: Context) : RecyclerView.Adapter<UserListViewHolder>() {

    var userList: MutableList<User> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserListViewHolder {
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.user_cell, parent, false)
        return UserListViewHolder(view)
    }

    override fun getItemCount(): Int = userList.size

    override fun onBindViewHolder(holder: UserListViewHolder, position: Int) {
        val user = userList[position]
        holder.iamTextView.text = user.iam
        holder.githubTextView.text = user.githubID
        holder.twitterTextView.text = user.twitterID
        holder.datetimeTextView.text = DateTime().getCustomizedTime(user.createdAt)

        val context = holder.itemView.context
        holder.githubLinearLayout.setOnClickListener {
            val githubURL = "https://github.com/${user.githubID}"
            navigateWebView(context, githubURL)
        }
        holder.twitterLinearLayout.setOnClickListener {
            val twitterURL = "https://twitter.com/${user.twitterID}"
            navigateWebView(context, twitterURL)
        }
    }

    fun updateUserList(users: MutableList<User>) {
        userList = users
        notifyDataSetChanged()
    }

    private fun navigateWebView(context: Context, url: String) {
        val intent = Intent(context, WebViewActivity::class.java)
        intent.putExtra("url", url)
        context.startActivity(intent)
    }
}