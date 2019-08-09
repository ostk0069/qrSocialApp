package jp.co.cyberagent.dojo2019.Repository

import android.content.Context

class MainRepository {

    fun fetchText(context: Context): String {
        val setData = context.getSharedPreferences("ca_dojo", Context.MODE_PRIVATE)
        val githubID = setData.getString("GithubID", "")
        githubID?: return ""
        return githubID
    }
}