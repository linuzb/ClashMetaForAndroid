package com.github.android.updater.design.model

enum class AppInfoSort(comparator: Comparator<AppInfo>) : Comparator<AppInfo> by comparator {
    Label(compareBy(AppInfo::label)),
    PackageName(compareBy(AppInfo::packageName)),
    InstallTime(compareBy(AppInfo::installTime)),
    UpdateTime(compareBy(AppInfo::updateDate)),
}
