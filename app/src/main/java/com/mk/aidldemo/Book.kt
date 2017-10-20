package com.mk.aidldemo

import android.os.Parcel
import android.os.Parcelable

/**
 * Created by liguoying on 2017/10/20.
 */
class Book(bookId: Int, bookName: String) : Parcelable {
    constructor(parcel: Parcel) : this(
            TODO("bookId"),
            TODO("bookName")) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {

    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Book> {
        override fun createFromParcel(parcel: Parcel): Book {
            return Book(parcel)
        }

        override fun newArray(size: Int): Array<Book?> {
            return arrayOfNulls(size)
        }
    }
}