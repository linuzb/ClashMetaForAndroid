package com.android.system.update.service.clash.module

import android.app.PendingIntent
import android.app.Service
import android.content.Intent
import android.os.PowerManager
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.getSystemService
import com.android.system.update.common.compat.getColorCompat
import com.android.system.update.common.compat.pendingIntentFlags
import com.android.system.update.common.constants.Components
import com.android.system.update.common.constants.Intents
import com.android.system.update.common.util.ticker
import com.android.system.update.core.Clash
import com.android.system.update.core.util.trafficDownload
import com.android.system.update.core.util.trafficUpload
import com.android.system.update.service.R
import com.android.system.update.service.StatusProvider
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.selects.select
import java.util.concurrent.TimeUnit

class DynamicNotificationModule(service: Service) : Module<Unit>(service) {
    private val builder = NotificationCompat.Builder(service, StaticNotificationModule.CHANNEL_ID)
        .setSmallIcon(R.drawable.ic_logo_service)
        .setOngoing(true)
        .setColor(service.getColorCompat(R.color.color_clash))
        .setOnlyAlertOnce(true)
        .setShowWhen(false)
        .setContentTitle("Not Selected")
        .setForegroundServiceBehavior(NotificationCompat.FOREGROUND_SERVICE_IMMEDIATE)
        .setContentIntent(
            PendingIntent.getActivity(
                service,
                R.id.nf_clash_status,
                Intent().setComponent(Components.MAIN_ACTIVITY)
                    .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_SINGLE_TOP or Intent.FLAG_ACTIVITY_CLEAR_TOP),
                pendingIntentFlags(PendingIntent.FLAG_UPDATE_CURRENT)
            )
        )

    private val notificationManager = NotificationManagerCompat.from(service)

    private fun update() {
        val now = Clash.queryTrafficNow()
        val total = Clash.queryTrafficTotal()

        val uploading = now.trafficUpload()
        val downloading = now.trafficDownload()
        val uploaded = total.trafficUpload()
        val downloaded = total.trafficDownload()

        val notification = builder
            .setContentText(
                service.getString(
                    R.string.clash_notification_content,
                    "$uploading/s", "$downloading/s"
                )
            )
            .setSubText(
                service.getString(
                    R.string.clash_notification_content,
                    uploaded, downloaded
                )
            )
            .build()

        notificationManager.notify(R.id.nf_clash_status, notification)
    }

    override suspend fun run() = coroutineScope {
        var shouldUpdate = service.getSystemService<PowerManager>()?.isInteractive ?: true

        val screenToggle = receiveBroadcast(false, Channel.CONFLATED) {
            addAction(Intent.ACTION_SCREEN_ON)
            addAction(Intent.ACTION_SCREEN_OFF)
        }

        val profileLoaded = receiveBroadcast(capacity = Channel.CONFLATED) {
            addAction(Intents.ACTION_PROFILE_LOADED)
        }

        val ticker = ticker(TimeUnit.SECONDS.toMillis(1))

        while (true) {
            select<Unit> {
                screenToggle.onReceive {
                    when (it.action) {
                        Intent.ACTION_SCREEN_ON ->
                            shouldUpdate = true
                        Intent.ACTION_SCREEN_OFF ->
                            shouldUpdate = false
                    }
                }
                profileLoaded.onReceive {
                    builder.setContentTitle(StatusProvider.currentProfile ?: "Not selected")
                }
                if (shouldUpdate) {
                    ticker.onReceive {
                        update()
                    }
                }
            }
        }
    }
}