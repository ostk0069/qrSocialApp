package jp.co.cyberagent.dojo2019.Database

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import jp.co.cyberagent.dojo2019.User
import java.util.*

//class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
//
//    private val CREATE_USER_TABLE = ("CREATE TABLE " + TABLE_USER + "("
//            + COLUMN_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_USER_NAME + " TEXT,"
//            + COLUMN_USER_EMAIL + " TEXT," + COLUMN_USER_PASSWORD + " TEXT" + ")")
//
//    private val DROP_USER_TABLE = "DROP TABLE IF EXISTS $TABLE_USER"
//
//    override fun onCreate(db: SQLiteDatabase) {
//        db.execSQL(CREATE_USER_TABLE)
//    }
//
//    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
//
//        //Drop User Table if exist
//        db.execSQL(DROP_USER_TABLE)
//
//        // Create tables again
//        onCreate(db)
//
//    }
//
//    fun addUser(user: User) {
//        val db = this.writableDatabase
//
//        val values = ContentValues()
//        values.put(COLUMN_USER_NAME, user.name)
//        values.put(COLUMN_USER_EMAIL, user.email)
//        values.put(COLUMN_USER_PASSWORD, user.password)
//
//        // Inserting Row
//        db.insert(TABLE_USER, null, values)
//        db.close()
//    }
//}