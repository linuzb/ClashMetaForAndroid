package com.android.system.update.service.remote

import com.android.system.update.core.model.LogMessage
import com.github.kr328.kaidl.BinderInterface

@BinderInterface
interface ILogObserver {
    fun newItem(log: LogMessage)
}