package jp.co.cyberagent.dojo2019.presentation.UserDetail

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import jp.co.cyberagent.dojo2019.R

class UserDetailViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    val nameTextView: TextView = view.findViewById(R.id.repo_name)
    val languageTextView: TextView = view.findViewById(R.id.repo_language)
    val starTextView: TextView = view.findViewById(R.id.repo_star)
}