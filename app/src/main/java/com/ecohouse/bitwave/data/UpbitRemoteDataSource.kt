package com.ecohouse.bitwave.data

import android.annotation.SuppressLint
import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ecohouse.bitwave.data.network.Candle
import com.ecohouse.bitwave.data.network.Market
import com.ecohouse.bitwave.data.network.Ticker
import com.ecohouse.bitwave.data.network.UpbitClient
import kotlinx.coroutines.delay
import java.time.Instant
import java.time.temporal.ChronoUnit


/**
 * @author leejaeho on 2021. 03. 21..
 */
object UpbitRemoteDataSource : UpbitDataSource {

    private var lastLoadedTimeStamp: Long = Long.MIN_VALUE
    private val marketInfos = LinkedHashMap<String, String>()
    private val yesterdayCandleMap = LinkedHashMap<String, Candle>()

    private val observableCoins = MutableLiveData<Result<List<Coin>>>()

    @SuppressLint("NullSafeMutableLiveData")
    override suspend fun refreshCoins() {
        observableCoins.value = getCoins()
    }

    @WorkerThread
    override suspend fun getCoins(): Result<List<Coin>> {
        val markets = try {
            loadKrwMarkets()
        } catch (e: Exception) {
            return Result.Error(e)
        }
        if (markets.isNullOrEmpty()) {
            return Result.Error(IllegalStateException("market list is empty!!"))
        }
        updateMarkets(markets)
        val tickers = loadTickers(markets)
        if (tickers.isNullOrEmpty()) {
            return Result.Error(IllegalStateException("tickers are empty!!"))
        }
        if (yesterdayCandleMap.isEmpty()) {
            updateYesterdayCandles(markets)
        }
        if (yesterdayCandleMap.isEmpty()) {
            return Result.Error(IllegalStateException("candles are empty!!"))
        }
        val coins = manufactureCoins(tickers)
        return Result.Success(coins)
    }

    private fun manufactureCoins(tickers: List<Ticker>): List<Coin> {
        return tickers.map {
            val candle = yesterdayCandleMap[it.market]!!
            Coin(
                market = it.market,
                name = marketInfos[it.market] ?: it.market,
                price = it.tradePrice,
                changeRate = it.signedChangeRate.times(100),
                volume = it.accTradePrice,
                openingPrice = it.openingPrice,
                prevVolume = candle.candleAccTradePrice,
                prevHighPrice = candle.highPrice,
                prevLowPrice = candle.lowPrice
            )
        }
    }

    @WorkerThread
    private fun loadKrwMarkets(): List<Market> {
        val call = UpbitClient.marketAll()
        val response = call.execute()
        return response.body()?.filter {
            it.market.contains("KRW-")
        } ?: emptyList()
    }

    @WorkerThread
    private fun loadTickers(markets: List<Market>): List<Ticker> {
        val call = UpbitClient.ticker(markets.map { it.market })
        val response = call.execute()
        lastLoadedTimeStamp = response.body()?.firstOrNull()?.timestamp ?: lastLoadedTimeStamp
        return response.body() ?: emptyList()
    }

    @WorkerThread
    private suspend fun updateYesterdayCandles(markets: List<Market>) {
        if (lastLoadedTimeStamp == Long.MIN_VALUE) {
            return
        }
        val instant = Instant.ofEpochMilli(lastLoadedTimeStamp).minus(1, ChronoUnit.DAYS).toString()
        markets.forEach {
            val call = UpbitClient.candleDays(it.market, instant, 1)
            val response = call.execute()
            response.body()?.let { candle ->
                yesterdayCandleMap[it.market] = candle.first()
            }
            delay(200)
        }
    }

    private fun updateMarkets(markets: List<Market>) {
        markets.forEach {
            marketInfos[it.market] = it.koreanName
        }
    }

    override fun observeCoins(): LiveData<Result<List<Coin>>> {
        return observableCoins
    }

    override suspend fun deleteAllCoins() {
        //NO-OP
    }

    override suspend fun saveCoins(coins: List<Coin>) {
        //NO-OP
    }

    //Instant.ofEpochMilli(it.timestamp.toLong()).minus(1, ChronoUnit.DAYS)
    //2021-03-22T15:49:28.105Z
}