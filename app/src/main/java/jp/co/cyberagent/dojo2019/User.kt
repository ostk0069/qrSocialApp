package jp.co.cyberagent.dojo2019

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

//data class User(val id: Int = -1, val iam: String, val tw: String, val gh: String)

@Entity
data class User(
    @PrimaryKey(autoGenerate = true) val uid: Int = 0,
    @ColumnInfo(name = "iam") val iam: String?,
    @ColumnInfo(name = "github_id") val githubID: String?
)