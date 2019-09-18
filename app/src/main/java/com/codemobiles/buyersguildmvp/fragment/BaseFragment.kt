package com.codemobiles.buyersguildmvp.fragment


import android.view.View
import com.codemobiles.presentation.view.BaseView
import com.google.android.material.snackbar.Snackbar
import dagger.android.support.DaggerFragment

abstract class BaseFragment : DaggerFragment(), BaseView {

    abstract fun getRootView(): View?

    override fun showErrorMessage(errorMessage: String) {
        getRootView()?.let { view ->
            Snackbar.make(
                view,
                errorMessage,
                Snackbar.LENGTH_LONG
            ).show()
        }
    }
}
