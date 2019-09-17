package com.codemobiles.buyersguildmvp.activity

import android.widget.Toast
import com.codemobiles.presentation.view.HandleError
import dagger.android.support.DaggerAppCompatActivity

abstract class BaseActivity : DaggerAppCompatActivity(), HandleError {
    override fun setErrorMessage() {
        Toast.makeText(this,"Error to fetch data!",Toast.LENGTH_LONG)
    }
}
