package com.ecohouse.bitwave.data.network

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


/**
 * @author leejaeho on 2021. 03. 22..
 */
interface UpbitService {

    @GET("market/all")
    fun marketAll(): Call<List<Market>>

    @GET("ticker")
    fun ticker(@Query("markets") markets: String): Call<List<Ticker>>

    @GET("candles/days")
    fun candleDays(
        @Query("market") market: String,
        @Query("to") to: String,
        @Query("count") count: Int
    ): Call<List<Candle>>

}