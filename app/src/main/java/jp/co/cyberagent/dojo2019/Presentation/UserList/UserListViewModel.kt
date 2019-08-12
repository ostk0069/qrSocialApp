package jp.co.cyberagent.dojo2019.Presentation.UserList

import android.app.Application
import androidx.lifecycle.*
import jp.co.cyberagent.dojo2019.Database.AppDatabase
import jp.co.cyberagent.dojo2019.Repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class UserListViewModel(application: Application): AndroidViewModel(application) {

    private val repository: UserRepository

    init {
        val dao = AppDatabase.getDatabase(application).userDao()
        repository = UserRepository(dao)
    }

    fun getUsers() {
        viewModelScope.launch {
            withContext(Dispatchers.Main) {
//                repository.getUsers()
            }
        }
    }
}