package com.ecohouse.bitwave

import android.content.Context
import com.ecohouse.bitwave.data.*


/**
 * @author leejaeho on 2021. 03. 21..
 */
object ServiceLocator {

    private val lock = Any()

    @Volatile
    private var upbitRepository: UpbitRepository? = null

    fun provideUpbitRepository(context: Context): UpbitRepository {
        synchronized(lock) {
            return upbitRepository ?: upbitRepository ?: createUpbitRepository(context)
        }
    }

    private fun createUpbitRepository(context: Context): UpbitRepository {
        val newRepo =
            DefaultUpbitRepository(UpbitRemoteDataSource, createUpbitLocalDataSource(context))
        upbitRepository = newRepo
        return newRepo
    }

    private fun createUpbitLocalDataSource(context: Context): UpbitDataSource {
        return UpbitLocalDataSource()
    }

}