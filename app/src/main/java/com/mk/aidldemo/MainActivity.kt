package com.mk.aidldemo

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.Toast
import com.mk.aidldemo.server.IBookManager2
import com.mk.aidldemo.server.MyServer
import com.mk.aidldemo.util.ProcessUtils
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var countId: Int = 0

    private var bookManager: IBookManager2? = null
    private val serverConnection = object : ServiceConnection {
        override fun onServiceDisconnected(name: ComponentName?) {
            Toast.makeText(baseContext, "onServiceDisconnected", Toast.LENGTH_SHORT).show()
            bookManager = null

        }

        /**
         * service 端对象 service
         */
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            // 如果在同一个进程中就为 Stub 对象，不在一个进程为 Proxy 对象
//           此时的 service 为 Service 中的 onBinder 方法返回的 IBinder 对象。
            Toast.makeText(baseContext, "onServiceConnected", Toast.LENGTH_SHORT).show()
            Log.e("process binderService",ProcessUtils.getCurrentProcessName())
            bookManager = IBookManager2.Stub.asInterface(service)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }


    fun bind(view: View) {
        val intent = Intent(this, MyServer::class.java)
        bindService(intent, serverConnection, Context.BIND_AUTO_CREATE)
    }

    fun addBook(view: View) {
        Log.e("process add ",ProcessUtils.getCurrentProcessName())
        addBook()
    }

    fun getBookList(view: View) {
        Log.e("process getBookList ",ProcessUtils.getCurrentProcessName())
        getBookList()
    }

    fun unBind(view: View) {
        unbindService(serverConnection)
    }

    private fun addBook() {
        bookManager?.addBook(Book(countId, "Book $countId"))
        countId++
    }


    private fun getBookList() {
        val bookList = bookManager?.bookList
        val stringBuilder = StringBuilder()
        bookList?.forEach {
            stringBuilder.append("BookId is ${it.bookId},BookName is ${it.bookName}\n")
            Log.e("getBookList", "BookId is ${it.bookId},BookName is ${it.bookName}")
        }
        tvBookList.text = stringBuilder
    }
}
