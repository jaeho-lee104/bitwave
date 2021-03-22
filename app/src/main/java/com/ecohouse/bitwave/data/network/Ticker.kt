package com.ecohouse.bitwave.data.network

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


/**
 * @author leejaeho on 2021. 03. 22..
 */
data class Ticker(
    @Expose
    @SerializedName("market")
    private var _market: String? = null,

    @Expose
    @SerializedName("trade_date")
    private var _trade_date: String? = null,

    @Expose
    @SerializedName("trade_time")
    private var _trade_time: String? = null,

    @Expose
    @SerializedName("trade_date_kst")
    private var _trade_date_kst: String? = null,

    @Expose
    @SerializedName("trade_time_kst")
    private var _trade_time_kst: String? = null,

    @Expose
    @SerializedName("opening_price")
    private var _opening_price: Double? = null,

    @Expose
    @SerializedName("high_price")
    private var _high_price: Double? = null,

    @Expose
    @SerializedName("low_price")
    private var _low_price: Double? = null,

    @Expose
    @SerializedName("trade_price")
    private var _trade_price: Double? = null,

    @Expose
    @SerializedName("prev_closing_price")
    private var _prev_closing_price: Double? = null,

    @Expose
    @SerializedName("change")
    private var _change: String? = null,

    @Expose
    @SerializedName("change_price")
    private var _change_price: Double? = null,

    @Expose
    @SerializedName("change_rate")
    private var _change_rate: Double? = null,

    @Expose
    @SerializedName("signed_change_price")
    private var _signed_change_price: Double? = null,

    @Expose
    @SerializedName("signed_change_rate")
    private var _signed_change_rate: Double? = null,

    @Expose
    @SerializedName("trade_volume")
    private var _trade_volume: Double? = null,

    @Expose
    @SerializedName("acc_trade_price")
    private var _acc_trade_price: Double? = null,

    @Expose
    @SerializedName("acc_trade_price_24h")
    private var _acc_trade_price_24h: Double? = null,

    @Expose
    @SerializedName("acc_trade_volume")
    private var _acc_trade_volume: Double? = null,

    @Expose
    @SerializedName("acc_trade_volume_24h")
    private var _acc_trade_volume_24h: Double? = null,

    @Expose
    @SerializedName("highest_52_week_price")
    private var _highest_52_week_price: Double? = null,

    @Expose
    @SerializedName("highest_52_week_date")
    private var _highest_52_week_date: String? = null,

    @Expose
    @SerializedName("lowest_52_week_price")
    private var _lowest_52_week_price: Double? = null,

    @Expose
    @SerializedName("lowest_52_week_date")
    private var _lowest_52_week_date: String? = null,

    @Expose
    @SerializedName("timestamp")
    private var _timestamp: String? = null

) {
    val market: String
        get() = _market ?: ""

    val tradeDate: String
        get() = _trade_date ?: ""

    val tradeTime: String
        get() = _trade_time ?: ""

    val tradeDateKst: String
        get() = _trade_date_kst ?: ""

    val tradeTimeKst: String
        get() = _trade_time_kst ?: ""

    val openingPrice: Double
        get() = _opening_price ?: Double.MIN_VALUE

    val highPrice: Double
        get() = _high_price ?: Double.MIN_VALUE

    val lowPrice: Double
        get() = _low_price ?: Double.MIN_VALUE

    val tradePrice: Double
        get() = _trade_price ?: Double.MIN_VALUE

    val prevClosingPrice: Double
        get() = _prev_closing_price ?: Double.MIN_VALUE

    val change: String
        get() = _change ?: ""

    val changePrice: Double
        get() = _change_price ?: Double.MIN_VALUE

    val changeRate: Double
        get() = _change_rate ?: Double.MIN_VALUE

    val signedChangePrice: Double
        get() = _signed_change_price ?: Double.MIN_VALUE

    val signedChangeRate: Double
        get() = _signed_change_rate ?: Double.MIN_VALUE

    val tradeVolume: Double
        get() = _trade_volume ?: Double.MIN_VALUE

    val accTradePrice: Double
        get() = _acc_trade_price ?: Double.MIN_VALUE

    val accTradePrice_24h: Double
        get() = _acc_trade_price_24h ?: Double.MIN_VALUE

    val accTradeVolume: Double
        get() = _acc_trade_volume ?: Double.MIN_VALUE

    val accTradeVolume24h: Double
        get() = _acc_trade_volume_24h ?: Double.MIN_VALUE

    val highest52WeekPrice: Double
        get() = _highest_52_week_price ?: Double.MIN_VALUE

    val highest52WeekDate: String
        get() = _highest_52_week_date ?: ""

    val lowest52WeekPrice: Double
        get() = _lowest_52_week_price ?: Double.MIN_VALUE

    val lowest52WeekDate: String
        get() = _lowest_52_week_date ?: ""

    val timestamp: String
        get() = _timestamp ?: ""

}