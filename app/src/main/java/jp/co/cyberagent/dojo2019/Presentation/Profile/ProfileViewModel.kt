package jp.co.cyberagent.dojo2019.Presentation.Profile

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import jp.co.cyberagent.dojo2019.Repository.AdminUserRepository

class ProfileViewModel(application: Application): AndroidViewModel(application) {

    // TODO: fix to liveData string

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