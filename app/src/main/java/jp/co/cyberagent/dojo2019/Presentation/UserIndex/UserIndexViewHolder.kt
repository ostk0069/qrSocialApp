package jp.co.cyberagent.dojo2019.Presentation.UserIndex

import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import jp.co.cyberagent.dojo2019.R

class UserIndexViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    val iamTextView: TextView = view.findViewById(R.id.user_iam)
    val githubTextView: TextView = view.findViewById(R.id.user_github)
    val twitterTextView: TextView = view.findViewById(R.id.user_twitter)
    val githubButton: Button = view.findViewById(R.id.btn_github)
    val twitterButton: Button = view.findViewById(R.id.btn_twitter)
}