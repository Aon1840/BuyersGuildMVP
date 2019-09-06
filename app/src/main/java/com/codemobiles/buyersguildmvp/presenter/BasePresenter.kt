package com.codemobiles.buyersguildmvp.presenter


abstract class BasePresenter<T : com.codemobiles.buyersguildmvp.contract.BaseView> {
    protected var mView: T? = null

    fun setView(view: T) {
        this.mView = view
    }

}