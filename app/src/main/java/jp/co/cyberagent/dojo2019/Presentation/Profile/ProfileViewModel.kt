package jp.co.cyberagent.dojo2019.Presentation.Profile

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import jp.co.cyberagent.dojo2019.Repository.UserRepository

class ProfileViewModel: ViewModel() {

    var liveGithubText: MutableLiveData<String> = MutableLiveData()

    fun fetchText(context: Context) {
        liveGithubText.value = UserRepository().fetchText(context)
    }

    fun fetchText(context: Context, listener: (String) -> Unit) {
        listener(UserRepository().fetchText(context))
    }
}