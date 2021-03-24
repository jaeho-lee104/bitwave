package com.ecohouse.bitwave.utils

import androidx.annotation.ColorRes
import androidx.annotation.DimenRes


/**
 * @author leejaeho on 2021. 03. 24..
 */

fun getColor(@ColorRes resId: Int) = applicationContext.resources.getColor(resId, null)

fun getDimensionPixelSize(@DimenRes resId: Int): Int =
    applicationContext.resources.getDimensionPixelSize(resId)

fun getDimension(@DimenRes resId: Int): Float = applicationContext.resources.getDimension(resId)
