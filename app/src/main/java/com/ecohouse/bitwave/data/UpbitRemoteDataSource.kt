package com.ecohouse.bitwave.data

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ecohouse.bitwave.data.network.Market
import com.ecohouse.bitwave.data.network.UpbitClient


/**
 * @author leejaeho on 2021. 03. 21..
 */
object UpbitRemoteDataSource : UpbitDataSource {

    private val marketInfos = LinkedHashMap<String, String>()

    private val observableCoins = MutableLiveData<Result<List<Coin>>>()

    override suspend fun refreshCoins() {
        observableCoins.value = getCoins()
    }

    @WorkerThread
    override suspend fun getCoins(): Result<List<Coin>> {
        val markets = getKrwMarkets()
        if (markets.isNullOrEmpty()) {
            return Result.Error(IllegalStateException("market list is empty !!"))
        }
        updateMarketInfos(markets)
        val coins = getKrwCoins(markets)
        return Result.Success(coins)
    }

    @WorkerThread
    private fun getKrwMarkets(): List<Market> {
        val call = UpbitClient.marketAll()
        val response = call.execute()
        return response.body()?.filter {
            it.market.contains("KRW-")
        } ?: emptyList()
    }

    @WorkerThread
    private fun getKrwCoins(markets: List<Market>): List<Coin> {
        val call = UpbitClient.ticker(markets.map { it.market })
        val response = call.execute()
        return response.body()?.map {
            Coin(name = marketInfos[it.market] ?: it.market, price = it.tradePrice.toString())
        } ?: emptyList()
    }

    private fun updateMarketInfos(markets: List<Market>) {
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
}