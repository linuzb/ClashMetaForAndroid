package com.android.system.update

import android.content.ComponentName
import android.content.pm.PackageManager
import com.android.system.update.common.util.componentName
import com.android.system.update.design.AppSettingsDesign
import com.android.system.update.design.model.Behavior
import com.android.system.update.service.store.ServiceStore
import com.android.system.update.util.ApplicationObserver
import kotlinx.coroutines.isActive
import kotlinx.coroutines.selects.select

class AppSettingsActivity : BaseActivity<AppSettingsDesign>(), Behavior {
    override suspend fun main() {
        val design = AppSettingsDesign(
            this,
            uiStore,
            ServiceStore(this),
            this,
            clashRunning,
            ::onHideIconChange,
        )

        setContentDesign(design)

        while (isActive) {
            select<Unit> {
                events.onReceive {
                    when (it) {
                        Event.ClashStart, Event.ClashStop, Event.ServiceRecreated ->
                            recreate()
                        else -> Unit
                    }
                }
                design.requests.onReceive {
                    ApplicationObserver.createdActivities.forEach {
                        it.recreate()
                    }
                }
            }
        }
    }

    override var autoRestart: Boolean
        get() {
            val status = packageManager.getComponentEnabledSetting(
                RestartReceiver::class.componentName
            )

            return status == PackageManager.COMPONENT_ENABLED_STATE_ENABLED
        }
        set(value) {
            val status = if (value)
                PackageManager.COMPONENT_ENABLED_STATE_ENABLED
            else
                PackageManager.COMPONENT_ENABLED_STATE_DISABLED

            packageManager.setComponentEnabledSetting(
                RestartReceiver::class.componentName,
                status,
                PackageManager.DONT_KILL_APP,
            )
        }

    private fun onHideIconChange(hide: Boolean) {
        val newState = if (hide) {
            PackageManager.COMPONENT_ENABLED_STATE_DISABLED
        } else {
            PackageManager.COMPONENT_ENABLED_STATE_ENABLED
        }
        packageManager.setComponentEnabledSetting(
            ComponentName(this, mainActivityAlias),
            newState,
            PackageManager.DONT_KILL_APP
        )
    }
}