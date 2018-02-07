package com.dev.android.wanti.poc_repo.sqlite

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import com.dev.android.wanti.poc_repo.repositories.UserRepository

/**
 * Created on 05.02.18.
 */

class LocalDatabase (context: Context) : SQLiteOpenHelper(context, DATABASE_NAME,null, DATABASE_VERSION) {

    companion object {
        val DATABASE_NAME = "test_v3.db"
        val DATABASE_VERSION = 1

    }

    override fun onCreate(db: SQLiteDatabase?) {
        createSchema(db)
        Log.d("Test","table users created")
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        deleteTables(db)
        onCreate(db)

        Log.d("Test","update db")
    }


    override fun onDowngrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        super.onDowngrade(db, oldVersion, newVersion)

        onUpgrade(db, oldVersion, newVersion)

        Log.d("Test","downgrade !!")
    }

    private fun createSchema(db: SQLiteDatabase?) {
        Log.d("Test",UserRepository.SQL_CREATE_USERS)
        db?.execSQL(UserRepository.SQL_CREATE_USERS)
    }

    private fun deleteTables(db: SQLiteDatabase?) {
        db?.execSQL(UserRepository.SQL_DELETE_USERS)
    }
}