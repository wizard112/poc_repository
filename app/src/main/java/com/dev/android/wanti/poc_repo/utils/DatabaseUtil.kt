package com.dev.android.wanti.poc_repo.utils

import com.dev.android.wanti.modellib.User

/**
 * Created by gael on 06.02.18.
 */

object DatabaseUtil {

    fun createUser(name : String = "test one ", firstName : String = " one ") = User(name, firstName)

}