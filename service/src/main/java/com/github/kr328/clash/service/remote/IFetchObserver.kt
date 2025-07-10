package com.android.system.update.service.remote

import com.android.system.update.core.model.FetchStatus
import com.github.kr328.kaidl.BinderInterface

@BinderInterface
fun interface IFetchObserver {
    fun updateStatus(status: FetchStatus)
}