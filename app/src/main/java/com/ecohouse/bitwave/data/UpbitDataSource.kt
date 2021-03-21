package com.ecohouse.bitwave.data

import androidx.lifecycle.LiveData


/**
 * @author leejaeho on 2021. 03. 21..
 */
interface UpbitDataSource {

    suspend fun refreshCoins()

    fun observeCoins(): LiveData<Result<List<Coin>>>

    suspend fun getCoins(): Result<List<Coin>>

    suspend fun deleteAllCoins()

    suspend fun saveCoins(coins: List<Coin>)

}