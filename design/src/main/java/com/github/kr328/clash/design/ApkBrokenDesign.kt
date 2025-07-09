package com.android.system.update.design

import android.content.Context
import android.view.View
import com.android.system.update.design.databinding.DesignSettingsCommonBinding
import com.android.system.update.design.preference.category
import com.android.system.update.design.preference.clickable
import com.android.system.update.design.preference.preferenceScreen
import com.android.system.update.design.preference.tips
import com.android.system.update.design.util.applyFrom
import com.android.system.update.design.util.bindAppBarElevation
import com.android.system.update.design.util.layoutInflater
import com.android.system.update.design.util.root

class ApkBrokenDesign(context: Context) : Design<ApkBrokenDesign.Request>(context) {
    data class Request(val url: String)

    private val binding = DesignSettingsCommonBinding
        .inflate(context.layoutInflater, context.root, false)

    override val root: View
        get() = binding.root

    init {
        binding.surface = surface

        binding.activityBarLayout.applyFrom(context)

        binding.scrollRoot.bindAppBarElevation(binding.activityBarLayout)

        val screen = preferenceScreen(context) {
            tips(R.string.application_broken_tips)

            category(R.string.reinstall)

            clickable(
                title = R.string.github_releases,
                summary = R.string.meta_github_url
            ) {
                clicked {
                    requests.trySend(Request(context.getString(R.string.meta_github_url)))
                }
            }
        }

        binding.content.addView(screen.root)
    }
}