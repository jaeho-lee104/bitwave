package com.ecohouse.bitwave

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import com.ecohouse.bitwave.coins.CoinListAdapter
import com.ecohouse.bitwave.data.Coin
import com.ecohouse.bitwave.databinding.FragmentHomeBinding
import com.ecohouse.bitwave.utils.getViewModelFactory
import com.ecohouse.bitwave.views.LoadingDialog
import com.ecohouse.bitwave.views.SortingOptions
import com.ecohouse.bitwave.views.SortingState
import com.ecohouse.bitwave.views.SortingStateView

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var listAdapter: CoinListAdapter
    private lateinit var loadingDialog: LoadingDialog
    private var sortingOptions: SortingOptions = SortingOptions.VOLUME
    private var descending = true
    private var handler = Handler(Looper.getMainLooper(), Handler.Callback {
        when (it.what) {
            LOADING_DIALOG_SHOW_MSG -> {
                if (!loadingDialog.isShowing) {
                    loadingDialog.show()
                }
            }
            LOADING_DIALOG_HIDE_MSG -> {
                if (loadingDialog.isShowing) {
                    loadingDialog.dismiss()
                }
            }
        }
        true
    })

    companion object {
        const val LOADING_DIALOG_DELAY_MS = 1000L
        const val LOADING_DIALOG_SHOW_MSG = 1
        const val LOADING_DIALOG_HIDE_MSG = 2
    }

    private val viewModel by viewModels<HomeViewModel> {
        getViewModelFactory()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupHeader()
        setupListAdapter()
        subscribeUi()
        loadingDialog = LoadingDialog(requireContext())
        viewModel.loadCoins(true)
    }

    private fun subscribeUi() {
        viewModel.items.observe(viewLifecycleOwner) {
            listAdapter.submitList(sortByCurrentOptions(it))
        }

        viewModel.dataLoading.observe(viewLifecycleOwner) {
            if (it) {
                handler.removeCallbacksAndMessages(null)
                handler.sendEmptyMessageDelayed(LOADING_DIALOG_SHOW_MSG, LOADING_DIALOG_DELAY_MS)
            } else {
                handler.removeCallbacksAndMessages(null)
                handler.sendEmptyMessage(LOADING_DIALOG_HIDE_MSG)
            }
        }
    }

    private fun setupHeader() {
        binding.volumeTitle.sortingState = SortingState.DESCENDING
        binding.coinNameTitle.setOnClickListener {
            handleSortViewOnClick(binding.coinNameTitle, SortingOptions.COIN_NAME)
        }
        binding.priceTitle.setOnClickListener {
            handleSortViewOnClick(binding.priceTitle, SortingOptions.PRICE)
        }
        binding.priceRateTitle.setOnClickListener {
            handleSortViewOnClick(binding.priceRateTitle, SortingOptions.CHANGE_PRICE_RATE)
        }
        binding.volumeTitle.setOnClickListener {
            handleSortViewOnClick(binding.volumeTitle, SortingOptions.VOLUME)
        }
        binding.volumeRateTitle.setOnClickListener {
            handleSortViewOnClick(binding.volumeRateTitle, SortingOptions.CHANGE_VOLUME_RATE)
        }
        binding.breakOutRate.setOnClickListener {
            handleSortViewOnClick(binding.breakOutRate, SortingOptions.BREAK_OUT_RATE)
        }
        binding.rsiValue.setOnClickListener {
            handleSortViewOnClick(binding.rsiValue, SortingOptions.RSI)
        }
    }

    private fun handleSortViewOnClick(view: SortingStateView, option: SortingOptions) {
        if (viewModel.items.value.isNullOrEmpty()) {
            return
        }

        if (sortingOptions == option) {
            descending = !descending
        } else {
            getSortingStateViewOf(sortingOptions)?.sortingState = SortingState.NONE
            sortingOptions = option
            descending = true
        }
        view.sortingState = if (descending) SortingState.DESCENDING else SortingState.ASCENDING
        listAdapter.submitList(sortByCurrentOptions(viewModel.items.value!!)) {
            binding.coinList.scrollToPosition(0)
        }
    }

    private fun sortByCurrentOptions(coins: List<Coin>): List<Coin> {
        return sortingOptions.sort(coins, descending)
    }


    private fun setupListAdapter() {
        binding.coinList.addItemDecoration(
            DividerItemDecoration(
                context,
                DividerItemDecoration.VERTICAL
            )
        )

        listAdapter = CoinListAdapter()
        binding.coinList.adapter = listAdapter
    }

    private fun getSortingStateViewOf(option: SortingOptions): SortingStateView? {
        return when (option) {
            SortingOptions.COIN_NAME -> binding.coinNameTitle
            SortingOptions.PRICE -> binding.priceTitle
            SortingOptions.CHANGE_PRICE_RATE -> binding.priceRateTitle
            SortingOptions.VOLUME -> binding.volumeTitle
            SortingOptions.CHANGE_VOLUME_RATE -> binding.volumeRateTitle
            SortingOptions.BREAK_OUT_RATE -> binding.breakOutRate
            SortingOptions.RSI -> binding.rsiValue
            else -> null
        }
    }
}