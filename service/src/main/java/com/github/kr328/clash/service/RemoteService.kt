package com.github.android.system.updater.service

import android.content.Intent
import android.os.IBinder
import com.github.android.system.updater.service.remote.IClashManager
import com.github.android.system.updater.service.remote.IRemoteService
import com.github.android.system.updater.service.remote.IProfileManager
import com.github.android.system.updater.service.remote.wrap
import com.github.android.system.updater.service.util.cancelAndJoinBlocking

class RemoteService : BaseService(), IRemoteService {
    private val binder = this.wrap()

    private var clash: ClashManager? = null
    private var profile: ProfileManager? = null
    private var clashBinder: IClashManager? = null
    private var profileBinder: IProfileManager? = null

    override fun onCreate() {
        super.onCreate()

        clash = ClashManager(this)
        profile = ProfileManager(this)
        clashBinder = clash?.wrap() as IClashManager?
        profileBinder = profile?.wrap() as IProfileManager?
    }

    override fun onDestroy() {
        super.onDestroy()

        clash?.cancelAndJoinBlocking()
        profile?.cancelAndJoinBlocking()
    }

    override fun onBind(intent: Intent?): IBinder {
        return binder
    }

    override fun clash(): IClashManager {
        return clashBinder!!
    }

    override fun profile(): IProfileManager {
        return profileBinder!!
    }
}