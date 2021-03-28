package com.ecohouse.bitwave.views.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ecohouse.bitwave.data.Coin
import com.ecohouse.bitwave.databinding.HeaderItemBinding


/**
 * @author leejaeho on 2021. 03. 28..
 */
class RowHeaderListAdapter :
    ListAdapter<Coin, RowHeaderListAdapter.ViewHolder>(RowHeaderDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position), position)
    }

    class ViewHolder private constructor(private val binding: HeaderItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(coin: Coin, position: Int) {
            binding.headerName.text = coin.name
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = HeaderItemBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }
}

class RowHeaderDiffCallback : DiffUtil.ItemCallback<Coin>() {
    override fun areItemsTheSame(oldItem: Coin, newItem: Coin): Boolean {
        return oldItem.market == newItem.market
    }

    override fun areContentsTheSame(oldItem: Coin, newItem: Coin): Boolean {
        return oldItem == newItem
    }
}