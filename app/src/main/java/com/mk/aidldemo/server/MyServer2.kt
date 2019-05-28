package com.mk.aidldemo.server

import android.app.Service
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Binder
import android.os.IBinder
import android.os.Parcel
import android.os.RemoteCallbackList
import android.os.RemoteException
import android.os.SystemClock
import android.util.Log
import com.mk.aidldemo.Book

import com.mk.aidldemo.IBookManager
import java.util.concurrent.CopyOnWriteArrayList
import java.util.concurrent.atomic.AtomicBoolean

/**
 * Created by liguoying on 2017/10/20.
 */

class MyServer2 : Service() {

    private val mIsServiceDestoryed = AtomicBoolean(false)

    private val mBookList = CopyOnWriteArrayList<Book>()
    // private CopyOnWriteArrayList<IOnNewBookArrivedListener> mListenerList =
    // new CopyOnWriteArrayList<IOnNewBookArrivedListener>();

    private val mListenerList = RemoteCallbackList<IBookManager>()

    private val mBinder = object : IBookManager.Stub() {

        @Throws(RemoteException::class)
        override fun getBookList(): List<Book> {
            SystemClock.sleep(5000)
            return mBookList
        }

        @Throws(RemoteException::class)
        override fun addBook(book: Book) {
            mBookList.add(book)
        }

        @Throws(RemoteException::class)
        override fun onTransact(code: Int, data: Parcel, reply: Parcel, flags: Int): Boolean {
            val check = checkCallingOrSelfPermission("com.ryg.chapter_2.permission.ACCESS_BOOK_SERVICE")
            Log.d(TAG, "check=" + check)
            if (check == PackageManager.PERMISSION_DENIED) {
                return false
            }

            var packageName: String? = null
            val packages = packageManager.getPackagesForUid(
                    Binder.getCallingUid())
            if (packages != null && packages.size > 0) {
                packageName = packages[0]
            }
            Log.d(TAG, "onTransact: " + packageName!!)
            if (!packageName.startsWith("com.ryg")) {
                return false
            }

            return super.onTransact(code, data, reply, flags)
        }

//        @Throws(RemoteException::class)
//        override fun registerListener(listener: IBookManager) {
//            mListenerList.register(listener)
//
//            val N = mListenerList.beginBroadcast()
//            mListenerList.finishBroadcast()
//            Log.d(TAG, "registerListener, current size:" + N)
//        }

        @Throws(RemoteException::class)
        fun unregisterListener(listener: IBookManager) {
            val success = mListenerList.unregister(listener)

            if (success) {
                Log.d(TAG, "unregister success.")
            } else {
                Log.d(TAG, "not found, can not unregister.")
            }
            val N = mListenerList.beginBroadcast()
            mListenerList.finishBroadcast()
            Log.d(TAG, "unregisterListener, current size:" + N)
        }

    }

    override fun onCreate() {
        super.onCreate()
        mBookList.add(Book(1, "Android"))
        mBookList.add(Book(2, "Ios"))
        Thread(ServiceWorker()).start()
    }

    override fun onBind(intent: Intent): IBinder? {
        val check = checkCallingOrSelfPermission("com.ryg.chapter_2.permission.ACCESS_BOOK_SERVICE")
        Log.d(TAG, "onbind check=" + check)
        if (check == PackageManager.PERMISSION_DENIED) {
            return null
        }
        return mBinder
    }

    override fun onDestroy() {
        mIsServiceDestoryed.set(true)
        super.onDestroy()
    }

    @Throws(RemoteException::class)
    private fun onNewBookArrived(book: Book) {
        mBookList.add(book)
        val N = mListenerList.beginBroadcast()
        for (i in 0..N - 1) {
            val l = mListenerList.getBroadcastItem(i)
            if (l != null) {
                try {
                    l.addBook(book)
                } catch (e: RemoteException) {
                    e.printStackTrace()
                }

            }
        }
        mListenerList.finishBroadcast()
    }

    private inner class ServiceWorker : Runnable {
        override fun run() {
            // do background processing here.....
            while (!mIsServiceDestoryed.get()) {
                try {
                    Thread.sleep(5000)
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }

                val bookId = mBookList.size + 1
                val newBook = Book(bookId, "new book#" + bookId)
                try {
                    onNewBookArrived(newBook)
                } catch (e: RemoteException) {
                    e.printStackTrace()
                }

            }
        }
    }

    companion object {
        private val TAG = "BMS"
    }
}
