package com.github.android.updater.remote

import android.content.Context
import android.content.Intent
import com.github.android.updater.ApkBrokenActivity
import com.github.android.updater.AppCrashedActivity
import com.github.android.updater.common.Global
import com.github.android.updater.common.log.Log
import com.github.android.updater.common.util.intent
import com.github.android.updater.store.AppStore
import com.github.android.updater.util.ApplicationObserver
import com.github.android.updater.util.verifyApk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.launch

object Remote {
    val broadcasts: Broadcasts = Broadcasts(Global.application)
    val service: Service = Service(Global.application) {
        ApplicationObserver.createdActivities.forEach { it.finish() }

        val intent = AppCrashedActivity::class.intent
            .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)

        Global.application.startActivity(intent)
    }

    private val visible = Channel<Boolean>(Channel.CONFLATED)

    fun launch() {
        ApplicationObserver.attach(Global.application)

        ApplicationObserver.onVisibleChanged {
            if(it) {
                Log.d("App becomes visible")
                service.bind()
                broadcasts.register()
            }
            else {
                Log.d("App becomes invisible")
                service.unbind()
                broadcasts.unregister()
            }
        }

        Global.launch(Dispatchers.IO) {
            verifyApp()
        }
    }

    private suspend fun verifyApp() {
        val context = Global.application
        val store = AppStore(context)
        val updatedAt = getLastUpdated(context)

        if (store.updatedAt != updatedAt) {
            if (!context.verifyApk()) {
                ApplicationObserver.createdActivities.forEach { it.finish() }

                val intent = ApkBrokenActivity::class.intent
                    .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)

                return context.startActivity(intent)
            } else {
                store.updatedAt = updatedAt
            }
        }
    }

    private fun getLastUpdated(context: Context): Long {
        return context.packageManager.getPackageInfo(context.packageName, 0).lastUpdateTime
    }
}