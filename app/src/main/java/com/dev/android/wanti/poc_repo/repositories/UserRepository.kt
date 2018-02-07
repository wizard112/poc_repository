package com.dev.android.wanti.poc_repo.repositories

import android.content.ContentValues
import android.database.sqlite.SQLiteOpenHelper
import android.provider.BaseColumns
import android.util.Log
import com.dev.android.wanti.modellib.User
import com.dev.android.wanti.poc_repo.MainActivity
import com.dev.android.wanti.poc_repo.sqlite.LocalDatabase

/**
 * Created on 05.02.18.
 */

class UserRepository (db : SQLiteOpenHelper): Repository<User> {

    companion object {
        val SQL_CREATE_USERS = "CREATE TABLE ${User.UserEntry.TABLE_NAME}" +
                " (${BaseColumns._ID} INTEGER PRIMARY KEY AUTOINCREMENT," +
                "${User.UserEntry.COLUMN_NAME_NAME} TEXT," +
                "${User.UserEntry.COLUMN_NAME_FIRSTNAME} TEXT);"

        val SQL_DELETE_USERS = "DROP TABLE IF EXISTS ${User.UserEntry.TABLE_NAME}"

        private val SQL_ROWS_USER = "SELECT * FROM ${User.UserEntry.TABLE_NAME}"

    }

    private var database : SQLiteOpenHelper? = null
    init {
        database = db
    }

    override fun add(item: User) {
        val db = database!!.writableDatabase
        val values = ContentValues().apply {
            put(User.UserEntry.COLUMN_NAME_NAME,item.name)
            put(User.UserEntry.COLUMN_NAME_FIRSTNAME, item.firstName)
        }

        val newRowId = db.insert(User.UserEntry.TABLE_NAME,null,values)
        db.close()
        Log.d("Test","add user : ".plus(newRowId))
    }

    override fun remove(item: User) {
        val db = database?.writableDatabase
        val total = db?.delete(User.UserEntry.TABLE_NAME,null,null)
        db!!.close()
        Log.d("Test","total deleted ".plus(total))
    }

    override fun total(): Int {
        val db = database?.readableDatabase
        val cursor = db?.rawQuery(SQL_ROWS_USER,null)
        val count = cursor?.count
        cursor!!.close()
        db.close()
        return count!!
    }

    override fun read() {
        val db = database!!.readableDatabase

        //columns :
        val projection = arrayOf(BaseColumns._ID, User.UserEntry.COLUMN_NAME_NAME, User.UserEntry.COLUMN_NAME_FIRSTNAME)

        val sortOrder = "${BaseColumns._ID} DESC"
        val curosr = db!!.query(
                User.UserEntry.TABLE_NAME, // table
                projection,    //columns to return
                null, //columns for where clause
                null,  //values for where clause
                null,    //don't group
                null,     //don't filter
                sortOrder        // sort
        )

        with(curosr) {
            while (moveToNext()) {
                Log.d("Test",getLong(0).toString().plus(" ").plus(getString(1)).plus(" ").plus(getString(2)))
            }
        }
    }
}