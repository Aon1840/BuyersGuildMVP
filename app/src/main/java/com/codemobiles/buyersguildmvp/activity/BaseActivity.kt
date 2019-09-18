package com.codemobiles.buyersguildmvp.activity

import android.view.View
import com.codemobiles.presentation.view.BaseView
import com.google.android.material.snackbar.Snackbar
import dagger.android.support.DaggerAppCompatActivity

abstract class BaseActivity : DaggerAppCompatActivity(), BaseView {

    abstract fun getView(): View

    override fun showErrorMessage(errorMessage: String) {
        Snackbar.make(
            getView(),
            errorMessage,
            Snackbar.LENGTH_LONG
        ).show()
    }
}
