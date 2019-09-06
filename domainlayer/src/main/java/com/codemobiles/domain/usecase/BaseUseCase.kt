package com.codemobiles.domain.usecase

import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers

abstract class BaseUseCase<T, Params>  {

    private val compositeDisposable = CompositeDisposable()

    abstract fun getObservable(params: Params?): Observable<T>

    fun execute(observer: DisposableObserver<T>, params: Params?) {
        val observable = this.getObservable(params)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
        addDisposable(observable.subscribeWith(observer))
    }

    fun dispose() {
        if (!compositeDisposable.isDisposed) {
            compositeDisposable.dispose()
        }
    }

    private fun addDisposable(disposable: Disposable) {
        compositeDisposable.add(disposable)
    }

}