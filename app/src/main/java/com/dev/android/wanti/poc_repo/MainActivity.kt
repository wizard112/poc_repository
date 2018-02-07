package com.dev.android.wanti.poc_repo

import android.database.DatabaseUtils
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.dev.android.wanti.poc_repo.repositories.UserRepository
import com.dev.android.wanti.poc_repo.sqlite.LocalDatabase
import com.dev.android.wanti.poc_repo.utils.DatabaseUtil

class MainActivity : AppCompatActivity() {

    private lateinit var repo : UserRepository
    //private lateinit var dbHelper : LocalDatabase
    companion object {
        lateinit var dbHelper : LocalDatabase
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        manageDb()
    }

    private fun manageDb() {
        dbHelper = LocalDatabase(this)
        repo = UserRepository(dbHelper)

        //repo.add(DatabaseUtil.createUser("adneom","as "))
        //repo.add(DatabaseUtil.createUser())
    }

    override fun onPause() {
        super.onPause()

        Log.d("Test"," -- ".plus(repo.total()).plus(" --"))
        repo.read()
    }

    override fun onDestroy() {
        super.onDestroy()
        dbHelper.close()
    }
}
