package com.github.android.updater.util

import android.content.Context
import android.content.Intent
import android.net.VpnService
import com.github.android.updater.common.compat.startForegroundServiceCompat
import com.github.android.updater.common.constants.Intents
import com.github.android.updater.common.util.intent
import com.github.android.updater.design.store.UiStore
import com.github.android.updater.service.ClashService
import com.github.android.updater.service.TunService
import com.github.android.updater.service.util.sendBroadcastSelf

fun Context.startClashService(): Intent? {
    val startTun = UiStore(this).enableVpn

    if (startTun) {
        val vpnRequest = VpnService.prepare(this)
        if (vpnRequest != null)
            return vpnRequest

        startForegroundServiceCompat(TunService::class.intent)
    } else {
        startForegroundServiceCompat(ClashService::class.intent)
    }

    return null
}

fun Context.stopClashService() {
    sendBroadcastSelf(Intent(Intents.ACTION_CLASH_REQUEST_STOP))
}