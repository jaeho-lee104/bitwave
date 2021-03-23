package com.ecohouse.bitwave.data.network

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


/**
 * @author leejaeho on 2021. 03. 22..
 */
data class Ticker(
    @Expose
    @SerializedName("market")
    private var _market: String?,

    @Expose
    @SerializedName("trade_date")
    private var _tradeDate: String?,

    @Expose
    @SerializedName("trade_time")
    private var _tradeTime: String?,

    @Expose
    @SerializedName("trade_date_kst")
    private var _tradeDateKst: String?,

    @Expose
    @SerializedName("trade_time_kst")
    private var _tradeTimeKst: String?,

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
    @SerializedName("prev_closing_price")
    private var _prevClosingPrice: Double?,

    @Expose
    @SerializedName("change")
    private var _change: String?,

    @Expose
    @SerializedName("change_price")
    private var _changePrice: Double?,

    @Expose
    @SerializedName("change_rate")
    private var _changeRate: Double?,

    @Expose
    @SerializedName("signed_change_price")
    private var _signedChangePrice: Double?,

    @Expose
    @SerializedName("signed_change_rate")
    private var _signedChangeRate: Double?,

    @Expose
    @SerializedName("trade_volume")
    private var _tradeVolume: Double?,

    @Expose
    @SerializedName("acc_trade_price")
    private var _accTradePrice: Double?,

    @Expose
    @SerializedName("acc_trade_price_24h")
    private var _accTradePrice24h: Double?,

    @Expose
    @SerializedName("acc_trade_volume")
    private var _accTradeVolume: Double?,

    @Expose
    @SerializedName("acc_trade_volume_24h")
    private var _accTradeVolume24h: Double?,

    @Expose
    @SerializedName("highest_52_week_price")
    private var _highest52WeekPrice: Double?,

    @Expose
    @SerializedName("highest_52_week_date")
    private var _highest52WeekDate: String?,

    @Expose
    @SerializedName("lowest_52_week_price")
    private var _lowest52WeekPrice: Double?,

    @Expose
    @SerializedName("lowest_52_week_date")
    private var _lowest52WeekDate: String?,

    @Expose
    @SerializedName("timestamp")
    private var _timestamp: Long?

) {
    val market: String
        get() = _market ?: ""

    val tradeDate: String
        get() = _tradeDate ?: ""

    val tradeTime: String
        get() = _tradeTime ?: ""

    val tradeDateKst: String
        get() = _tradeDateKst ?: ""

    val tradeTimeKst: String
        get() = _tradeTimeKst ?: ""

    val openingPrice: Double
        get() = _openingPrice ?: Double.MIN_VALUE

    val highPrice: Double
        get() = _highPrice ?: Double.MIN_VALUE

    val lowPrice: Double
        get() = _lowPrice ?: Double.MIN_VALUE

    val tradePrice: Double
        get() = _tradePrice ?: Double.MIN_VALUE

    val prevClosingPrice: Double
        get() = _prevClosingPrice ?: Double.MIN_VALUE

    val change: String
        get() = _change ?: ""

    val changePrice: Double
        get() = _changePrice ?: Double.MIN_VALUE

    val changeRate: Double
        get() = _changeRate ?: Double.MIN_VALUE

    val signedChangePrice: Double
        get() = _signedChangePrice ?: Double.MIN_VALUE

    val signedChangeRate: Double
        get() = _signedChangeRate ?: Double.MIN_VALUE

    val tradeVolume: Double
        get() = _tradeVolume ?: Double.MIN_VALUE

    val accTradePrice: Double
        get() = _accTradePrice ?: Double.MIN_VALUE

    val accTradePrice_24h: Double
        get() = _accTradePrice24h ?: Double.MIN_VALUE

    val accTradeVolume: Double
        get() = _accTradeVolume ?: Double.MIN_VALUE

    val accTradeVolume24h: Double
        get() = _accTradeVolume24h ?: Double.MIN_VALUE

    val highest52WeekPrice: Double
        get() = _highest52WeekPrice ?: Double.MIN_VALUE

    val highest52WeekDate: String
        get() = _highest52WeekDate ?: ""

    val lowest52WeekPrice: Double
        get() = _lowest52WeekPrice ?: Double.MIN_VALUE

    val lowest52WeekDate: String
        get() = _lowest52WeekDate ?: ""

    val timestamp: Long
        get() = _timestamp ?: Long.MIN_VALUE

}