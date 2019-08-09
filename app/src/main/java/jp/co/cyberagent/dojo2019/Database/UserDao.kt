package jp.co.cyberagent.dojo2019.Database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import jp.co.cyberagent.dojo2019.Model.User

@Dao
interface UserDao {

    @Query("SELECT * FROM user")
    suspend fun getAll(): List<User>

    @Insert
    suspend fun insert(user: User)

    @Query("DELETE FROM user WHERE uid = :uid")
    suspend fun deleteByUid(uid: Int)
}