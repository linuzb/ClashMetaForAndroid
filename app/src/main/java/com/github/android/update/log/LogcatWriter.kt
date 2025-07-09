package com.android.system.update.log

import android.content.Context
import com.android.system.update.core.model.LogMessage
import com.android.system.update.design.model.LogFile
import com.android.system.update.util.logsDir
import java.io.BufferedWriter
import java.io.FileWriter

class LogcatWriter(context: Context) : AutoCloseable {
    private val file = LogFile.generate()
    private val writer = BufferedWriter(FileWriter(context.logsDir.resolve(file.fileName)))

    override fun close() {
        writer.close()
    }

    fun appendMessage(message: LogMessage) {
        writer.appendLine(FORMAT.format(message.time.time, message.level.name, message.message))
    }

    companion object {
        private const val FORMAT = "%d:%s:%s"
    }
}