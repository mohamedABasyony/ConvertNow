package com.basyony.convertnow.domain

import android.accounts.NetworkErrorException
import com.basyony.convertnow.entities.*
import java.net.UnknownHostException
import java.net.UnknownServiceException
import java.util.concurrent.TimeoutException

fun errorMapper(throwable: Throwable): AppError {
    throwable.printStackTrace()
    return when (throwable.cause) {
        is NetworkErrorException -> NoInternet
        is UnknownHostException -> NoInternet
        is UnknownServiceException -> ServerDown
        is TimeoutException -> TimeOut
        else -> Business
    }
}
