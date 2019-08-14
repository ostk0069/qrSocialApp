package jp.co.cyberagent.dojo2019.Entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class User {

    @PrimaryKey(autoGenerate = true) var uid: Int = 0
    @ColumnInfo(name = "iam") var iam: String? = null
    @ColumnInfo(name = "github_id") var githubID: String? = null
    @ColumnInfo(name = "twitter_id") var twitterID: String? = null
    @ColumnInfo(name = "created_at") var createdAt: Long? = null

    companion object {
        fun create(iam: String, githubID: String, twitterID: String?, createdAt: Long?): User {
            val user = User()
            user.uid = 0
            user.iam = iam
            user.githubID = githubID
            user.twitterID = twitterID
            user.createdAt = createdAt
            return user
        }
    }
}
