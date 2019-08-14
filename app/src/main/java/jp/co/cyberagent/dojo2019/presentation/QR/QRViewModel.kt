package jp.co.cyberagent.dojo2019.presentation.QR

import android.app.Application
import android.net.Uri
import androidx.lifecycle.AndroidViewModel
import jp.co.cyberagent.dojo2019.Repository.AdminUserRepository

class QRViewModel(application: Application): AndroidViewModel(application) {

    private var repository = AdminUserRepository(application)

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