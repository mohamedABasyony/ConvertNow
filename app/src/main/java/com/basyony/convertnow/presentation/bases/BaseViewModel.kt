package com.basyony.convertnow.presentation.bases

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable

open class BaseViewModel : ViewModel() {

    protected val disposables by lazy { CompositeDisposable() }


    override fun onCleared() {
        disposables.clear()
        super.onCleared()
    }

}