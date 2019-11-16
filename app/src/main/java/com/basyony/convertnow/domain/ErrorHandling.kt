package com.basyony.convertnow.domain

import com.basyony.convertnow.entities.AppError

fun errorMapper(throwable: Throwable): AppError {
    throwable.printStackTrace()
    TODO("Not Implemented")
}
