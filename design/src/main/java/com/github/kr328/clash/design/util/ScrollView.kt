package com.github.android.updater.design.util

import com.github.android.updater.design.view.ObservableScrollView

val ObservableScrollView.isTop: Boolean
    get() = scrollX == 0 && scrollY == 0
