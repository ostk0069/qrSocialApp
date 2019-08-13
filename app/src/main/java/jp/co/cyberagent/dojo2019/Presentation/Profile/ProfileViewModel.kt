package jp.co.cyberagent.dojo2019.Presentation.Profile

import android.app.Application
import android.graphics.Color
import androidx.annotation.ColorInt
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import jp.co.cyberagent.dojo2019.Repository.AdminUserRepository

class ProfileViewModel(application: Application): AndroidViewModel(application) {

    private val repository = AdminUserRepository(application)
    var validationMessage: MutableLiveData<String> = MutableLiveData()

    fun fetchIam(): String {
        return repository.fetchIam()
    }

    fun fetchGithubID(): String {
        return repository.fetchGithubID()
    }

    fun fetchTwitterID(): String {
        return repository.fetchTwitterID()
    }

    fun fetchValidationMessage(): String {
        val length = validationMessage.toString().length
        if (length == 0) {
            return "GitHubIDを入力してください"
        } else if (length > 4) {
            return "4文字以上必要です"
        } else {
            return "Looks Good!"
        }
    }

    fun fetchValidationMessageColor(): Int {
        val length = validationMessage.toString().length
        var colorString: String
        if (length == 0) {
            colorString = "#d0184c"
        } else if (length > 4) {
            colorString = "#d0184c"
        } else {
            colorString = "#d0184c"
        }
        return Color.parseColor(colorString)
    }
}