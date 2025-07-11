package com.github.android.updater.service.util

import android.net.ConnectivityManager
import android.net.Network

fun ConnectivityManager.resolveDns(network: Network?): List<String> {
    val properties = getLinkProperties(network) ?: return listOf()
    return properties.dnsServers.map { it.asSocketAddressText(53) }
}
