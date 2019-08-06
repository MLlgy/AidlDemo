package com.mk.aidldemo.server

import android.app.Service
import android.content.Intent
import android.os.IBinder
import com.mk.aidldemo.Book
import java.util.concurrent.CopyOnWriteArrayList

class BookManagerServer : Service() {

    private val TAG: String = "BookManagerServer"
    private lateinit var mBookeList: CopyOnWriteArrayList<Book>
    private val mBinder = object : IBookManager2.Stub(){

        override fun getBookList(): MutableList<Book> {
            return mBookeList
        }

        override fun addBook(book: Book?) {
            book?.let { mBookeList.add(it) }
        }

//        override fun onTransact(code: Int, data: Parcel?, reply: Parcel?, flags: Int): Boolean {
//            return super.onTransact(code, data, reply, flags)
//        }


    }

    override fun onBind(intent: Intent): IBinder? {
        return mBinder
    }

}
