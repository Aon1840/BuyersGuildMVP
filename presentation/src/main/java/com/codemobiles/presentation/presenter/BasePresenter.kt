package com.codemobiles.presentation.presenter

import com.codemobiles.presentation.view.BaseView


abstract class BasePresenter<T : BaseView> {
    protected var mView: T? = null

    fun setView(view: T) {
        this.mView = view
    }

}