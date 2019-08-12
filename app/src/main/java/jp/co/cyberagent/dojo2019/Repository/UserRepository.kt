package jp.co.cyberagent.dojo2019.Repository

import androidx.lifecycle.LiveData
import jp.co.cyberagent.dojo2019.Database.UserDao
import jp.co.cyberagent.dojo2019.Entity.User

class UserRepository(private val dao: UserDao) {

//    suspend fun getUsers(): LiveData<List<User>> {
//        return dao.getLiveUsers()
//    }
}