package com.android.system.update.design.preference

import android.view.View
import androidx.annotation.StringRes
import com.android.system.update.design.databinding.PreferenceTipsBinding
import com.android.system.update.design.util.getHtml
import com.android.system.update.design.util.layoutInflater
import com.android.system.update.design.util.root

interface TipsPreference : Preference {
    var text: CharSequence?
}

fun PreferenceScreen.tips(
    @StringRes text: Int,
    configure: TipsPreference.() -> Unit = {},
): TipsPreference {
    val binding = PreferenceTipsBinding
        .inflate(context.layoutInflater, context.root, false)
    val impl = object : TipsPreference {
        override var text: CharSequence?
            get() = binding.tips.text
            set(value) {
                binding.tips.text = value
            }
        override val view: View
            get() = binding.root
        override var enabled: Boolean
            get() = binding.root.isEnabled
            set(value) {
                binding.root.isEnabled = value
            }
    }

    binding.tips.text = context.getHtml(text)

    impl.configure()

    addElement(impl)

    return impl
}