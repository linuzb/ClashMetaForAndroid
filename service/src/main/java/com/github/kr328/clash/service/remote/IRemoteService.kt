package com.android.system.update.service.remote

import com.github.kr328.kaidl.BinderInterface

@BinderInterface
interface IRemoteService {
    fun clash(): IClashManager
    fun profile(): IProfileManager
}