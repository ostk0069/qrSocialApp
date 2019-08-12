package jp.co.cyberagent.dojo2019.Repository

import android.content.Context

class AdminUserRepository(context: Context) {

    private val setData = context.getSharedPreferences("ca_dojo", Context.MODE_PRIVATE)

    fun fetchIam(): String {
        val iam = setData.getString("¥iam", "")
        return iam.orEmpty()
    }

    fun fetchGithubID(): String {
        val githubID = setData.getString("GithubID", "")
        return githubID.orEmpty()
    }

    fun fetchTwitterID(): String {
        val twitterID = setData.getString("twitterID", "")
        return twitterID.orEmpty()
    }
}