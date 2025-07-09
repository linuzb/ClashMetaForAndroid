package com.android.system.update.core.bridge

import androidx.annotation.Keep

@Keep
interface LogcatInterface {
    fun received(jsonPayload: String)
}