package com.android.system.update.log

import android.content.Context
import com.android.system.update.core.model.LogMessage
import com.android.system.update.design.util.format
import java.io.BufferedWriter
import java.io.Writer
import java.util.*

class LogcatFilter(output: Writer, private val context: Context) : BufferedWriter(output) {
    fun writeHeader(time: Date) {
        appendLine("# Capture on ${time.format(context)}")
    }

    fun writeMessage(message: LogMessage) {
        val time = message.time.format(context, includeDate = false)
        val level = message.level.name

        appendLine(FORMAT.format(time, level, message.message))
    }

    companion object {
        private const val FORMAT = "%12s %7s: %s"
    }
}