package com.basyony.convertnow.domain

import com.basyony.convertnow.entities.ErrorMapper
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

fun <T> composer(it: Single<T>, errorMapper: ErrorMapper) =
    it.subscribeOn(Schedulers.io())
        .observeOn(Schedulers.io())
        .onErrorResumeNext { Single.error(errorMapper(it)) }
