package com.ecohouse.bitwave

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.ecohouse.bitwave.coins.CoinListAdapter
import com.ecohouse.bitwave.databinding.FragmentHomeBinding
import com.ecohouse.bitwave.utils.getViewModelFactory

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var listAdapter: CoinListAdapter

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

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupListAdapter()
        setupRefreshLayout()
        subscribeUi()
    }

    override fun onStart() {
        super.onStart()
        viewModel.loadCoins(true)
    }

    private fun subscribeUi() {
        viewModel.items.observe(viewLifecycleOwner) {
            listAdapter.submitList(it)
        }
    }

    private fun setupListAdapter() {
        listAdapter = CoinListAdapter()
        binding.coinList.adapter = listAdapter
    }

    private fun setupRefreshLayout() {

    }
}