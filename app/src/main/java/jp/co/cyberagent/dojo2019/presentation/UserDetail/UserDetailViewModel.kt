package jp.co.cyberagent.dojo2019.presentation.UserDetail

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import jp.co.cyberagent.dojo2019.Entity.User
import jp.co.cyberagent.dojo2019.Repository.UserRepository

class UserDetailViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = UserRepository(application)

    fun findUserBy(uid: Int): LiveData<User> {
        return repository.fetchUserBy(uid)
    }
}