package com.codemobiles.domain.repository

import com.codemobiles.domain.model.PhotoListModel
import io.reactivex.Observable

interface PhotoRepository {
    fun getPhoto(id: Int): Observable<List<PhotoListModel>>
}