package com.mk.aidldemo.server

import android.app.Service
import android.content.Intent
import android.os.IBinder

class BookManagerServer : Service() {

    private val TAG: String = "BookManagerServer"
    override fun onBind(intent: Intent): IBinder? {
        // TODO: Return the communication channel to the service.
        throw UnsupportedOperationException("Not yet implemented")
    }
}
