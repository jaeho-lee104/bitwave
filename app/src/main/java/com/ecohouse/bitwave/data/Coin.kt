package com.ecohouse.bitwave.data


/**
 * @author leejaeho on 2021. 03. 21..
 */
data class Coin(
    val market: String,
    val name: String,
    val price: Double,
    val changeRate: Double,
    val volume: Double
)