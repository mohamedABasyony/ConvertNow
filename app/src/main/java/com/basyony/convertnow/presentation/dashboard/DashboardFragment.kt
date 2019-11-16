package com.basyony.convertnow.presentation.dashboard


import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.basyony.convertnow.R
import com.basyony.convertnow.entities.*
import com.basyony.convertnow.extensions.observe
import com.basyony.convertnow.presentation.bases.BaseFragment
import kotlinx.android.synthetic.main.fragment_dashboard.*
import kotlinx.android.synthetic.main.item_rate.*
import org.koin.android.viewmodel.ext.android.viewModel
import java.util.*


class DashboardFragment : BaseFragment(), RateAdapter.SetOnItemChanged {

    private var latestValue: Double = 1.0
    private var baseItem = RateUiItem("EUR", "Euro", 1.0, R.drawable.ic_european_union)
    private var listData: DashboardUiModel? = null
    private var adapter: RateAdapter? = null
    private var runTimer = true

    private val viewModel by viewModel<DashboardViewModel>()

    override val viewID: Int get() = R.layout.fragment_dashboard

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        observe(viewModel.screenData, ::onData)
        initList()
        initTimer()
        setBaseItem(baseItem)
        viewModel.load(baseItem.code)
        handleUserInteractions()
    }

    private fun initList() {
        val layoutManger = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        list_rates.layoutManager = layoutManger
    }

    private fun handleUserInteractions() {
        swipe_layout.setOnRefreshListener { viewModel.load(baseItem.code) }
        et_rate_value.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                delayTimer()
                if (adapter != null && listData != null && p0 != null && p0.isNotEmpty()) {
                    latestValue = p0.toString().toDouble()
                    baseItem.rate = latestValue
                    adapter!!.setData(listData!!.rates.map {
                        RateUiItem(
                            it.code,
                            it.name,
                            it.rate * latestValue,
                            it.iconId
                        )
                    })
                }
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
        })
    }

    private fun initTimer() {
        val timer = Timer()
        val task = object : TimerTask() {
            override fun run() {
                if (runTimer) {
                    viewModel.load(baseItem.code)
                }
            }
        }
        timer.schedule(task, 0L, 1000)
    }

    private fun setBaseItem(item: RateUiItem) {
        tv_country_name.text = item.name
        tv_currency_code.text = item.code
        et_rate_value.setText(String.format("%.2f", item.rate))
        iv_country_flag.setImageResource(item.iconId)
    }

    private fun onData(state: BasicScreenState) {
        when (state) {
            is Success<*> -> bindScreenData(state.uiModel as DashboardUiModel)
            is ScreenError -> handleError(state.appError)
            is Loading -> showLoading()
        }
    }

    @SuppressLint("SetTextI18n")
    private fun bindScreenData(uiModel: DashboardUiModel) {
        hideLoading()
        listData = uiModel
        swipe_layout.isRefreshing = false
        if (adapter == null) {
            adapter = RateAdapter(context!!, uiModel.rates, this)
            list_rates.adapter = adapter
        } else {
            adapter!!.setData(uiModel.rates.map {
                RateUiItem(
                    it.code,
                    it.name,
                    it.rate * latestValue,
                    it.iconId
                )
            })
        }
    }

    override fun onFocus(item: RateUiItem) {
        baseItem = item
        delayTimer()
        latestValue = baseItem.rate
        setBaseItem(baseItem)
        viewModel.load(baseItem.code)
    }

    private fun delayTimer() {
        runTimer = false
        Handler().postDelayed({
            runTimer = true
        }, 10000)
    }
}

