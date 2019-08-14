package jp.co.cyberagent.dojo2019.presentation.UserList

import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import jp.co.cyberagent.dojo2019.R

class UserListViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    val iamTextView: TextView = view.findViewById(R.id.user_iam)
    val githubTextView: TextView = view.findViewById(R.id.user_github)
    val twitterTextView: TextView = view.findViewById(R.id.user_twitter)
    val githubLinearLayout: LinearLayout = view.findViewById(R.id.github_linear_layout)
    val twitterLinearLayout: LinearLayout = view.findViewById(R.id.twitter_linear_layout)
    val datetimeTextView: TextView = view.findViewById(R.id.datetime)
}