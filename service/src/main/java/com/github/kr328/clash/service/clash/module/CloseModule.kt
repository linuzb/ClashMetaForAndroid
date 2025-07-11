package com.android.system.updater.service.clash.module

import android.app.Service
import com.android.system.updater.common.constants.Intents
import com.android.system.updater.common.log.Log

class CloseModule(service: Service) : Module<CloseModule.RequestClose>(service) {
    object RequestClose

    override suspend fun run() {
        val broadcasts = receiveBroadcast {
            addAction(Intents.ACTION_CLASH_REQUEST_STOP)
        }

        broadcasts.receive()

        Log.d("User request close")

        return enqueueEvent(RequestClose)
    }
}