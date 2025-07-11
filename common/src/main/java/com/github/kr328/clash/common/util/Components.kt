package com.android.system.updater.common.util

import android.content.ComponentName
import android.content.Intent
import com.android.system.updater.common.Global
import kotlin.reflect.KClass

val KClass<*>.componentName: ComponentName
    get() = ComponentName(Global.application.packageName, this.java.name)

val KClass<*>.intent: Intent
    get() = Intent(Global.application, this.java)