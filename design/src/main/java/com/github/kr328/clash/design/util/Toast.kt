package com.github.android.updater.design.util

import com.github.android.updater.design.Design
import com.github.android.updater.design.R
import com.github.android.updater.design.ui.ToastDuration
import com.google.android.material.dialog.MaterialAlertDialogBuilder

suspend fun Design<*>.showExceptionToast(message: CharSequence) {
    showToast(message, ToastDuration.Long) {
        setAction(R.string.detail) {
            MaterialAlertDialogBuilder(it.context)
                .setTitle(R.string.error)
                .setMessage(message)
                .setCancelable(true)
                .setPositiveButton(R.string.ok) { _, _ -> }
                .show()
        }
    }
}

suspend fun Design<*>.showExceptionToast(exception: Exception) {
    showExceptionToast(exception.message ?: "Unknown")
}