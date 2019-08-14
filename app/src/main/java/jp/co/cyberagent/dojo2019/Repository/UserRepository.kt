package jp.co.cyberagent.dojo2019.Repository

import android.content.Context
import androidx.lifecycle.LiveData
import jp.co.cyberagent.dojo2019.Database.AppDatabase
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

    fun getLiveDataUsers(): LiveData<List<User>> {
        return database.userDao().getLiveUsers()
    }

    fun fetchUserBy(uid: Int): LiveData<User> {
        return database.userDao().findUserBy(uid)
    }
}