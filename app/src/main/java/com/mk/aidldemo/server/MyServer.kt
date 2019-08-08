package com.mk.aidldemo.server

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import com.mk.aidldemo.Book
import com.mk.aidldemo.util.ProcessUtils

/**
 * @uthor: GY.LEE
 * @date: 2019-05-28
 */

class MyServer : Service() {

    var list = mutableListOf<Book>()

    private val mBinder = object : IBookManager2.Stub() {
        override fun getBookList(): MutableList<Book> {
            Log.e("process service getList",ProcessUtils.getCurrentProcessName())
            return list
        }
        override fun addBook(book: Book?) {
            Log.e("process service addBook",ProcessUtils.getCurrentProcessName())
            list.add(book!!)
        }
    }


    override fun onBind(intent: Intent?): IBinder? {
        return mBinder
    }

}