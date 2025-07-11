package com.github.android.updater.common.constants

import com.github.android.updater.common.util.packageName

object Authorities {
    val STATUS_PROVIDER = "$packageName.status"
    val SETTINGS_PROVIDER = "$packageName.settings"
    val FILES_PROVIDER = "$packageName.files"
}