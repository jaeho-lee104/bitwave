package com.ecohouse.bitwave

import android.app.Application
import com.ecohouse.bitwave.data.UpbitRepository


/**
 * @author user on 2021. 03. 21..
 */
class BitWaveApplication : Application() {

    val upbitRepository: UpbitRepository
        get() = ServiceLocator.provideUpbitRepository(this)

}