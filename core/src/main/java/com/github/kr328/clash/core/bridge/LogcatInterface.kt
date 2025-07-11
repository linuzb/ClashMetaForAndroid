package com.github.android.system.updater.core.bridge

import androidx.annotation.Keep

@Keep
interface LogcatInterface {
    fun received(jsonPayload: String)
}