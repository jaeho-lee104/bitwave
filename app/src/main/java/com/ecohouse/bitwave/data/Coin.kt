package com.ecohouse.bitwave.data


/**
 * @author leejaeho on 2021. 03. 21..
 */
data class Coin(
    var market: String = "",
    var name: String = "",
    var price: Double = Double.MIN_VALUE
) {
}