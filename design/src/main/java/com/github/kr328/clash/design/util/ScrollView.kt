package com.android.system.updater.design.util

import com.android.system.updater.design.view.ObservableScrollView

val ObservableScrollView.isTop: Boolean
    get() = scrollX == 0 && scrollY == 0
