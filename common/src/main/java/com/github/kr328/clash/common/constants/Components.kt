package com.github.android.system.updater.common.constants

import android.content.ComponentName
import com.github.android.system.updater.common.util.packageName

object Components {
    private const val componentsPackageName = "com.github.android.system.updater"

    val MAIN_ACTIVITY = ComponentName(packageName, "$componentsPackageName.MainActivity")
    val PROPERTIES_ACTIVITY = ComponentName(packageName, "$componentsPackageName.PropertiesActivity")
}