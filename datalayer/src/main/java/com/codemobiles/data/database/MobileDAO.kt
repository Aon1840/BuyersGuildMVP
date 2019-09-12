package com.codemobiles.buyersguildmvp.database

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Insert
import androidx.room.Delete
import com.codemobiles.data.model.db.MobileEntity
import io.reactivex.Completable

@Dao
interface MobileDAO {
    @Query(value = "select * from favorite")
    fun queryFavorites(): List<MobileEntity>

    @Insert
    fun addFavorite(favoriteEntity: MobileEntity)
//    fun addFavorite(favoriteEntity: MobileEntity): Completable

    @Delete
    fun deleteFavorite(favoriteEntity: MobileEntity)
//    fun deleteFavorite(favoriteEntity: MobileEntity): Completable
}