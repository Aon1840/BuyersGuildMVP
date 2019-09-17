package com.codemobiles.buyersguildmvp.fragment


import com.codemobiles.presentation.view.HandleError
import dagger.android.support.DaggerFragment

abstract class BaseFragment : DaggerFragment(), HandleError {
    override fun setErrorMessage() {

    }
}
