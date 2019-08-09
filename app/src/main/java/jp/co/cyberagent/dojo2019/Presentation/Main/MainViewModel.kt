package jp.co.cyberagent.dojo2019.Presentation.Main

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import jp.co.cyberagent.dojo2019.Repository.MainRepository

class MainViewModel: ViewModel() {

    var liveGithubText: MutableLiveData<String> = MutableLiveData()

    fun fetchText(context: Context) {
        liveGithubText.value = MainRepository().fetchText(context)
    }

    fun fetchText(context: Context, listener: (String) -> Unit) {
        listener(MainRepository().fetchText(context))
    }
}