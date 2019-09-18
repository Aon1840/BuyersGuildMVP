package com.codemobiles.buyersguildmvp.fragment


import android.view.View
import com.codemobiles.presentation.view.BaseView
import com.google.android.material.snackbar.Snackbar
import dagger.android.support.DaggerFragment

abstract class BaseFragment : DaggerFragment(), BaseView {

    abstract fun getViewFragment(): View?

    override fun showErrorMessage(errorMessage: String) {
        getViewFragment()?.let {
            Snackbar.make(
                it,
                errorMessage,
                Snackbar.LENGTH_LONG
            ).show()
        }
    }
}
