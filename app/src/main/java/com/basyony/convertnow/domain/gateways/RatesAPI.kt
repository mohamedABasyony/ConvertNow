package com.basyony.convertnow.domain.gateways

import com.basyony.convertnow.entities.LatestResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface RatesAPI {
    @GET("latest")
    fun getLatestRates(@Query("base") base: String): Single<LatestResponse>

}
