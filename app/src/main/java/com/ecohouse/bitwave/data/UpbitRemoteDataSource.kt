package com.ecohouse.bitwave.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.delay


/**
 * @author leejaeho on 2021. 03. 21..
 */
object UpbitRemoteDataSource : UpbitDataSource {

    private const val SERVICE_LATENCY_IN_MILLIS = 2000L
    private var COINS_REMOTE_DATA = LinkedHashMap<String, Coin>(2)

    init {
        addCoin("XRP", "500")
        addCoin("EOS", "4000")
    }

    private val observableCoins = MutableLiveData<Result<List<Coin>>>()

    override suspend fun refreshCoins() {
        observableCoins.value = getCoins()
    }

    override suspend fun getCoins(): Result<List<Coin>> {
        val coins = COINS_REMOTE_DATA.values.toList()
        delay(SERVICE_LATENCY_IN_MILLIS)
        return Result.Success(coins)
    }

    override fun observeCoins(): LiveData<Result<List<Coin>>> {
        return observableCoins
    }

    override suspend fun deleteAllCoins() {
        COINS_REMOTE_DATA.clear()
    }

    override suspend fun saveCoins(coins: List<Coin>) {
        coins.forEach {
            COINS_REMOTE_DATA[it.id] = it
        }
    }

    private fun addCoin(title: String, price: String) {
        val newCoin = Coin(title, price)
        COINS_REMOTE_DATA[newCoin.id] = newCoin
    }
}