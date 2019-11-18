package com.basyony.convertnow.domain

import com.basyony.convertnow.R
import com.basyony.convertnow.domain.gateways.RatesAPI
import com.basyony.convertnow.entities.DashboardUiModel
import com.basyony.convertnow.entities.LatestResponse
import com.basyony.convertnow.entities.RateUiItem
import com.basyony.convertnow.entities.Rates
import io.reactivex.Single


fun dashboardRepository(dataSource: RatesAPI, base : String): Single<LatestResponse> {
    return dataSource.getLatestRates(base)
}

fun LatestResponse.toDashboardUiModel() =
    DashboardUiModel(base = this.base ,
        date = this.date,
        rates = mapRateObject(this.rates)
    )


fun mapRateObject(rates: Rates) : MutableList<RateUiItem> {
    rates.apply {
        val rateUiList: MutableList<RateUiItem> = mutableListOf()
        if (eUR != -1.0) rateUiList.add(RateUiItem("EUR", "Euro", eUR, R.drawable.ic_european_union))
        if (uSD != -1.0) rateUiList.add(RateUiItem("USD", "United States Dollar", uSD, R.drawable.ic_united_states))
        if (gBP != -1.0) rateUiList.add(RateUiItem("GBP", "United Kingdom Pound", gBP, R.drawable.ic_united_kingdom))
        if (cHF != -1.0) rateUiList.add(RateUiItem("CHF", "Swiss Franc", cHF, R.drawable.ic_switzerland))
        if (cNY != -1.0) rateUiList.add(RateUiItem("CNY", "China Yuan", cNY, R.drawable.ic_china))
        if (jPY != -1.0) rateUiList.add(RateUiItem("JPY", "Japan Yen", jPY, R.drawable.ic_japan))
        if (hRK != -1.0) rateUiList.add(RateUiItem("HRK", "Croatia Kuna", hRK, R.drawable.ic_croatia))
        if (mXN != -1.0) rateUiList.add(RateUiItem("MXN", "Mexico Peso", mXN, R.drawable.ic_mexico))
        if (zAR != -1.0) rateUiList.add(RateUiItem("ZAR", "South Africa Rand", zAR, R.drawable.ic_south_africa))
        if (iNR != -1.0) rateUiList.add(RateUiItem("INR", "Indian Rupee", iNR, R.drawable.ic_india))
        if (tHB != -1.0) rateUiList.add(RateUiItem("THB", "Thailand Baht", tHB, R.drawable.ic_thailand))
        if (aUD != -1.0) rateUiList.add(RateUiItem("AUD", "Australian Dollar", aUD, R.drawable.ic_australia))
        if (iLS != -1.0) rateUiList.add(RateUiItem("ILS", "Israel Shekel", iLS, R.drawable.ic_israel))
        if (kRW != -1.0) rateUiList.add(RateUiItem("KRW", "South Korean Won", kRW, R.drawable.ic_south_korea))
        if (pLN != -1.0) rateUiList.add(RateUiItem("PLN", "Polish Zloty", pLN, R.drawable.ic_poland))
        if (iDR != -1.0) rateUiList.add(RateUiItem("IDR", "Indonesia Rupiah", iDR, R.drawable.ic_indonesia))
        if (hUF != -1.0) rateUiList.add(RateUiItem("HUF", "Hungarian Forint", hUF, R.drawable.ic_hungary))
        if (pHP != -1.0) rateUiList.add(RateUiItem("PHP", "Philippines Peso", pHP, R.drawable.ic_philippines))
        if (tRY != -1.0) rateUiList.add(RateUiItem("TRY", "Turkish Lira", tRY, R.drawable.ic_turkey))
        if (rUB != -1.0) rateUiList.add(RateUiItem("RUB", "Russia Ruble", rUB, R.drawable.ic_russia))
        if (hKD != -1.0) rateUiList.add(RateUiItem("HKD", "Hong Kong Dollar", hKD, R.drawable.ic_hong_kong))
        if (iSK != -1.0) rateUiList.add(RateUiItem("ISK", "Iceland Krona", iSK, R.drawable.ic_iceland))
        if (dKK != -1.0) rateUiList.add(RateUiItem("DKK", "Denmark Krone", dKK, R.drawable.ic_denmark))
        if (cAD != -1.0) rateUiList.add(RateUiItem("CAD", "Canadian Dollar", cAD, R.drawable.ic_canada))
        if (mYR != -1.0) rateUiList.add(RateUiItem("MYR", "Malaysian Ringgit", mYR, R.drawable.ic_malaysia))
        if (bGN != -1.0) rateUiList.add(RateUiItem("BGN", "Bulgarian Lev", bGN, R.drawable.ic_bulgaria))
        if (nOK != -1.0) rateUiList.add(RateUiItem("NOK", "Norway Krone", nOK, R.drawable.ic_norway))
        if (rON != -1.0) rateUiList.add(RateUiItem("RON", "Romanian Leu", rON, R.drawable.ic_romania))
        if (sGD != -1.0) rateUiList.add(RateUiItem("SGD", "Singapore Dollar", sGD, R.drawable.ic_singapore))
        if (cZK != -1.0) rateUiList.add(RateUiItem("CZK", "Czech Koruna", cZK, R.drawable.ic_czech_republic))
        if (sEK != -1.0) rateUiList.add(RateUiItem("SEK", "Sweden Krona", sEK, R.drawable.ic_sweden))
        if (nZD != -1.0) rateUiList.add(RateUiItem("NZD", "New Zealand Dollar", nZD, R.drawable.ic_new_zealand))
        if (bRL != -1.0) rateUiList.add(RateUiItem("BRL", "Brazilian Real", bRL, R.drawable.ic_brazil))
        return rateUiList
    }
}