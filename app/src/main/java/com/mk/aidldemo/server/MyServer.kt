package com.mk.aidldemo.server

import android.app.Service
import android.content.Intent
import android.os.IBinder
import com.mk.aidldemo.Book
import com.mk.aidldemo.IBookManager

/**
 * @uthor: GY.LEE
 * @date: 2019-05-28
 */

class MyServer : Service() {

    var list = mutableListOf<Book>()

    val mBinder = object : IBookManager2.Stub() {
        override fun getBookList(): MutableList<Book> {
            return list
        }

        override fun addBook(book: Book?) {
            list.add(book!!)
        }

    }



    override fun onBind(intent: Intent?): IBinder? {

        return mBinder
    }

}