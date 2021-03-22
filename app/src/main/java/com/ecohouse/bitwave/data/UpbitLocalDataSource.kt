package com.ecohouse.bitwave.data

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData


/**
 * @author leejaeho on 2021. 03. 21..
 */

class UpbitLocalDataSource : UpbitDataSource {

    private val observableCoins = MutableLiveData<Result<List<Coin>>>()
    private var localCoins = LinkedHashMap<String, Coin>()

    @SuppressLint("NullSafeMutableLiveData")
    override suspend fun refreshCoins() {
        observableCoins.value = getCoins()
    }

    override fun observeCoins(): LiveData<Result<List<Coin>>> {
        return observableCoins
    }

    override suspend fun getCoins(): Result<List<Coin>> {
        val coins = localCoins.values.toList()
        return Result.Success(coins)
    }

    override suspend fun deleteAllCoins() {
        localCoins.clear()
    }

    override suspend fun saveCoins(coins: List<Coin>) {
        coins.forEach {
            localCoins[it.market] = it
        }
        refreshCoins()
    }

}