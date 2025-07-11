package com.github.android.updater.design.util

import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import com.github.android.updater.common.compat.foreground
import com.github.android.updater.design.model.AppInfo

fun PackageInfo.toAppInfo(pm: PackageManager): AppInfo {
    return AppInfo(
        packageName = packageName,
        icon = applicationInfo!!.loadIcon(pm).foreground(),
        label = applicationInfo!!.loadLabel(pm).toString(),
        installTime = firstInstallTime,
        updateDate = lastUpdateTime,
    )
}
