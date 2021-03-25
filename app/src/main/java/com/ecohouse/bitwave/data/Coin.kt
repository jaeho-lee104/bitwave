package com.ecohouse.bitwave.data


/**
 * @author leejaeho on 2021. 03. 21..
 */
data class Coin(
    val market: String,
    val name: String,
    val price: Double,
    val priceChangeRate: Double,
    val volume: Double,
    val openingPrice: Double,
    val prevVolume: Double,
    val prevHighPrice: Double,
    val prevLowPrice: Double,
    val volumeChangeRate: Double,
    val breakOutRate: Double,
    val rsi: Double
)