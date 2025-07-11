package com.github.android.system.updater

import android.content.pm.PackageManager
import com.github.android.system.updater.common.compat.getDrawableCompat
import com.github.android.system.updater.common.constants.Metadata
import com.github.android.system.updater.core.Clash
import com.github.android.system.updater.design.OverrideSettingsDesign
import com.github.android.system.updater.design.model.AppInfo
import com.github.android.system.updater.design.util.toAppInfo
import com.github.android.system.updater.service.store.ServiceStore
import com.github.android.system.updater.util.withClash
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.isActive
import kotlinx.coroutines.selects.select
import kotlinx.coroutines.withContext

class OverrideSettingsActivity : BaseActivity<OverrideSettingsDesign>() {
    override suspend fun main() {
        val configuration = withClash { queryOverride(Clash.OverrideSlot.Persist) }
        val service = ServiceStore(this)

        defer {
            withClash {
                patchOverride(Clash.OverrideSlot.Persist, configuration)
            }
        }

        val design = OverrideSettingsDesign(
            this,
            configuration
        )

        setContentDesign(design)

        while (isActive) {
            select<Unit> {
                events.onReceive {

                }
                design.requests.onReceive {
                    when (it) {
                        OverrideSettingsDesign.Request.ResetOverride -> {
                            if (design.requestResetConfirm()) {
                                defer {
                                    withClash {
                                        clearOverride(Clash.OverrideSlot.Persist)
                                    }
                                }

                                finish()
                            }
                        }
                    }
                }
            }
        }
    }
}