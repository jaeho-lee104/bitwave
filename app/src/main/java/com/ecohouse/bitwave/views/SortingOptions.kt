package com.ecohouse.bitwave.views

import com.ecohouse.bitwave.data.Coin


/**
 * @author leejaeho on 2021. 03. 24..
 */
enum class SortingOptions(val code: Int) {

    COIN_NAME(0) {
        override fun sort(coins: List<Coin>, descending: Boolean): List<Coin> = if (descending) {
            coins.sortedByDescending { it.name }
        } else {
            coins.sortedBy { it.name }
        }
    },
    PRICE(1) {
        override fun sort(coins: List<Coin>, descending: Boolean): List<Coin> = if (descending) {
            coins.sortedByDescending { it.price }
        } else {
            coins.sortedBy { it.price }
        }
    },
    CHANGE_PRICE_RATE(2) {
        override fun sort(coins: List<Coin>, descending: Boolean): List<Coin> = if (descending) {
            coins.sortedByDescending { it.priceChangeRate }
        } else {
            coins.sortedBy { it.priceChangeRate }
        }

    },
    VOLUME(3) {
        override fun sort(coins: List<Coin>, descending: Boolean): List<Coin> = if (descending) {
            coins.sortedByDescending { it.volume }
        } else {
            coins.sortedBy { it.volume }
        }

    },
    CHANGE_VOLUME_RATE(4) {
        override fun sort(coins: List<Coin>, descending: Boolean): List<Coin> = if (descending) {
            coins.sortedByDescending { it.volumeChangeRate }
        } else {
            coins.sortedBy { it.volumeChangeRate }
        }

    },
    BREAK_OUT_RATE(5) {
        override fun sort(coins: List<Coin>, descending: Boolean): List<Coin> = if (descending) {
            coins.sortedByDescending { it.breakOutRate }
        } else {
            coins.sortedBy { it.breakOutRate }
        }

    },
    NONE(6) {
        override fun sort(coins: List<Coin>, descending: Boolean): List<Coin> {
            return coins
        }
    };

    companion object {
        fun of(code: Int): SortingOptions {
            values().forEach {
                if (it.code == code) {
                    return it
                }
            }
            return NONE
        }
    }

    abstract fun sort(coins: List<Coin>, descending: Boolean): List<Coin>

}