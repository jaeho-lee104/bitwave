package com.ecohouse.bitwave.data.network

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


/**
 * @author leejaeho on 2021. 03. 21..
 */
object UpbitClient {

    private const val BASE_URL = "https://api.upbit.com/v1/"

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .client(
                OkHttpClient.Builder()
                    .callTimeout(5, TimeUnit.SECONDS)
                    .connectTimeout(5, TimeUnit.SECONDS)
                    .readTimeout(5, TimeUnit.SECONDS)
                    .writeTimeout(5, TimeUnit.SECONDS)
                    .build()
            )
            .baseUrl(BASE_URL)
            .addConverterFactory(
                GsonConverterFactory.create(
                    GsonBuilder().excludeFieldsWithoutExposeAnnotation().create()
                )
            )
            .build()
    }

    private val service: UpbitService by lazy {
        retrofit.create(UpbitService::class.java)
    }

    fun marketAll(): Call<List<Market>> {
        return service.marketAll()
    }

    fun ticker(markets: List<String>): Call<List<Ticker>> {
        return service.ticker(markets.joinToString(separator = ","))
    }

    fun candleDays(market: String, to: String, count: Int): Call<List<Candle>> {
        return service.candleDays(market, to, count)
    }

}