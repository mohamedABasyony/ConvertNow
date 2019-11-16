package com.basyony.convertnow.di

import com.basyony.convertnow.domain.composer
import com.basyony.convertnow.domain.errorMapper
import com.basyony.convertnow.domain.gateways.RatesAPI
import com.basyony.convertnow.entities.Composer
import com.basyony.convertnow.entities.ErrorMapper
import com.basyony.convertnow.frameworks.generateRetrofit
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import io.reactivex.schedulers.Schedulers
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {
    factory(named("baseUrl")) { "https://revolut.duckdns.org/" }
    factory(named("callFactory")) { RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()) }
    factory(named("jsonConverter")) { GsonConverterFactory.create() }
    factory(named("retrofit")) {
        generateRetrofit(
            baseUrl = get(named("baseUrl")),
            callFactory = get(named("callFactory")),
            converter = get(named("jsonConverter"))
        )
    }

    single<RatesAPI>(named("rateAPI")) {
        get<Retrofit>(named("retrofit")).create(
            RatesAPI::class.java
        )
    }

    single<ErrorMapper>(named("errorMapper")) { ::errorMapper }

    single<Composer<*>>(named("composer")) {
        {
            composer(
                it,
                get(named("errorMapper"))
            )
        }
    }
}

