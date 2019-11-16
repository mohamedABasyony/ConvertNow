package com.basyony.convertnow.extensions

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

fun Disposable.disposeBy(disposables: CompositeDisposable) {
    disposables.add(this)
}