package com.codemobiles.presentation.presenter

import com.codemobiles.presentation.view.BaseView
import com.codemobiles.presentation.view.HandleError


abstract class BasePresenter<T : BaseView> : HandleError {
    protected var mView: T? = null

    fun setView(view: T) {
        this.mView = view
    }

    override fun setErrorMessage() {

    }

}