package jp.co.cyberagent.dojo2019.Presentation.Profile

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import jp.co.cyberagent.dojo2019.Repository.AdminUserRepository

class ProfileViewModel(context: Context): ViewModel() {

    // TODO: fix to liveData string

    private val repository = AdminUserRepository(context)

    fun fetchIam(): String {
        return repository.fetchIam()
    }

    fun fetchGithubID(): String {
        return repository.fetchGithubID()
    }

    fun fetchTwitterID(): String {
        return repository.fetchTwitterID()
    }

// var liveGithubText: MutableLiveData<String> = MutableLiveData()
//
//    fun fetchText(context: Context) {
//        liveGithubText.value = AdminUserRepository().fetchText(context)
//    }
//
//    fun fetchText(context: Context, listener: (String) -> Unit) {
//        listener(AdminUserRepository().fetchText(context))
//    }
}