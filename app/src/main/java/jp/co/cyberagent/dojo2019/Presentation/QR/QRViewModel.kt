package jp.co.cyberagent.dojo2019.Presentation.QR

import android.content.Context
import android.net.Uri
import androidx.lifecycle.ViewModel
import jp.co.cyberagent.dojo2019.Repository.AdminUserRepository

class QRViewModel(context: Context): ViewModel() {

    // TODO: fix to liveData string

    private var repository = AdminUserRepository(context)

    fun fetchEncodedUserIam(): String {
        val iam = repository.fetchIam()
        return Uri.encode(iam)
    }

    fun fetchEncodedUserGitHubID(): String {
        val githubId = repository.fetchGithubID()
        return Uri.encode(githubId)
    }

    fun fetchEncodedUserTwitterID(): String {
        val twitterId = repository.fetchTwitterID()
        return Uri.encode(twitterId)
    }
}