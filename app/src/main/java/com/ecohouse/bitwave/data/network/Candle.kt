package com.ecohouse.bitwave.data.network

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


/**
 * @author leejaeho on 2021. 03. 24..
 */
data class Candle(
    @Expose
    @SerializedName("market")
    private var _market: String?,

    @Expose
    @SerializedName("candle_date_time_utc")
    private var _candleDateTimeUtc: String?,

    @Expose
    @SerializedName("candle_date_time_kst")
    private var _candleDateTimeKst: String?,

    @Expose
    @SerializedName("opening_price")
    private var _openingPrice: Double?,

    @Expose
    @SerializedName("high_price")
    private var _highPrice: Double?,

    @Expose
    @SerializedName("low_price")
    private var _lowPrice: Double?,

    @Expose
    @SerializedName("trade_price")
    private var _tradePrice: Double?,

    @Expose
    @SerializedName("timestamp")
    private var _timestamp: Long?,

    @Expose
    @SerializedName("candle_acc_trade_price")
    private var _candleAccTradePrice: Double?,

    @Expose
    @SerializedName("candle_acc_trade_volume")
    private var _candleAccTradeVolume: Double?,

    @Expose
    @SerializedName("prev_closing_price")
    private var _prevClosingPrice: Double?,

    @Expose
    @SerializedName("change_price")
    private var _changePrice: Double?,

    @Expose
    @SerializedName("change_rate")
    private var _changeRate: Double?,

    ) {

    val market: String
        get() = _market ?: ""

    val candleDateTimeUtc: String
        get() = _candleDateTimeUtc ?: ""

    val candleDateTimeKst: String
        get() = _candleDateTimeKst ?: ""

    val openingPrice: Double
        get() = _openingPrice ?: Double.MIN_VALUE

    val highPrice: Double
        get() = _highPrice ?: Double.MIN_VALUE

    val lowPrice: Double
        get() = _lowPrice ?: Double.MIN_VALUE

    val tradePrice: Double
        get() = _tradePrice ?: Double.MIN_VALUE

    val timestamp: Long
        get() = _timestamp ?: Long.MIN_VALUE

    val candleAccTradePrice: Double
        get() = _candleAccTradePrice ?: Double.MIN_VALUE

    val candleAccTradeVolume: Double
        get() = _candleAccTradeVolume ?: Double.MIN_VALUE

    val prevClosingPrice: Double
        get() = _prevClosingPrice ?: Double.MIN_VALUE

    val changePrice: Double
        get() = _changePrice ?: Double.MIN_VALUE

    val changeRate: Double
        get() = _changeRate ?: Double.MIN_VALUE
}