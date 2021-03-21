package com.ecohouse.bitwave.data

import androidx.lifecycle.LiveData


/**
 * @author leejaeho on 2021. 03. 21..
 */
interface UpbitRepository {

    fun observeCoins(): LiveData<Result<List<Coin>>>

    suspend fun refreshCoins()

}