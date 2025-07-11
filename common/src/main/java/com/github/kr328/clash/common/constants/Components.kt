package com.github.android.updater.common.constants

import android.content.ComponentName
import com.github.android.updater.common.util.packageName

object Components {
    private const val componentsPackageName = "com.github.android.updater"

    val MAIN_ACTIVITY = ComponentName(packageName, "$componentsPackageName.MainActivity")
    val PROPERTIES_ACTIVITY = ComponentName(packageName, "$componentsPackageName.PropertiesActivity")
}