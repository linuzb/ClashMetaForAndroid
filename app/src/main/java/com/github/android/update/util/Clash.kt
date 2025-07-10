package com.android.system.update.util

import android.content.Context
import android.content.Intent
import android.net.VpnService
import com.android.system.update.common.compat.startForegroundServiceCompat
import com.android.system.update.common.constants.Intents
import com.android.system.update.common.util.intent
import com.android.system.update.design.store.UiStore
import com.android.system.update.service.ClashService
import com.android.system.update.service.TunService
import com.android.system.update.service.util.sendBroadcastSelf

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