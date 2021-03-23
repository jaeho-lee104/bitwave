package com.ecohouse.bitwave.coins

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ecohouse.bitwave.data.Coin
import com.ecohouse.bitwave.databinding.CoinItemBinding
import com.ecohouse.bitwave.utils.formatDisplay


/**
 * @author leejaeho on 2021. 03. 21..
 */
class CoinListAdapter : ListAdapter<Coin, RecyclerView.ViewHolder>(CoinDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return CoinViewHolder.from(parent)

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is CoinViewHolder) {
            holder.bind(getItem(position))
        }
    }

    class CoinViewHolder private constructor(private val binding: CoinItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(coin: Coin) {
            binding.name.text = coin.name
            binding.price.text = coin.price.formatDisplay()
            binding.changeRate.text = coin.changeRate.formatDisplay().plus("%")
            binding.volume.text = coin.volume.formatDisplay()
        }

        companion object {
            fun from(parent: ViewGroup): CoinViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = CoinItemBinding.inflate(layoutInflater, parent, false)
                return CoinViewHolder(binding)
            }
        }
    }

}

class CoinDiffCallback : DiffUtil.ItemCallback<Coin>() {
    override fun areItemsTheSame(oldItem: Coin, newItem: Coin): Boolean {
        return oldItem.market == newItem.market
    }

    override fun areContentsTheSame(oldItem: Coin, newItem: Coin): Boolean {
        return oldItem == newItem
    }
}
