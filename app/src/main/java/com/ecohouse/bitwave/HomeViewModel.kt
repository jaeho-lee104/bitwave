package com.ecohouse.bitwave

import android.annotation.SuppressLint
import androidx.lifecycle.*
import com.ecohouse.bitwave.data.Coin
import com.ecohouse.bitwave.data.Result
import com.ecohouse.bitwave.data.UpbitRepository
import kotlinx.coroutines.launch


/**
 * @author user on 2021. 03. 21..
 */
class HomeViewModel(private val upbitRepository: UpbitRepository) : ViewModel() {

    private val _forceUpdate = MutableLiveData(false)

    private val _items: LiveData<List<Coin>> =
        _forceUpdate.switchMap { forceUpdate ->
            if (forceUpdate) {
                _dataLoading.value = true
                viewModelScope.launch {
                    upbitRepository.refreshCoins()
                    _dataLoading.value = false
                }
            }
            upbitRepository.observeCoins().distinctUntilChanged().switchMap {
                filterCoins(it)
            }
        }
    val items: LiveData<List<Coin>> = _items


    private val _dataLoading = MutableLiveData<Boolean>()
    val dataLoading: LiveData<Boolean> = _dataLoading

    fun loadCoins(forceUpdate: Boolean) {
        _forceUpdate.value = forceUpdate
    }

    @SuppressLint("NullSafeMutableLiveData")
    private fun filterCoins(coinsResult: Result<List<Coin>>): LiveData<List<Coin>> {
        val result = MutableLiveData<List<Coin>>()
        if (coinsResult is Result.Success) {
            result.value = coinsResult.data
        } else {
            result.value = emptyList()
        }
        return result
    }
}