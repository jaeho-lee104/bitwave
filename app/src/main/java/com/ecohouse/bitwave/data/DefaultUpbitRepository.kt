package com.ecohouse.bitwave.data

import androidx.lifecycle.LiveData
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


/**
 * @author leejaeho on 2021. 03. 21..
 */
class DefaultUpbitRepository(
    private val upbitRemoteDataSource: UpbitDataSource,
    private val upbitLocalDataSource: UpbitDataSource,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : UpbitRepository {

    override fun observeCoins(): LiveData<Result<List<Coin>>> {
        return upbitLocalDataSource.observeCoins()
    }

    override suspend fun refreshCoins() {
        updateCoinsFromRemoteDataSource()
    }

    private suspend fun updateCoinsFromRemoteDataSource() {
        val remoteCoins = withContext(ioDispatcher) {
            upbitRemoteDataSource.getCoins()
        }
        if (remoteCoins is Result.Success) {
            upbitLocalDataSource.deleteAllCoins()
            upbitLocalDataSource.saveCoins(remoteCoins.data)

        } else if (remoteCoins is Result.Error) {
            println("updateCoinsFromRemoteDataSource failed. cause: ${remoteCoins.exception}")
            upbitLocalDataSource.deleteAllCoins()
            upbitLocalDataSource.saveCoins(emptyList())
        }
    }

}