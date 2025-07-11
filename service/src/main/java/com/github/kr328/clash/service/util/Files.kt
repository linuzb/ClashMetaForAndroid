package com.github.android.updater.service.util

import android.content.Context
import java.io.File

val Context.importedDir: File
    get() = filesDir.resolve("imported")

val Context.pendingDir: File
    get() = filesDir.resolve("pending")

val Context.processingDir: File
    get() = filesDir.resolve("processing")

val File.directoryLastModified: Long?
    get() {
        return walk().map { it.lastModified() }.maxOrNull()
    }