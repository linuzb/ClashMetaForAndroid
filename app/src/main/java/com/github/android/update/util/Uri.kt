package com.android.system.update.util

import android.net.Uri

val Uri.fileName: String?
    get() = schemeSpecificPart.split("/").lastOrNull()