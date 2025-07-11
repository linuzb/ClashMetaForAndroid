package com.android.system.updater

import android.content.Intent
import com.android.system.updater.design.HelpDesign
import kotlinx.coroutines.isActive

class HelpActivity : BaseActivity<HelpDesign>() {
    override suspend fun main() {
        val design = HelpDesign(this) {
            startActivity(Intent(Intent.ACTION_VIEW).setData(it))
        }

        setContentDesign(design)

        while (isActive) {
            events.receive()
        }
    }
}