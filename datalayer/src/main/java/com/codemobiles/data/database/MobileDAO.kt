package com.codemobiles.buyersguildmvp.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.codemobiles.data.model.db.MobileEntity

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