package com.basyony.convertnow.entities

import io.reactivex.Single

typealias Repository<T, R> = (dataSource: T, base: String) -> Single<R>
typealias ErrorMapper = (Throwable) -> AppError
typealias Composer<R> = (Single<R>) -> Single<R>
