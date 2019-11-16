package com.basyony.convertnow.entities

sealed class BasicScreenState
class Success<T>(val uiModel: T) : BasicScreenState()
object Loading : BasicScreenState()
class ScreenError(val appError: AppError) : BasicScreenState()

