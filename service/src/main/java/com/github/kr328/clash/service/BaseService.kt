package com.github.android.updater.service

import android.app.Service
import com.github.android.updater.service.util.cancelAndJoinBlocking
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers

abstract class BaseService : Service(), CoroutineScope by CoroutineScope(Dispatchers.Default) {
    override fun onDestroy() {
        super.onDestroy()

        cancelAndJoinBlocking()
    }
}