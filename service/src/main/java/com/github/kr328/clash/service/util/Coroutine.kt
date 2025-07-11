package com.github.android.updater.service.util

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.job
import kotlinx.coroutines.runBlocking

fun CoroutineScope.cancelAndJoinBlocking() {
    val scope = this

    runBlocking {
        scope.coroutineContext.job.cancel()
        scope.coroutineContext.job.join()
    }
}