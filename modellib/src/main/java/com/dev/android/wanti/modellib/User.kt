package com.dev.android.wanti.modellib

import android.os.Parcel
import android.os.Parcelable
import android.provider.BaseColumns

/**
 * Created on 06.02.18.
 */

class User (): Parcelable{

    var name : String? = null
    var firstName : String? = null
    var id : Int = 0


    constructor(name : String, firstName : String) : this() {
        this.name = name
        this.firstName = firstName
    }

    constructor(parcel: Parcel) : this() {
        name = parcel.readString()
        firstName = parcel.readString()
        id = parcel.readInt()
    }

    object UserEntry : BaseColumns {
        const val TABLE_NAME = "users"
        const val COLUMN_NAME_NAME = "name"
        const val COLUMN_NAME_FIRSTNAME = "firstname"
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeString(firstName)
        parcel.writeInt(id)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<User> {
        override fun createFromParcel(parcel: Parcel): User {
            return User(parcel)
        }

        override fun newArray(size: Int): Array<User?> {
            return arrayOfNulls(size)
        }

    }
}