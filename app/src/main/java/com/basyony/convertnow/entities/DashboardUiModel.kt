package com.basyony.convertnow.entities

data class DashboardUiModel(
    val base: String,
    val date: String,
    val rates: List<RateUiItem>
)