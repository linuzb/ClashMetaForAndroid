package com.github.android.updater.design.model

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.github.android.updater.core.model.Provider
import com.github.android.updater.design.BR

class ProviderState(
    val provider: Provider,
    updatedAt: Long,
    updating: Boolean,
) : BaseObservable() {
    var updatedAt: Long = updatedAt
        @Bindable get
        set(value) {
            field = value

            notifyPropertyChanged(BR.updatedAt)
        }

    var updating: Boolean = updating
        @Bindable get
        set(value) {
            field = value

            notifyPropertyChanged(BR.updating)
        }
}