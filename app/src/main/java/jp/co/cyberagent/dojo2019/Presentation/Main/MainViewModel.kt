package jp.co.cyberagent.dojo2019.Presentation.Main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel: ViewModel() {

    var liveGithubText: MutableLiveData<String> = MutableLiveData()
}