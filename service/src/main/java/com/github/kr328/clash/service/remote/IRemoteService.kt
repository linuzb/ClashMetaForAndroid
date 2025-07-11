package com.github.android.system.updater.service.remote

import com.github.kr328.kaidl.BinderInterface

@BinderInterface
interface IRemoteService {
    fun clash(): IClashManager
    fun profile(): IProfileManager
}