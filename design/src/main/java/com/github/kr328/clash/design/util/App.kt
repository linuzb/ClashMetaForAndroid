package com.android.system.updater.design.util

import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import com.android.system.updater.common.compat.foreground
import com.android.system.updater.design.model.AppInfo

fun PackageInfo.toAppInfo(pm: PackageManager): AppInfo {
    return AppInfo(
        packageName = packageName,
        icon = applicationInfo!!.loadIcon(pm).foreground(),
        label = applicationInfo!!.loadLabel(pm).toString(),
        installTime = firstInstallTime,
        updateDate = lastUpdateTime,
    )
}
