package com.basyony.convertnow.presentation.dashboard

import androidx.lifecycle.MutableLiveData
import com.basyony.convertnow.domain.errorMapper
import com.basyony.convertnow.domain.gateways.RatesAPI
import com.basyony.convertnow.domain.toDashboardUiModel
import com.basyony.convertnow.entities.*
import com.basyony.convertnow.extensions.disposeBy
import com.basyony.convertnow.presentation.bases.BaseViewModel


class DashboardViewModel (
    private val repository: Repository<RatesAPI, LatestResponse>,
    private val stateDataSource: RatesAPI,
    private val composer: Composer<LatestResponse>
) : BaseViewModel() {

    internal val screenData by lazy { MutableLiveData<BasicScreenState>() }

    internal fun load(base : String) {
        screenData.postValue(Loading)
        repository(stateDataSource,base)
            .compose { composer(it) }
            .subscribe(
                { screenData.postValue(Success(it.toDashboardUiModel())) },
                { screenData.postValue(ScreenError(errorMapper(it))) }
            )
            .disposeBy(disposables)
    }

}
