package com.android.system.update.design

import android.content.Context
import android.view.View
import com.android.system.update.design.databinding.DesignAppCrashedBinding
import com.android.system.update.design.util.applyFrom
import com.android.system.update.design.util.bindAppBarElevation
import com.android.system.update.design.util.layoutInflater
import com.android.system.update.design.util.root

class AppCrashedDesign(context: Context) : Design<Unit>(context) {
    private val binding = DesignAppCrashedBinding
        .inflate(context.layoutInflater, context.root, false)

    override val root: View
        get() = binding.root

    fun setAppLogs(logs: String) {
        binding.logsView.text = logs
    }

    init {
        binding.self = this

        binding.activityBarLayout.applyFrom(context)

        binding.scrollRoot.bindAppBarElevation(binding.activityBarLayout)
    }
}