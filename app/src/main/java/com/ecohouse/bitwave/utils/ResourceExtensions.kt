package com.ecohouse.bitwave.utils

import androidx.annotation.ColorRes


/**
 * @author leejaeho on 2021. 03. 24..
 */

fun Any?.getColor(@ColorRes resId: Int) = applicationContext.resources.getColor(resId, null)
