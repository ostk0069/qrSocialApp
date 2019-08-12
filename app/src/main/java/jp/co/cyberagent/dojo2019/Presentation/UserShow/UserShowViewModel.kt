package jp.co.cyberagent.dojo2019.Presentation.UserShow

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import jp.co.cyberagent.dojo2019.Database.AppDatabase
import jp.co.cyberagent.dojo2019.Entity.User
import kotlinx.coroutines.launch

class UserShowViewModel(context: Context) : ViewModel() {

    private val database = AppDatabase.getDatabase(context)

    suspend fun deleteByUid(uid: Int) {
        database?.userDao()?.deleteByUid(uid)
    }

    suspend fun insertUser(user: User?) {
        val user = user?: return
        database?.userDao()?.insert(user)
    }

    suspend fun deleteUserIfGithubIDAvailable(id: String) {
        val existUser = database?.userDao()?.findUserByGithubId(id)
        if (existUser != null) {
            database?.userDao()?.deleteByUid(existUser.uid)
        }
    }

    fun insertOrUpdateUser(id: String, user: User?) {
        viewModelScope.launch {
            deleteUserIfGithubIDAvailable(id)
            insertUser(user)
        }
    }
}