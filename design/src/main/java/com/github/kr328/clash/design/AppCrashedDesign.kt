package com.github.android.updater.design

import android.content.Context
import android.view.View
import com.github.android.updater.design.databinding.DesignAppCrashedBinding
import com.github.android.updater.design.util.applyFrom
import com.github.android.updater.design.util.bindAppBarElevation
import com.github.android.updater.design.util.layoutInflater
import com.github.android.updater.design.util.root

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