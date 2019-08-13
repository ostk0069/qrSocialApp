package jp.co.cyberagent.dojo2019.Presentation.Profile

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import jp.co.cyberagent.dojo2019.Repository.AdminUserRepository

class ProfileViewModel(application: Application): AndroidViewModel(application) {

    private val repository = AdminUserRepository(application)

    fun fetchIam(): String {
        return repository.fetchIam()
    }

    fun fetchGithubID(): String {
        return repository.fetchGithubID()
    }

    fun fetchTwitterID(): String {
        return repository.fetchTwitterID()
    }
}