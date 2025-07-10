package com.android.system.update

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.android.system.update.service.StatusProvider
import com.android.system.update.util.startClashService

class RestartReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        when (intent.action) {
            Intent.ACTION_BOOT_COMPLETED, Intent.ACTION_MY_PACKAGE_REPLACED -> {
                if (StatusProvider.shouldStartClashOnBoot)
                    context.startClashService()
            }
        }
    }
}