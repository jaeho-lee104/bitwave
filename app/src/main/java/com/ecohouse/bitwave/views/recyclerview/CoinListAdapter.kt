package com.ecohouse.bitwave.views.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ecohouse.bitwave.R
import com.ecohouse.bitwave.data.Coin
import com.ecohouse.bitwave.databinding.CoinItemBinding
import com.ecohouse.bitwave.utils.formatDisplay
import com.ecohouse.bitwave.utils.getColor


/**
 * @author leejaeho on 2021. 03. 21..
 */
class CoinListAdapter : ListAdapter<Coin, RecyclerView.ViewHolder>(CoinDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return CoinViewHolder.from(parent)

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is CoinViewHolder) {
            holder.bind(getItem(position), position)
        }
    }

    class CoinViewHolder private constructor(private val binding: CoinItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(coin: Coin, position: Int) {
            binding.apply {
                price.text = coin.price.formatDisplay()
                volume.text = coin.volume.formatDisplay()

                changeRate.text = coin.priceChangeRate.formatDisplay().plus("%")
                when {
                    coin.priceChangeRate > 0 -> changeRate.setTextColor(getColor(R.color.plus_rate_text_color))
                    coin.priceChangeRate < 0 -> changeRate.setTextColor(getColor(R.color.minus_rate_text_color))
                    else -> changeRate.setTextColor(getColor(R.color.coin_item_default_text_color))
                }

                volumeRate.text = coin.volumeChangeRate.formatDisplay().plus("%")
                when {
                    coin.volumeChangeRate > 100 -> volumeRate.setTextColor(getColor(R.color.plus_rate_text_color))
                    else -> volumeRate.setTextColor(getColor(R.color.coin_item_default_text_color))
                }

                breakOutRate.text = coin.breakOutRate.formatDisplay().plus("%")
                when {
                    coin.breakOutRate > 100 -> breakOutRate.setTextColor(getColor(R.color.plus_rate_text_color))
                    else -> breakOutRate.setTextColor(getColor(R.color.coin_item_default_text_color))
                }

                rsiValue.text = coin.rsi.formatDisplay()
                when {
                    coin.rsi >= 70 -> rsiValue.setTextColor(getColor(R.color.plus_rate_text_color))
                    coin.rsi <= 30 -> rsiValue.setTextColor(getColor(R.color.minus_rate_text_color))
                    else -> rsiValue.setTextColor(getColor(R.color.coin_item_default_text_color))
                }

                highPriceRate.text = coin.highPriceRate.formatDisplay().plus("%")
                lowPriceRate.text = coin.lowPriceRate.formatDisplay().plus("%")
            }
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
