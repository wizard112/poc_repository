package com.dev.android.wanti.poc_repo.repositories

/**
 * Created by gael on 05.02.18.
 */

interface Repository<T> {

    fun add (item : T)
    fun remove (item : T)
    fun total () : Int
    fun read()
}