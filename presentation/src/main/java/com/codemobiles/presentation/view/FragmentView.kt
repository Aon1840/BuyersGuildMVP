package com.codemobiles.presentation.view

import com.codemobiles.domain.model.MobileModel

interface FragmentView {
    fun addFavorite(data: MobileModel)
    fun removeFavourite(mobile: MobileModel)
}