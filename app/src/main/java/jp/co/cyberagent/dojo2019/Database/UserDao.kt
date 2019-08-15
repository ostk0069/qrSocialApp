package jp.co.cyberagent.dojo2019.Database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import jp.co.cyberagent.dojo2019.Entity.User

@Dao
interface UserDao {

    @Query("SELECT * FROM user")
    fun getUsers(): LiveData<List<User>>

    @Query("SELECT * FROM user WHERE uid = :uid")
    fun findUserBy(uid: Int): LiveData<User>

    @Insert
    suspend fun insert(user: User)

    @Query("DELETE FROM user WHERE uid = :uid")
    suspend fun deleteBy(uid: Int)

    @Query("SELECT * FROM user WHERE github_id = :githubID")
    suspend fun findUserBy(githubID: String): User?
}