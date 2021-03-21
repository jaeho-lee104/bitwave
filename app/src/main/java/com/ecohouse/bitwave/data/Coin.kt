package com.ecohouse.bitwave.data

import java.util.*


/**
 * @author leejaeho on 2021. 03. 21..
 */
data class Coin(
    var id: String = UUID.randomUUID().toString(),
    var title: String = "",
    var price: String = ""
) {
}