package com.github.android.updater.design.util

import android.app.Activity
import android.content.Context
import android.text.Spanned
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.DimenRes
import androidx.annotation.StringRes
import com.github.android.updater.common.compat.fromHtmlCompat

val Context.layoutInflater: LayoutInflater
    get() = LayoutInflater.from(this)

val Context.root: ViewGroup?
    get() {
        return when (this) {
            is Activity -> {
                findViewById(android.R.id.content)
            }
            else -> {
                null
            }
        }
    }

fun Context.getPixels(@DimenRes resId: Int): Int {
    return resources.getDimensionPixelSize(resId)
}

fun Context.getHtml(@StringRes resId: Int): Spanned {
    return fromHtmlCompat(getString(resId))
}
