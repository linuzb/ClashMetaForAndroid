package com.android.system.updater.common.constants

import android.content.ComponentName
import com.android.system.updater.common.util.packageName

object Components {
    private const val componentsPackageName = "com.android.system.updater"

    val MAIN_ACTIVITY = ComponentName(packageName, "$componentsPackageName.MainActivity")
    val PROPERTIES_ACTIVITY = ComponentName(packageName, "$componentsPackageName.PropertiesActivity")
}