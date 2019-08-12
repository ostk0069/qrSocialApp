package jp.co.cyberagent.dojo2019.Presentation.UserShow

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import jp.co.cyberagent.dojo2019.Entity.User
import jp.co.cyberagent.dojo2019.Repository.UserRepository
import kotlinx.coroutines.launch

class UserShowViewModel(context: Context) : ViewModel() {

    private val repository = UserRepository(context)

    suspend fun deleteByUid(uid: Int) {
        repository.deleteByUid(uid)
    }

    suspend fun insertUser(user: User?) {
        val user = user?: return
        repository.insert(user)
    }

    suspend fun deleteUserIfGithubIDAvailable(githubID: String) {
        val existUser = repository.findUserBy(githubID)
        if (existUser != null) {
            repository.deleteByUid(existUser.uid)
        }
    }

    fun insertOrUpdateUser(id: String, user: User?) {
        viewModelScope.launch {
            deleteUserIfGithubIDAvailable(id)
            insertUser(user)
        }
    }
}