package com.android.system.updater.service.remote

import com.android.system.updater.core.model.FetchStatus
import com.github.kr328.kaidl.BinderInterface

@BinderInterface
fun interface IFetchObserver {
    fun updateStatus(status: FetchStatus)
}