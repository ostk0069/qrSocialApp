package jp.co.cyberagent.dojo2019.Database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import jp.co.cyberagent.dojo2019.Entity.User

@Dao
interface UserDao {

    @Query("SELECT * FROM user")
    suspend fun getAll(): List<User>

    @Query("SELECT * FROM user")
    fun getLiveUsers(): LiveData<List<User>>

    @Query("SELECT * FROM user WHERE uid = :uid")
    fun findUserBy(uid: Int): LiveData<User>

    @Insert
    suspend fun insert(user: User)

    @Query("DELETE FROM user WHERE uid = :uid")
    suspend fun deleteByUid(uid: Int)

    @Query("SELECT * FROM user WHERE github_id = :githubID")
    suspend fun findUserByGithubId(githubID: String): User?
}