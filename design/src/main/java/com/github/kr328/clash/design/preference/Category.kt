package com.android.system.updater.design.preference

import android.view.View
import androidx.annotation.StringRes
import com.android.system.updater.design.databinding.PreferenceCategoryBinding
import com.android.system.updater.design.util.layoutInflater

fun PreferenceScreen.category(
    @StringRes text: Int,
) {
    val binding = PreferenceCategoryBinding
        .inflate(context.layoutInflater, root, false)

    binding.textView.text = context.getString(text)

    addElement(object : Preference {
        override val view: View
            get() = binding.root
        override var enabled: Boolean
            get() = binding.root.isEnabled
            set(value) {
                binding.root.isEnabled = value
            }
    })
}