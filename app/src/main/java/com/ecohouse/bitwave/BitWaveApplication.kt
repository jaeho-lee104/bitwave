package com.ecohouse.bitwave

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import com.ecohouse.bitwave.data.UpbitRepository


/**
 * @author user on 2021. 03. 21..
 */
class BitWaveApplication : Application() {

    companion object {
        @SuppressLint("StaticFieldLeak")
        lateinit var context: Context
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        context = base!!
    }

    val upbitRepository: UpbitRepository
        get() = ServiceLocator.provideUpbitRepository(this)

}