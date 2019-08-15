package jp.co.cyberagent.dojo2019.presentation.UserDetail

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import jp.co.cyberagent.dojo2019.Entity.GithubRepository
import jp.co.cyberagent.dojo2019.R
import jp.co.cyberagent.dojo2019.presentation.Common.WebViewActivity

class UserDetailAdapter(private val context: Context): RecyclerView.Adapter<UserDetailViewHolder>() {

    var repoList: MutableList<GithubRepository> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserDetailViewHolder {
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.user_detail_repo_cell, parent, false)
        return UserDetailViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserDetailViewHolder, position: Int) {
        val repo = repoList[position]
        holder.nameTextView.text = repo.name
        holder.languageTextView.text = repo.language
        holder.starTextView.text = repo.star.toString()

        holder.layout.setOnClickListener {
            val intent = Intent(context, WebViewActivity::class.java)
            intent.putExtra("url", repo.url)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int = repoList.size

    fun update(repos: MutableList<GithubRepository>) {
        repoList = repos
        notifyDataSetChanged()
    }

}