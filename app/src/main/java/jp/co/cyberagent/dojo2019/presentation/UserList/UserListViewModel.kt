package jp.co.cyberagent.dojo2019.presentation.UserList

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import jp.co.cyberagent.dojo2019.Entity.User
import jp.co.cyberagent.dojo2019.Repository.UserRepository
import kotlinx.coroutines.launch

class UserListViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: UserRepository = UserRepository(application)

    fun deleteUserBy(uid: Int) {
        viewModelScope.launch {
            repository.deleteUserBy(uid)
        }
    }

    fun getUsers(): LiveData<List<User>> {
        return repository.getUsers()
    }
}