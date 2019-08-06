package jp.co.cyberagent.dojo2019

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class User {

    @PrimaryKey(autoGenerate = true) var uid: Int = 0
    @ColumnInfo(name = "iam") var iam: String? = null
    @ColumnInfo(name = "github_id") var githubID: String? = null
    @ColumnInfo(name = "twitter_id") var twitterID: String? = null

    companion object {
        fun create(iam: String, githubID: String, twitterID: String?): User {
            val user = User()
            user.uid = 0
            user.iam = iam
            user.githubID = githubID
            user.twitterID = twitterID
            return user
        }
    }
}
