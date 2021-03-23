package com.ecohouse.bitwave.utils

import java.text.DecimalFormat


/**
 * @author leejaeho on 2021. 03. 23..
 */

private val formatter = DecimalFormat("###,###.#")

fun Double.formatDisplay(): String = formatter.format(this)
