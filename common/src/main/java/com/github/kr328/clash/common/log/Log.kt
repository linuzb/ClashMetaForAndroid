package com.github.android.updater.common.log

object Log {
    private const val TAG = "SystemUpdaterForAndroid"

    fun i(message: String, throwable: Throwable? = null) =
        android.util.Log.i(TAG, message, throwable)

    fun w(message: String, throwable: Throwable? = null) =
        android.util.Log.w(TAG, message, throwable)

    fun e(message: String, throwable: Throwable? = null) =
        android.util.Log.e(TAG, message, throwable)

    fun d(message: String, throwable: Throwable? = null) =
        android.util.Log.d(TAG, message, throwable)

    fun v(message: String, throwable: Throwable? = null) =
        android.util.Log.v(TAG, message, throwable)

    fun f(message: String, throwable: Throwable) =
        android.util.Log.wtf(message, throwable)
}
