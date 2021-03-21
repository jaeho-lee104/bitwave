package com.ecohouse.bitwave.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData


/**
 * @author leejaeho on 2021. 03. 21..
 */

class UpbitLocalDataSource : UpbitDataSource {

    private val observableCoins = MutableLiveData<Result<List<Coin>>>()
    private var COINS_LOCAL_DATA = LinkedHashMap<String, Coin>()

    override suspend fun refreshCoins() {
        observableCoins.value = getCoins()
    }

    override fun observeCoins(): LiveData<Result<List<Coin>>> {
        return observableCoins
    }

    override suspend fun getCoins(): Result<List<Coin>> {
        val coins = COINS_LOCAL_DATA.values.toList()
        return Result.Success(coins)
    }

    override suspend fun deleteAllCoins() {
        COINS_LOCAL_DATA.clear()
    }

    override suspend fun saveCoins(coins: List<Coin>) {
        coins.forEach {
            COINS_LOCAL_DATA[it.id] = it
        }
        refreshCoins()
    }

}