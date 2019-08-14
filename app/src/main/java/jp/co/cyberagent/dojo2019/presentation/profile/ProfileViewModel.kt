package jp.co.cyberagent.dojo2019.presentation.profile

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import jp.co.cyberagent.dojo2019.R
import jp.co.cyberagent.dojo2019.Repository.AdminUserRepository

class ProfileViewModel(application: Application): AndroidViewModel(application) {

    private val repository = AdminUserRepository(application)
    var validationMessage: MutableLiveData<String> = MutableLiveData()
    var validationMessageColor: MutableLiveData<Int> = MutableLiveData()

    init {
        validationMessageColor.value = R.color.colorDark
    }

    fun fetchIam(): String {
        return repository.fetchIam()
    }

    fun fetchGithubID(): String {
        return repository.fetchGithubID()
    }

    fun fetchTwitterID(): String {
        return repository.fetchTwitterID()
    }

    fun fetchValidationMessage(value: Int?) {
        val value = value?: return
        if (value == 0) {
            validationMessage.value = "GitHubIDを入力してください"
        } else if (value < 4) {
            validationMessage.value = "4文字以上必要です"
        } else {
            validationMessage.value = "Looks Good!"
        }
    }

    fun fetchValidationMessageColor(value: Int?) {
        val value = value?: return
        if (value == 0) {
            validationMessageColor.value = R.color.colorAccent
        } else if (value < 4) {
            validationMessageColor.value = R.color.colorWaring
        } else {
            validationMessageColor.value = R.color.colorPrimary
        }
    }
}