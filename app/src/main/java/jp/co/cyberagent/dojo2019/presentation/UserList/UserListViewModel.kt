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

    fun deleteUser(uid: Int) {
        viewModelScope.launch {
            repository.deleteByUid(uid)
        }
    }

    fun getLiveUsers(): LiveData<List<User>> {
        return repository.getLiveDataUsers()
    }
}