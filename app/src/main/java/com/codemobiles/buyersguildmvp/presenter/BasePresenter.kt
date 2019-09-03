package com.codemobiles.buyersguildmvp.presenter

import com.codemobiles.buyersguildmvp.contract.BaseView

abstract class BasePresenter<T : BaseView> {
    protected var mView: T? = null

    fun setView(view: T) {
        this.mView = view
    }

}