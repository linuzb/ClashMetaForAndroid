package com.github.android.system.updater.service.remote

import com.github.android.system.updater.core.model.LogMessage
import com.github.kr328.kaidl.BinderInterface

@BinderInterface
interface ILogObserver {
    fun newItem(log: LogMessage)
}