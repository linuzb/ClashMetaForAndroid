package com.android.system.update.design

import android.content.Context
import android.view.View
import com.android.system.update.design.databinding.DesignSettingsBinding
import com.android.system.update.design.util.applyFrom
import com.android.system.update.design.util.bindAppBarElevation
import com.android.system.update.design.util.layoutInflater
import com.android.system.update.design.util.root

class SettingsDesign(context: Context) : Design<SettingsDesign.Request>(context) {
    enum class Request {
        StartApp, StartNetwork, StartOverride, StartMetaFeature,
    }

    private val binding = DesignSettingsBinding
        .inflate(context.layoutInflater, context.root, false)

    override val root: View
        get() = binding.root

    init {
        binding.self = this

        binding.activityBarLayout.applyFrom(context)

        binding.scrollRoot.bindAppBarElevation(binding.activityBarLayout)
    }

    fun request(request: Request) {
        requests.trySend(request)
    }
}