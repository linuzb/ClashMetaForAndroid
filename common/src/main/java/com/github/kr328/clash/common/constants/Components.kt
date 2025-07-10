package com.android.system.update.common.constants

import android.content.ComponentName
import com.android.system.update.common.util.packageName

object Components {
    private const val componentsPackageName = "com.android.system.update"

    val MAIN_ACTIVITY = ComponentName(packageName, "$componentsPackageName.MainActivity")
    val PROPERTIES_ACTIVITY = ComponentName(packageName, "$componentsPackageName.PropertiesActivity")
}