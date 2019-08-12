package jp.co.cyberagent.dojo2019.Repository

import android.content.Context
import androidx.lifecycle.LiveData
import jp.co.cyberagent.dojo2019.Database.AppDatabase
import jp.co.cyberagent.dojo2019.Database.UserDao
import jp.co.cyberagent.dojo2019.Entity.User

class UserRepository(context: Context) {

    private val database = AppDatabase.getDatabase(context)

    suspend fun insert(user: User) {
        database.userDao().insert(user)
    }

    suspend fun deleteByUid(uid: Int) {
        database.userDao().deleteByUid(uid)
    }

    suspend fun findUserBy(githubID: String): User? {
        return database.userDao().findUserByGithubId(githubID)
    }

//    suspend fun getUsers(): LiveData<List<User>> {
//        return dao.getLiveUsers()
//    }
}