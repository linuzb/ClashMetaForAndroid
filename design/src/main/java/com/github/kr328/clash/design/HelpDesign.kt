package com.android.system.update.design

import android.content.Context
import android.net.Uri
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

class HelpDesign(
    context: Context,
    openLink: (Uri) -> Unit,
) : Design<Unit>(context) {
    private val binding = DesignSettingsCommonBinding
        .inflate(context.layoutInflater, context.root, false)

    override val root: View
        get() = binding.root

    init {
        binding.surface = surface

        binding.activityBarLayout.applyFrom(context)

        binding.scrollRoot.bindAppBarElevation(binding.activityBarLayout)

        val screen = preferenceScreen(context) {
            tips(R.string.tips_help)

            category(R.string.document)

            clickable(
                title = R.string.clash_wiki,
                summary = R.string.clash_wiki_url
            ) {
                clicked {
                    openLink(Uri.parse(context.getString(R.string.clash_wiki_url)))
                }
            }

            clickable(
                title = R.string.clash_meta_wiki,
                summary = R.string.clash_meta_wiki_url
            ) {
                clicked {
                    openLink(Uri.parse(context.getString(R.string.clash_meta_wiki_url)))
                }
            }

            category(R.string.sources)

            clickable(
                title = R.string.clash_meta_core,
                summary = R.string.clash_meta_core_url
            ) {
                clicked {
                    openLink(Uri.parse(context.getString(R.string.clash_meta_core_url)))
                }
            }

            clickable(
                title = R.string.clash_meta_for_android,
                summary = R.string.meta_github_url
            ) {
                clicked {
                    openLink(Uri.parse(context.getString(R.string.meta_github_url)))
                }
            }
        }

        binding.content.addView(screen.root)
    }
}