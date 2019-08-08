package jp.co.cyberagent.dojo2019.Presentation.UserIndex

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import jp.co.cyberagent.dojo2019.R
import jp.co.cyberagent.dojo2019.Model.User
import jp.co.cyberagent.dojo2019.Presentation.WebViewActivity

class UserIndexAdapter(private val context: Context, private val userList: MutableList<User>) :
    RecyclerView.Adapter<UserIndexViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserIndexViewHolder {
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.user_cell, parent, false)
        return UserIndexViewHolder(view)
    }

    override fun getItemCount(): Int = userList.size

    override fun onBindViewHolder(holder: UserIndexViewHolder, position: Int) {
        val user = userList[position]
        holder.iamTextView.text = user.iam
        holder.githubTextView.text = user.githubID
        holder.twitterTextView.text = user.twitterID

        val context = holder.itemView.context
        holder.githubButton.setOnClickListener {
            val githubURL = "https://github.com/${user.githubID}"
            navigateWebView(context, githubURL)
        }
        holder.twitterButton.setOnClickListener {
            val twitterURL = "https://twitter.com/${user.twitterID}"
            navigateWebView(context, twitterURL)
        }
    }

    fun updateUserList(users: MutableList<User>) {
        userList.addAll(users)
        notifyDataSetChanged()
    }

    private fun navigateWebView(context: Context, url: String) {
        val intent = Intent(context, WebViewActivity::class.java)
        intent.putExtra("url", url)
        context.startActivity(intent)

    }
}