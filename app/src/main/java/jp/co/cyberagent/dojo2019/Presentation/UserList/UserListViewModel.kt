package jp.co.cyberagent.dojo2019.Presentation.UserList

import android.app.Application
import androidx.lifecycle.*
import jp.co.cyberagent.dojo2019.Entity.User
import jp.co.cyberagent.dojo2019.Repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class UserListViewModel(application: Application): AndroidViewModel(application) {

    private val repository: UserRepository = UserRepository(application)

    fun deleteUser(uid: Int) {
        viewModelScope.launch {
            withContext(Dispatchers.Main) {
                repository.deleteByUid(uid)
            }
        }
    }

    fun getLiveUsers(): LiveData<List<User>> {
        return repository.getLiveDataUsers()
    }
}