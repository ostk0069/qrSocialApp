package jp.co.cyberagent.dojo2019.Presentation.QR

import android.content.Context
import android.net.Uri
import androidx.lifecycle.ViewModel

class QRViewModel(private val context: Context): ViewModel() {

    // TODO: fix to liveData string

    private val setData = context.getSharedPreferences("ca_dojo", Context.MODE_PRIVATE)

    fun fetchUserIam(): String {
        val iam = setData?.getString("iam", "")
        return Uri.encode(iam)
    }

    fun fetchUserGitHubID(): String {
        val githubId = setData?.getString("GithubID", "")
        return Uri.encode(githubId)
    }

    fun fetchUserTwitterID(): String {
        val twitterId = setData?.getString("GithubID", "")
        return Uri.encode(twitterId)
    }
}