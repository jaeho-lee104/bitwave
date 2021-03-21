package com.ecohouse.bitwave.utils

import androidx.fragment.app.Fragment
import com.ecohouse.bitwave.BitWaveApplication
import com.ecohouse.bitwave.ViewModelFactory


/**
 * @author leejaeho on 2021. 03. 21..
 */

fun Fragment.getViewModelFactory(): ViewModelFactory {
    val repository = (requireContext().applicationContext as BitWaveApplication).upbitRepository
    return ViewModelFactory(repository, this)
}
