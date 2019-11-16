package com.basyony.convertnow.di

import com.basyony.convertnow.domain.dashboardRepository
import com.basyony.convertnow.domain.gateways.RatesAPI
import com.basyony.convertnow.entities.LatestResponse
import com.basyony.convertnow.entities.Repository
import com.basyony.convertnow.presentation.dashboard.DashboardViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module


val dashboardModule = module {
    factory<Repository<RatesAPI, LatestResponse>>(named("dashboardRepository")) { ::dashboardRepository }

    viewModel {
        DashboardViewModel(
            repository = get(named("dashboardRepository")),
            stateDataSource = get(named("rateAPI")),
            composer = get(named("composer"))
        )
    }
}