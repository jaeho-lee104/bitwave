package com.ecohouse.bitwave.views

import android.content.Context
import android.util.AttributeSet
import android.util.TypedValue.COMPLEX_UNIT_PX
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.ecohouse.bitwave.R
import com.ecohouse.bitwave.databinding.ViewSortingStateBinding
import com.ecohouse.bitwave.utils.getColor
import com.ecohouse.bitwave.utils.getDimension


/**
 * @author leejaeho on 2021. 03. 24..
 */
class SortingStateView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    private var binding = ViewSortingStateBinding.inflate(LayoutInflater.from(context), this, true)

    init {
        orientation = HORIZONTAL
        if (attrs != null) {
            val typedArray =
                context.obtainStyledAttributes(attrs, R.styleable.SortingStateView, 0, 0)
            binding.title.text = typedArray.getString(R.styleable.SortingStateView_title) ?: ""
            binding.title.setTextSize(
                COMPLEX_UNIT_PX, typedArray.getDimension(
                    R.styleable.SortingStateView_titleSize,
                    getDimension(R.dimen.coin_item_text_size)
                )
            )
            binding.title.setTextColor(
                typedArray.getColor(
                    R.styleable.SortingStateView_titleColor,
                    getColor(R.color.purple_200)
                )
            )
            typedArray.recycle()
        }
    }

    var title: String
        set(value) {
            binding.title.text = value
        }
        get() = binding.title.text.toString()

    var sortingState: SortingState = SortingState.NONE
        set(value) {
            field = value
            updateArrowByState(value)
        }

    private fun updateArrowByState(state: SortingState) {
        when (state) {
            SortingState.NONE -> {
                binding.arrowDown.visibility = GONE
                binding.arrowUp.visibility = GONE
            }
            SortingState.ASCENDING -> {
                binding.arrowDown.visibility = GONE
                binding.arrowUp.visibility = VISIBLE
            }
            SortingState.DESCENDING -> {
                binding.arrowUp.visibility = GONE
                binding.arrowDown.visibility = VISIBLE
            }
        }
    }

}