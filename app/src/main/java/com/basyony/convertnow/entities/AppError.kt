package com.basyony.convertnow.entities

/**
 * AppError is an enum type for all backend error types
 */
sealed class AppError : Throwable()

object NoInternet : AppError()
object TimeOut : AppError()
object Business : AppError()
object ServerDown : AppError()
