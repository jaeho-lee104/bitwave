package com.ecohouse.bitwave

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.ecohouse.bitwave.data.Coin
import com.ecohouse.bitwave.databinding.FragmentHomeBinding
import com.ecohouse.bitwave.utils.getViewModelFactory
import com.ecohouse.bitwave.views.LoadingDialog
import com.ecohouse.bitwave.views.SortingOptions
import com.ecohouse.bitwave.views.SortingState
import com.ecohouse.bitwave.views.SortingStateView
import com.ecohouse.bitwave.views.recyclerview.CoinListAdapter
import com.ecohouse.bitwave.views.recyclerview.RowHeaderListAdapter

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var coinListAdapter: CoinListAdapter
    private lateinit var rowHeaderListAdapter: RowHeaderListAdapter
    private lateinit var loadingDialog: LoadingDialog
    private var sortingOptions: SortingOptions = SortingOptions.VOLUME
    private var descending = true
    private var scrollViewId: Int = 0
    private var handler = Handler(Looper.getMainLooper()) {
        if (isAdded.not() || isDetached) {
            return@Handler true
        }
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
    }

    companion object {
        const val LOADING_DIALOG_DELAY_MS = 1500L
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
            val sorted = sortByCurrentOptions(it)
            coinListAdapter.submitList(sorted)
            rowHeaderListAdapter.submitList(sorted)
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
        binding.highPriceRate.setOnClickListener {
            handleSortViewOnClick(binding.highPriceRate, SortingOptions.HIGH_PRICE_RATE)
        }
        binding.lowPriceRate.setOnClickListener {
            handleSortViewOnClick(binding.lowPriceRate, SortingOptions.LOW_PRICE_RATE)
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
        val sortedList = sortByCurrentOptions(viewModel.items.value!!)
        coinListAdapter.submitList(sortedList) {
            binding.coinList.scrollToPosition(0)
        }
        rowHeaderListAdapter.submitList(sortedList) {
            binding.rowHeaderList.scrollToPosition(0)
        }
    }

    private fun sortByCurrentOptions(coins: List<Coin>): List<Coin> {
        return sortingOptions.sort(coins, descending)
    }


    @SuppressLint("ClickableViewAccessibility")
    private fun setupListAdapter() {
        binding.coinList.addItemDecoration(
            DividerItemDecoration(
                context,
                DividerItemDecoration.VERTICAL
            )
        )

        coinListAdapter = CoinListAdapter()
        binding.coinList.adapter = coinListAdapter
        binding.coinList.setOnTouchListener { v, event ->
            if (event.actionMasked == MotionEvent.ACTION_DOWN
                || event.actionMasked == MotionEvent.ACTION_POINTER_DOWN
            ) {
                binding.coinList.stopScroll()
                binding.rowHeaderList.stopScroll()
            }
            false
        }
        binding.coinList.addOnScrollListener(object : RecyclerView.OnScrollListener() {

            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                scrollViewId = binding.coinList.id
            }

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                if (scrollViewId == binding.coinList.id) {
                    binding.rowHeaderList.scrollBy(dx, dy)
                }
            }
        })

        binding.rowHeaderList.addItemDecoration(
            DividerItemDecoration(
                context,
                DividerItemDecoration.VERTICAL
            )
        )
        rowHeaderListAdapter = RowHeaderListAdapter()
        binding.rowHeaderList.adapter = rowHeaderListAdapter
        binding.rowHeaderList.setOnTouchListener { _, event ->
            if (event.actionMasked == MotionEvent.ACTION_DOWN
                || event.actionMasked == MotionEvent.ACTION_POINTER_DOWN
            ) {
                binding.coinList.stopScroll()
                binding.rowHeaderList.stopScroll()
            }
            false
        }
        binding.rowHeaderList.addOnScrollListener(object : RecyclerView.OnScrollListener() {

            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                scrollViewId = binding.rowHeaderList.id
            }

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                if (scrollViewId == binding.rowHeaderList.id) {
                    binding.coinList.scrollBy(dx, dy)
                }
            }
        })
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
            SortingOptions.HIGH_PRICE_RATE -> binding.highPriceRate
            SortingOptions.LOW_PRICE_RATE -> binding.lowPriceRate
            else -> null
        }
    }
}