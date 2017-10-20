package com.mk.aidldemo.server

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.os.Parcel
import com.mk.aidldemo.Book
import com.mk.aidldemo.IBookManager
import java.util.concurrent.CopyOnWriteArrayList

class BookManagerServer : Service() {

    private val TAG: String = "BookManagerServer"
    private lateinit var mBookeList: CopyOnWriteArrayList<Book>
    private val mBinder = object : IBookManager.Stub(){
        override fun registerListener(listener: IBookManager?) {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun getBookList(): MutableList<Book> {
            return bookList
        }

        override fun addBook(book: Book?) {
            book?.let { bookList.add(it) }
        }

        override fun onTransact(code: Int, data: Parcel?, reply: Parcel?, flags: Int): Boolean {
            return super.onTransact(code, data, reply, flags)
        }


    }

    override fun onBind(intent: Intent): IBinder? {
        // TODO: Return the communication channel to the service.
        throw UnsupportedOperationException("Not yet implemented")
    }

}
