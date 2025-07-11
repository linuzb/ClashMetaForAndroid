package com.android.system.updater.service.util

import com.android.system.updater.service.data.ImportedDao
import com.android.system.updater.service.data.PendingDao
import java.util.*

suspend fun generateProfileUUID(): UUID {
    var result = UUID.randomUUID()

    while (ImportedDao().exists(result) || PendingDao().exists(result)) {
        result = UUID.randomUUID()
    }

    return result
}
