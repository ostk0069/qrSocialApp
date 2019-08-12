package jp.co.cyberagent.dojo2019.Presentation.UserList

import android.app.Application
import android.content.Context
import androidx.lifecycle.*
import jp.co.cyberagent.dojo2019.Database.AppDatabase
import jp.co.cyberagent.dojo2019.Repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class UserListViewModel(context: Context): ViewModel() {

    private val repository: UserRepository = UserRepository(context)

    fun getUsers() {
        viewModelScope.launch {
            withContext(Dispatchers.Main) {
//                repository.getUsers()
            }
        }
    }
}