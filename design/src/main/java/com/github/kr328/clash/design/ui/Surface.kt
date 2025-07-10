package com.android.system.update.design.ui

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.android.system.update.design.BR

class Surface : BaseObservable() {
    var insets: Insets = Insets.EMPTY
        @Bindable get
        set(value) {
            field = value

            notifyPropertyChanged(BR.insets)
        }
}