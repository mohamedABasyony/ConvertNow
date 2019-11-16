package com.basyony.convertnow.presentation.dashboard

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.basyony.convertnow.R
import com.basyony.convertnow.entities.RateUiItem
import kotlinx.android.synthetic.main.item_rate.view.*


class RateAdapter(
    private val context: Context
    , private var items: List<RateUiItem>
    , private val onItemChanged: SetOnItemChanged
) : RecyclerView.Adapter<RateAdapter.MovieItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieItemViewHolder {
        val root: View = LayoutInflater.from(context).inflate(R.layout.item_rate, parent, false)
        return MovieItemViewHolder(root)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun setData(items: List<RateUiItem>) {
        this.items = listOf()
        this.items = items
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: MovieItemViewHolder, position: Int) {
        holder.bindData(items[position])
        holder.rateET.setOnFocusChangeListener { _, b ->
            if (b) {
                onItemChanged.onFocus(items[position])
            }
        }
        holder.itemView.setOnClickListener { onItemChanged.onFocus(items[position]) }
    }

    class MovieItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val currencyCodeTV: TextView = view.tv_currency_code
        private val countryNameTV: TextView = view.tv_country_name
        val rateET: EditText = view.et_rate_value
        private val countryFlagIV: ImageView = view.iv_country_flag

        fun bindData(searchItem: RateUiItem) {
            searchItem.apply {
                currencyCodeTV.text = code
                countryNameTV.text = name
                rateET.setText(String.format("%.2f", rate))
                countryFlagIV.setImageResource(iconId)
            }
        }
    }

    interface SetOnItemChanged {
        fun onFocus(item: RateUiItem)
    }
}